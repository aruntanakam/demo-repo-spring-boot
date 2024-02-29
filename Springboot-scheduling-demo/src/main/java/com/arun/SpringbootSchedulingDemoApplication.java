package com.arun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication(scanBasePackages = {"com.arun","com.kumar"} )
@EnableScheduling
public class SpringbootSchedulingDemoApplication {
	
	public static  char[][] printMatrix(int m,int n,char[] targets,Integer[] columnIndices,int[] rowPointers)
	{
		char[][] c=new char[m][n];
		for(int i=0;i<m;i++)
		{
			Arrays.fill(c[i], 'z');
		}
		int row=0;
		
		for(int i=0;i<rowPointers.length-1;i++)
		{
			int a=rowPointers[i];
			int b=rowPointers[i+1];
			
			if((b-a)==0)
			{
				row++;
				continue;
			}
			else
			{
			    
				int k=a;
				while(k<b)
				{
				   int col=columnIndices[k];
				   c[row][col]=targets[k];
				   k++;
				}
				row++;
				
			}
			
		}
		
	return c;	
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		//ApplicationContext ctx=SpringApplication.run(SpringbootSchedulingDemoApplication.class, args);
		
		
		
		/* System.out.println("Application started at:"+new Date()); */
		
		/*
		 * Timer timer=new Timer(); TimerTask task= new Task1();
		 * 
		 * //timer.schedule(task, 2000L, 3000L); timer.schedule(new Task1(),new
		 * Date(123, 7, 30, 22, 44, 0));
		 */
		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int m=Integer.parseInt(br.readLine());
		int n=Integer.parseInt(br.readLine());
		
		char[] targets=br.readLine().replaceAll(" ", "").toCharArray();
		
		String[] s=br.readLine().split(" ");
		
		Integer[] col=new Integer[s.length];
		
		Arrays.asList(s).stream().map(Integer::parseInt).collect(Collectors.toList()).toArray(col);
 s=br.readLine().split(" ");
		
		int[] row=new int[s.length];
		for(int i=0;i<s.length;i++)
		{
			row[i]=Integer.parseInt(s[i]);
		}
		
		System.out.println(Arrays.toString(targets));
		System.out.println(Arrays.toString(col));
		System.out.println(Arrays.toString(row));
		
		char[][] c=printMatrix(m, n, targets, col, row);
		
		for(int i=0;i<c.length;i++)
		{
			System.out.println(Arrays.toString(c[i]));
		}
		
	}

}
