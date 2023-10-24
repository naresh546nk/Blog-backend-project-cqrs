package com.blog.site.query.api.controller;

import com.blog.site.core.api.entity.Blog;
import com.blog.site.core.api.repository.BlogRepository;
import com.commons.dto.BlogDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Slf4j
public class BlogReplicaEndToEndTest {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    ObjectMapper objectMapper;
    private String BASE_URL="/api/v1.0/blogsite/blogs";


    @Test
    public  void getAllBlogs_test() throws Exception {
        ResponseEntity<List> result = testRestTemplate.getForEntity(BASE_URL + "/getall", List.class);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getBody().size()>=1,true);
        log.info("Result of EndToEnd AllBlogs: {}",result.getBody());
    }


    @Test
    public  void findDistinctCategory_test() throws Exception {
        ResponseEntity<List> result = testRestTemplate.getForEntity(BASE_URL + "/info/category", List.class);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getBody().size()>=1,true);
        log.info("Result of EndToEnd findDistinctCategory : {}",result.getBody());
    }

    @Test
    public  void blogByCategory_test() throws Exception {
        ResponseEntity<List> result = testRestTemplate.getForEntity(BASE_URL + "/info/Motivation", List.class);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getBody().size()>=1,true);
        log.info("Result of EndToEnd blog By Category : {}",result.getBody());
    }

    @Test
    public  void blogsByCategoryDateRange_test() throws Exception {
        ResponseEntity<List> result = testRestTemplate.getForEntity(BASE_URL + "/info/Motivation/2023-01-01/2023-12-12", List.class);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getBody().size()>=1,true);
        log.info("Result of EndToEnd blogsByCategoryDateRange : {}",result.getBody());
    }

    @Test
    public  void blogById_test() throws Exception {
        ResponseEntity<BlogDto> result = testRestTemplate.getForEntity(BASE_URL + "/4", BlogDto.class);
        Assertions.assertNotNull(result);
        log.info("Result of EndToEnd blogById : {}",result.getBody());
    }





}
