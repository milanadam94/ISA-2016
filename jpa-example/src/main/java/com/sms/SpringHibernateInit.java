package com.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringHibernateInit {

	public static void main(String[] args) {
		SpringApplication.run(SpringHibernateInit.class, args);
	}
}
