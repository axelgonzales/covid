package com.pe.covid.core.cocovid.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;

public interface AmazonClientService {

    public String uploadFile(String folderName, MultipartFile multipartFile, Boolean readpublic) ;
  
    public String getEndpointUrl(String folderName, String fileName) ;

    public InputStream downloadFile(String folderName, String fileName);
    public File convertMultiPartToFile(MultipartFile file) throws IOException ;

    public String generateFileName(MultipartFile multiPart, Boolean ramdon) ;

    public S3Object getFileFromS3Bucket(String folderName, String fileName) ;
    public void uploadFileToS3Bucket(String folderName, String fileName, File file);
    public void uploadFileToS3BucketPublic(String folderName, String fileName, File file) ;
    public String deleteFileFromS3Bucket(String fileUrl) ;

    public String getFileUrl(String folder, String fileName) ;
}
