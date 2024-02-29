package com.arun.service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.arun.model.Item;
import com.arun.model.Order;
import com.arun.model.UserInfo;

import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;

@Service
public class OrderManagerMailServiceImpl implements IOrderManagerMailService {
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Configuration config;//for freemarker
	
	@Autowired
	private TemplateEngine engine;//for thymeleaf
	

	
	
	
	
	/*
	 * public OrderManagerMailServiceImpl(JavaMailSender mailSender, Configuration
	 * config) { super(); this.mailSender = mailSender; this.config = config; }
	 */



	String path;
	@Override
	public String triggerMail(Order order)  {
		
		try {
        double total=0.0;
        
        for(Item item:order.getItems())
        {
        	total+=(item.getItemPrice())*(item.getQuantity());
        }
        order.setTotalBill(total);
        order.setTotalItems(order.getItems().size());
        
       String text=buildMailBody(order);
       
       MimeMessage message=mailSender.createMimeMessage();
       
       MimeMessageHelper  helper=new MimeMessageHelper(message, true);
       
       //SimpleMailMessage message=new SimpleMailMessage();
       
       helper.setFrom("aruntanakam2000@gmail.com");
       helper.setTo("madhutanakam@gmail.com");
       helper.setCc(new String[]{"aruntanakam2000@gmail.com","madhutanakam@gmail.com"});
       helper.setBcc("aruntanakam0107@gmail.com");
       //helper.setText(buildMailBody(order));
       helper.setText("<html><body><img src=\"cid:identifier1234\" width='30' height='30'></body></html>",true);      
       helper.setSubject("Order Summary");
       
      // buildAttachment(order);
       
       //helper.addAttachment("invoice_"+order.getOid()+".xlsx", new FileSystemResource(path));
       
       helper.addInline("identifier1234", new FileSystemResource("D:\\vivo\\varshini.jpg"));
       
          mailSender.send(message);
    	    return "Mail sent successfully";
		}
      
       catch(Exception e)
       {
    	   e.printStackTrace();
    	   return "Failed to send mail";
       }
       
	}
	
	public String triggerMail(UserInfo info)
	{
		try
		{
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		
		helper.setTo(info.getUserName());
		helper.setSubject("Welcome mail");
		
		/*
		 * Map<String,Object> model=new HashMap<>(); model.put("user", info);
		 */
		
		/*
		 * StringWriter writer=new StringWriter();
		 * 
		 * config.getTemplate("mail-templates/freemarkermail.ftlh").process(model,
		 * writer);
		 * 
		 * 
		 * helper.setText(writer.toString(),true);
		 */
		
		Context context=new Context();
		
		context.setVariable("user", info);
		String text=engine.process("mail-templates/thymeleafmail.html", context);
		
		helper.setText(text, true);
	  mailSender.send(message);
		
		return "mail sent";
		
		}
		
		catch(Exception e)
		{
			return "mail failed to send";
		}
	}
	
	public String buildMailBody(Order o)
	{
		StringBuilder sb=new StringBuilder();
		
		sb.append("OrderSummary");
		sb.append('\n');
		sb.append("Order Id:"+o.getOid());
		sb.append('\n');
		sb.append("Total Items:"+o.getTotalItems());
		sb.append('\n');
		sb.append("Total Bill:"+o.getTotalBill());
		sb.append('\n');
		sb.append("Shipping Address:"+o.getAddress());
		sb.append('\n');
		sb.append("Items:---------------------------");
		
		for(Item item:o.getItems())
		{
			sb.append('\n');
			sb.append("--------------------------------");
			sb.append('\n');
			sb.append("Item Name:"+item.getItemName());
			sb.append('\n');
			sb.append("Item Price:"+item.getItemPrice());
			sb.append('\n');
			sb.append("Item Quantity:"+item.getQuantity());
			sb.append('\n');
			sb.append("For more detailed order info please refer to attached invoice document");
		}
		
		return sb.toString();
	}
	

	
	public void buildAttachment(Order o) 
	{
		 path="D:\\mail_attachments\\invoice_"+o.getOid()+".xlsx";
		try(OutputStream os=new FileOutputStream(path);
				
				XSSFWorkbook workbook=new XSSFWorkbook()) {
		
	
		
		XSSFSheet sheet =workbook.createSheet("Order data");
		Row r=sheet.createRow(0);
		
		Field[] fields=Item.class.getDeclaredFields();
		
		for(int i=0;i<fields.length;i++)
		{
			 r.createCell(i).setCellValue(fields[i].getName());
		}
		
		
		int rowNum=1;
		
		for(Item i:o.getItems())
		{
			r=sheet.createRow(rowNum);
			 fields=i.getClass().getDeclaredFields();
			
			for(int j=0;j<fields.length;j++)
			{
				 r.createCell(j).setCellValue(String.valueOf(fields[j].get(i)));
				 
			}
			rowNum++;
			
			
		}
		workbook.write(os);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
