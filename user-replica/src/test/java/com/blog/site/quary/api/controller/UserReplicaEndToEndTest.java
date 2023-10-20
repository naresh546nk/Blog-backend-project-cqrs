package com.blog.site.quary.api.controller;

import com.blog.site.core.api.entity.BlogUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Slf4j
public class UserReplicaEndToEndTest {

    @Autowired
    private QueryGateway queryGateway;

    @Autowired

    private CommandGateway commandGateway;

    @Autowired
    TestRestTemplate testRestTemplate;


    @Autowired
    ObjectMapper objectMapper;
    private String BASE_URL="/api/v1.0/blogsite/users";

    private BlogUser blogUser=BlogUser.builder()
            .id("1")
            .name("Naresh Kumar")
            .authority("ADMIN")
            .username("naresh546.nk@gmail.com")
            .build();


    @Test
    public  void getAllUsers_test(){
        ResponseEntity<List> result = testRestTemplate.getForEntity(BASE_URL + "/getall", List.class);
        Assertions.assertNotNull(result);
        log.info("Result of EndToEnd testing : {}",result.getBody());
    }
    @Test
    public  void getByUserName_test(){
        ResponseEntity<BlogUser> result = testRestTemplate.getForEntity(BASE_URL + "/getall/username/kajal.nk@gmail.com",BlogUser.class );
        Assertions.assertNotNull(result);
        log.info("Result of EndToEnd testing : {}",result.getBody());
    }

    @Test
    public  void getUserById_test(){
        ResponseEntity<BlogUser> result = testRestTemplate.getForEntity(BASE_URL + "/getall/1",BlogUser.class );
        Assertions.assertNotNull(result);
        log.info("Result of EndToEnd testing : {}",result.getBody());
    }


}
