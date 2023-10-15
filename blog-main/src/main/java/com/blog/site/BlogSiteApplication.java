package com.blog.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BlogSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogSiteApplication.class, args);
	}

}
