package com.blog.site.command.api.controller;

import com.blog.site.core.api.entity.BlogUser;
import com.blog.site.core.api.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Slf4j
public class UserEndToEndTest {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;
    private String BASE_URL="/api/v1.0/blogsite/users";

    private BlogUser blogUser=BlogUser.builder()
            .name("Test name")
            .authority("USER")
            .username("test@gmail.com")
            .build();


    @Test
    public  void registerUser_test() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<BlogUser> request = new HttpEntity<>(blogUser, headers);
        //String json=objectMapper.writeValueAsString(user);
        ResponseEntity<BlogUser> result = testRestTemplate.postForEntity(BASE_URL + "/add", request, BlogUser.class);
        Long id = result.getBody().getId();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(id>=1,true);
        log.info("Result of EndToEnd testing : {}",result.getBody());
        testRestTemplate.delete(BASE_URL + "/delete/{id}",id);
    }

}
