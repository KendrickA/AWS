package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class Send {
	
	@Autowired
	QueueMessagingTemplate queueMessagingTemplate;
	
	static class Toll {
		public String stationId;
		public String customerId;
		public String timestamp;
		
		public Toll()
		{
			
		}
		
//		public Toll(String StationId, String CustomerId, String Timestamp) {
//			this.stationId = StationId;
//			this.customerId = CustomerId;
//			this.timestamp = Timestamp;
//		}
		
		public String getStationId() {
			return stationId;
		}

		public void setStationId(String stationId) {
			this.stationId = stationId;
		}

		public String getCustomerId() {
			return customerId;
		}

		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}

		public String getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		
	}
	
	public void send(String payload)
	{
		//MyEvent event = new MyEvent("Hello World");

		//queueMessagingTemplate.convertAndSend("my_queue_name", new Toll("20","100","2017-07-12T12:04:00"));
		
		Toll t =new Toll();
		t.setStationId("20");
		queueMessagingTemplate.convertAndSend("my_queue_name", t);;
	}
	
	
	@SqsListener(value = "my_queue_name", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void consume(Toll toll) {
		
		System.out.println(toll.getStationId());
	  //TODO handle the received event
	}
	
	

}
