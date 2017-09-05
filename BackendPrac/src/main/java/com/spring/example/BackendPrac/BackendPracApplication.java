package com.spring.example.BackendPrac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.example.BackendPrac.dao.ProductDAOImpl;

@SpringBootApplication
public class BackendPracApplication implements CommandLineRunner{

	@Autowired
	ProductDAOImpl dao;
	
	public static void main(String[] args) {
		SpringApplication.run(BackendPracApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("^^^^^^^^^^^^"+dao.getProductById(1000).getProduct_name()+" Rs "+dao.getProductById(1000).getPrice());
		
	}
	
}
