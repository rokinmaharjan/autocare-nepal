package com.policynepal.autocare.aws.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Service {
	
	private S3Service() {
		
	}
	
	private static final String SUFFIX = "/";

	public static void createFolder(AmazonS3Client s3Client, String bucketName, String folderName) {
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName + SUFFIX, emptyContent,
				metadata);

		s3Client.putObject(putObjectRequest);
	}

	public static void uploadFileInS3(AmazonS3Client s3Client, String bucketName, String key, File file) {
		s3Client.putObject(new PutObjectRequest(bucketName, key, file)
				.withCannedAcl(CannedAccessControlList.PublicRead));
	}

	public static void deleteFileInS3(AmazonS3Client s3Client, String bucketName, String key) {
		s3Client.deleteObject(bucketName, key);
	}
	
	public static String getFileUrl(AmazonS3Client s3Client, String bucketName, String key) {
		return s3Client.getResourceUrl(bucketName, key);
	}

}
