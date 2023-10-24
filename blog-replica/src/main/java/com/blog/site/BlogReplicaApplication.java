package com.blog.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BlogReplicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogReplicaApplication.class, args);
	}

}
