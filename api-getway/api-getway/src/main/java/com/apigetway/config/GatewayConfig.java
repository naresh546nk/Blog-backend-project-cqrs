package com.apigetway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@Slf4j
public class GatewayConfig {
	@Bean
	public RouteLocator getRouterLocator(RouteLocatorBuilder builder) {
        log.info("Find the route to redirect the traffic : {}",builder.routes());
		return builder.routes()
				.route(p->p.path("/api/v1.0/blogsite/blogs/add","/api/v1.0/blogsite/blogs/delete/**")
						.uri("lb://blog"))
				.route(p->p.path("/api/v1.0/blogsite/blogs/**")
						.uri("lb://blog-replica"))
				.route(p->p.path("/api/v1.0/blogsite/users/add","/api/v1.0/blogsite/users/delete/**")
						.uri("lb://user"))
				.route(p->p.path("/api/v1.0/blogsite/users/**")
						.uri("lb://user-replica"))
				.build();
	}

}
