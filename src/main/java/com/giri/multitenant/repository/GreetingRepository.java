package com.giri.multitenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.giri.multitenant.entity.Greeting;

public interface GreetingRepository extends JpaRepository<Greeting, Long> {

	//tenant id is implicitly provided by Hibernate
	@Query("select message from Greeting g where  g.name = ?1")
	String findByName(String name);

	//@Query("select message from Greeting g where g.tenantId = ?1 and g.name = ?2")
	//String findByName(String tenantId, String name);
}
