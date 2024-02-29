package com.arun;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.arun.model.Item;
import com.arun.model.Order;
import com.arun.model.UserInfo;
import com.arun.service.IOrderManagerMailService;

@SpringBootApplication
public class SpringbootMailDemoApplication {

	public static void main(String...args)  {
		ApplicationContext ctx=SpringApplication.run(SpringbootMailDemoApplication.class, args);
		
		IOrderManagerMailService mailService=ctx.getBean(IOrderManagerMailService.class);
		
	    List<Item> items=Arrays.asList(new Item("Mobile",(double) 20000,2),new Item("Ear phones",(double) 2000,3) );
	    
	    
			Order order = new Order(new Random().nextInt(0,1000),null,null,"Hyderabad",items);
		
	
			try {
				
				UserInfo info=new UserInfo("Arunkumar","aruntanakam2000@gmail.com");
		
		System.out.println(mailService.triggerMail(info));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
	
	}

}
