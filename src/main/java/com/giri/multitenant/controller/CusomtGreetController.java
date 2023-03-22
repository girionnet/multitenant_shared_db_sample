package com.giri.multitenant.controller;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.giri.multitenant.entity.Greeting;
import com.giri.multitenant.multitenant.TenantContext;
import com.giri.multitenant.repository.GreetingRepository;

@RestController
@RequestMapping("/api")
public class CusomtGreetController {
	

    @Autowired
    private GreetingRepository greetingRepository;
  

    @GetMapping("/data")
    public String getData(@RequestParam String name) {
    	String currentTenant = TenantContext.getCurrentTenant();
       // return myService.getDataForCurrentTenant();
    	//ArrayList al = new ArrayList(); 
    	//al.add("Giri"); 
    	System.out.println("currentTenant :"+ currentTenant);
    	return greetingRepository.findByName(name);
    	
    	//return al; 
    }


}
