package com.example.demo;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;




@Controller
public class EmpController {

	@Autowired
	EmpRepository repo;
	
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
	@RequestMapping("/login")
	public String Login()
	{
		
		return "Login";
		
	}
	@RequestMapping("/register")
	public String Register()
	{
		
		return "Register";
		
	}
	@RequestMapping("/technical")
	public String Technical()
	{
		
		return "Technical";
		
	}
	@RequestMapping("/behavioural")
	public String Behavioural()
	{
		
		return "Behavioural";
		
	}
	
	@RequestMapping(value="/tech-register", method=RequestMethod.POST)
	@ResponseBody
    public String Tech_Register(Assessment testObj){
		
		testObj.setType("Technical");
		repo.save(testObj);
		return "<html><body>Registered Successfully<br><a href='/'>Main Page!..</a></body></html>";
		//return "redirect:/";
        //return "login";
		
    }
	@RequestMapping(value="/beh-register", method=RequestMethod.POST)
	@ResponseBody
    public String Beh_Register(Assessment testObj){
		
		testObj.setType("Behavioural");
		repo.save(testObj);
		return "<html><body>Registered Successfully<br><a href='/'>Main Page!..</a></body></html>";
		//return "redirect:/";
        //return "login";
    }
	
	@RequestMapping(value="/assessments", method=RequestMethod.GET)
	@ResponseBody
    public Iterable<Assessment> Assessments(){
		
//		try {                // to test fallbackmethod in Adminservice
//			TimeUnit.MINUTES.sleep(1);
//		    } 		
//		catch (InterruptedException e) {
//			e.printStackTrace();
//		  }
		
		return repo.findAll();
    }
	
}
