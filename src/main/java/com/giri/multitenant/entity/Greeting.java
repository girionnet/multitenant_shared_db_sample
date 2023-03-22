package com.giri.multitenant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.TenantId;
@Entity
@Table(name = "greeting")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Greeting {

	 
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
	    @Column(name = "name")
	    private String name;
	    
	    @TenantId
	    private String tenantId;
	 
	    @Column(name = "message")
	    private String message;
	 
	    // getters and setters omitted for brevity
	

}
