package com.giri.multitenant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "greeting")
@Data
public class Greeting {

	 
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
	    @Column(name = "name")
	    private String name;
	    
	    @Column(name = "tenant_id")
	    private String tenantId;
	 
	    @Column(name = "message")
	    private String message;
	 
	    // getters and setters omitted for brevity
	

}
