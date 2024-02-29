package com.arun.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Scheduled(fixedRate= 3000)
	public void generateReport() {
		System.out.println(
				"sales Report generation started  at:" + (LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"))));
		
		try
		{
		      Thread.sleep(5000);	
		}
		catch(InterruptedException e)
		{
			 e.printStackTrace(); 
		}
		
		System.out.println(
				"sales Report generation completed  at:" + (LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"))));

	}

}
