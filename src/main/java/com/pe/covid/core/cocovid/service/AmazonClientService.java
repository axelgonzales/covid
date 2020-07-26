package com.pe.covid.core.cocovid.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AmazonClientService {

    private AmazonS3 s3client;
  
    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.clientRegion}")
    private String clientRegion;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        s3client = AmazonS3ClientBuilder.standard().withRegion(this.clientRegion).withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    public String uploadFile(String folderName, MultipartFile multipartFile, Boolean readPrivate) {
    	String fileUrl = "";
    	try {
            File file = convertMultiPartToFile(multipartFile);
            String fileNameS3 = generateFileName(multipartFile, false);
            if (readPrivate) {
            	uploadFileToS3Bucket(folderName, fileNameS3, file);
			} else {
				uploadFileToS3BucketPublic(folderName, fileNameS3, file);
			}
            
            log.info("--Uploading file done");
             fileUrl = getEndpointUrl(folderName, fileNameS3);
          

            file.delete();
        } catch (IOException | AmazonS3Exception e) {
            log.info("error " + e.getLocalizedMessage());
        }
        return fileUrl;
    }

  
    private String getEndpointUrl(String folderName, String fileName) {
        return this.endpointUrl + '/' + this.bucketName + '/' + folderName + '/' + fileName;
    }

    public InputStream downloadFile(String folderName, String fileName) {
        InputStream is = null;
        try {
            S3Object fullObject;
            fullObject = getFileFromS3Bucket(folderName, fileName);
            log.info("--Uploading file done");
            return fullObject.getObjectContent();
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        }
        return is;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart, Boolean ramdon) {
        if (ramdon)
            return new Date().getTime() + "-" + multiPart.getOriginalFilename().replaceAll(" ", "_");
        else
            return multiPart.getOriginalFilename().replaceAll(" ", "_");
    }

    private S3Object getFileFromS3Bucket(String folderName, String fileName) {
        return s3client.getObject(new GetObjectRequest(this.bucketName, folderName + "/" + fileName));
    }

    private void uploadFileToS3Bucket(String folderName, String fileName, File file) {
        s3client.putObject(new PutObjectRequest(this.bucketName, folderName + "/" + fileName, file));
    }
    private void uploadFileToS3BucketPublic(String folderName, String fileName, File file) {
        s3client.putObject(new PutObjectRequest(this.bucketName, folderName + "/" + fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));

    }
    
    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        return "Successfully deleted";
    }

    public String getFileUrl(String folder, String fileName) {
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);
        log.debug("Generating pre-signed URL");
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, folder + "/" + fileName)
                .withMethod(HttpMethod.GET).withExpiration(expiration);
        URL url = s3client.generatePresignedUrl(generatePresignedUrlRequest);
        log.debug("Generated pre-signed URL: {}", url.toString());
        return url.toString();
    }
}
