package com.arun;

import java.time.LocalTime;
import java.util.TimerTask;

public class Task1 extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(LocalTime.now());		
	}
	
	

}
