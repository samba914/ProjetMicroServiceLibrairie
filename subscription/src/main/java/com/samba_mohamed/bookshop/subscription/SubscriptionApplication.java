package com.samba_mohamed.bookshop.subscription;

import com.samba_mohamed.bookshop.subscription.cache.RedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RedisConfig.class)
public class SubscriptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionApplication.class, args);
	}

}
