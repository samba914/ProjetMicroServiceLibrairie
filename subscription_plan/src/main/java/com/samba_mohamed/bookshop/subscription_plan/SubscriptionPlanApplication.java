package com.samba_mohamed.bookshop.subscription_plan;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDiscoveryClient
@SpringBootApplication
public class SubscriptionPlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionPlanApplication.class, args);
	}

}
