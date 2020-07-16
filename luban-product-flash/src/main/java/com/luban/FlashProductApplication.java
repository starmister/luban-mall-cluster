package com.luban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication/*(exclude = GlobalTransactionAutoConfiguration.class)*/
@EnableDiscoveryClient
public class FlashProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlashProductApplication.class, args);
	}

}
