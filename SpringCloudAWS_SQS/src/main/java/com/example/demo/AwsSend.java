/**
 * 
 */
package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.buffered.AmazonSQSBufferedAsyncClient;

@Configuration
public class AwsSend {
	@Value("${jsa.aws.access_key_id}")
	private String awsId;

	@Value("${jsa.aws.secret_access_key}")
	private String awsKey;
	
	@Value("${jsa.s3.region}")
	private String region;

	@Bean
	public QueueMessagingTemplate queueMessagingTemplate() {
	 
		return new QueueMessagingTemplate(amazonSQS());		
	}

	@Bean(destroyMethod = "shutdown")
	public AmazonSQSAsync amazonSQS() {
						
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJ6NFGXM6QI6PCUZA", "h9gEcRsFX2w9ij6mt9BqMbJPR2AVLEVBiCz30Klz");

		AmazonSQSAsyncClient amazonSQSAsyncClient = new AmazonSQSAsyncClient(new AWSStaticCredentialsProvider(awsCreds));
	  
		amazonSQSAsyncClient.setRegion(com.amazonaws.regions.Region.getRegion(Regions.fromName("eu-west-2")));
		return new AmazonSQSBufferedAsyncClient(amazonSQSAsyncClient);
	  
	  
	}
	
	
}


