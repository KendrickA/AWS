package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class AwsApplication implements CommandLineRunner{
	
	@Autowired
	Send send;

	public static void main(String[] args) {
		SpringApplication.run(AwsApplication.class, args);
		
		
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		send.send("test payload");
	}
}
