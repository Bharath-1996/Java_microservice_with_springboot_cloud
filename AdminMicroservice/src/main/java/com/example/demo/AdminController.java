package com.example.demo;

import java.io.IOException;
import java.net.URI;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Http.HttpMethod;

import freemarker.template.utility.ObjectConstructor;




@Controller
public class AdminController {

		
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private LoadBalancerClient loadbalancerclient;
	
	@RequestMapping("/")
	public String indexpage()
	{
		
		return "index";
		
	}
	@HystrixCommand(fallbackMethod = "defaultassessments",
			commandProperties = {
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="5"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000"),
					
			})
	
	@RequestMapping("/assessments")
	public String assessments(Model model) throws JsonMappingException, JsonProcessingException
	{
		String service="Emp-service";
		
		//List<ServiceInstance> instances=discoveryClient.getInstances(service);
		//URI uri=instances.get(0).getUri();
		
		//using loadbalancerclient to choose instances
		ServiceInstance instance=loadbalancerclient.choose(service);
		URI uri=URI.create(String.format("http://%s:%s", instance.getHost(),instance.getPort()));
		
		String url=uri.toString()+"/assessments";
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(url, Object[].class);
		Object[] objects = responseEntity.getBody();
		
		if(responseEntity.getStatusCode()==HttpStatus.OK)
		{
			model.addAttribute("list",objects);
			
		    return "List";
		}
		else
			return "invalid";	
	}
	
	@ResponseBody
	public String defaultassessments(Model model) throws JsonMappingException, JsonProcessingException
	{
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);
		Assessment obj1=new Assessment((long) 1,"default","default",date,"default");

		model.addAttribute("list",obj1);
		
	    return "List";
		
	}
	
}
