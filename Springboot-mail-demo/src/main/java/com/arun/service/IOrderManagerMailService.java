package com.arun.service;

import com.arun.model.Order;
import com.arun.model.UserInfo;

public interface IOrderManagerMailService {
	
	public String triggerMail(Order order) ;
	public String triggerMail(UserInfo info);

}
