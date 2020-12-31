package com.policynepal.autocare.aws.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.policynepal.autocare.aws.model.AwsCredential;

@Configuration
public class AwsConfiguration {
	
	@Autowired
	private AwsCredential awsCredential;
	@Bean
	public AWSCredentials getAwsCredentials() {
		return new BasicAWSCredentials(awsCredential.getAccessKey(), awsCredential.getAccessSecret());
	}

	@Bean
	public AmazonS3Client getAmazonS3Client() {
		EndpointConfiguration endpointConfiguration = new EndpointConfiguration(awsCredential.getS3().getBaseUrl(),
				awsCredential.getS3().getRegion());

		return (AmazonS3Client) AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(getAwsCredentials()))
				.withEndpointConfiguration(endpointConfiguration)
				.build();
	}
}
