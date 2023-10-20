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

//    private String article="Women account for one of the groups most affected by the disparities in the legal system. Therefore, this sphere " +
//            "has been an area of intense interest for feminist researchers and activists. Subsequently, various standpoints are expressed through different means of " +
//            "communication, calling for an equal approach to justice for both sexes. Such kinds of activism positively affect society, as they attract the public’s attention " +
//            "to a topical issue. Willison and O’Brien (2016) state that the incarceration rate of women in the United States has grown at a quicker pace than a similar number for men." +
//            " Accordingly, it is possible to conclude that the country is currently experiencing the phenomenon of mass female incarceration, which reflects the unfair side of the legal system."+
//            "Women account for one of the groups most affected by the disparities in the legal system. Therefore, this sphere " +
//            "has been an area of intense interest for feminist researchers and activists. Subsequently, various standpoints are expressed through different means of " +
//            "communication, calling for an equal approach to justice for both sexes. Such kinds of activism positively affect society, as they attract the public’s attention " +
//            "to a topical issue. Willison and O’Brien (2016) state that the incarceration rate of women in the United States has grown at a quicker pace than a similar number for men." +
//            " Accordingly, it is possible to conclude that the country is currently experiencing the phenomenon of mass female incarceration, which reflects the unfair side of the legal system."+
//            "Women account for one of the groups most affected by the disparities in the legal system. Therefore, this sphere " +
//            "has been an area of intense interest for feminist researchers and activists. Subsequently, various standpoints are expressed through different means of " +
//            "communication, calling for an equal approach to justice for both sexes. Such kinds of activism positively affect society, as they attract the public’s attention " +
//            "to a topical issue. Willison and O’Brien (2016) state that the incarceration rate of women in the United States has grown at a quicker pace than a similar number for men." +
//            " Accordingly, it is possible to conclude that the country is currently experiencing the phenomenon of mass female incarceration, which reflects the unfair side of the legal system.";
//
//    private Blog blog=Blog.builder()
//            .blogName("This is my blogName")
//            .authorName("Myke Tyson")
//            .category("Motivation")
//            .article(article)
//            .userId("10")
//            .build();

    @Test
    public  void createBlog_test() throws Exception {
        ResponseEntity<Object> result = testRestTemplate.getForEntity(BASE_URL + "/getall", Object.class);
        Assertions.assertNotNull(result);
        log.info("Result of EndToEnd testing : {}",result.getBody());
    }

    @Test
    public  void findDistinctCategory_test() throws Exception {
        ResponseEntity<Object> result = testRestTemplate.getForEntity(BASE_URL + "/info/category", Object.class);
        Assertions.assertNotNull(result);
        log.info("Result of EndToEnd testing : {}",result.getBody());
    }

    @Test
    public  void blogByTimeRange_test() throws Exception {
        ResponseEntity<Object> result = testRestTemplate.getForEntity(BASE_URL + "/2023/01/01/2023/12/12", Object.class);
        Assertions.assertNotNull(result);
        log.info("Result of EndToEnd testing : {}",result.getBody());
    }

    @Test
    public  void blogById_test() throws Exception {
        ResponseEntity<BlogDto> result = testRestTemplate.getForEntity(BASE_URL + "/1", BlogDto.class);
        Assertions.assertNotNull(result);
        log.info("Result of EndToEnd testing : {}",result.getBody());
    }





}
