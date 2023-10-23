package com.blog.site.command.api.controller;

import com.blog.site.core.api.entity.Blog;
import com.blog.site.core.api.repository.BlogRepository;
import com.blog.site.core.api.validation.BlogValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(BlogCommandController.class)
@Slf4j
public class BlogCommandControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private  CommandGateway commandGateway;

    @MockBean
    private  BlogRepository userRepository;

    @MockBean
    private BlogValidation blogValidation;

    @Autowired
    MockMvc mockMvc;

    private String BASE_URL="/api/v1.0/blogsite/blogs";

    private String article="Women account for one of the groups most affected by the disparities in the legal system. Therefore, this sphere " +
            "has been an area of intense interest for feminist researchers and activists. Subsequently, various standpoints are expressed through different means of " +
            "communication, calling for an equal approach to justice for both sexes. Such kinds of activism positively affect society, as they attract the public’s attention " +
            "to a topical issue. Willison and O’Brien (2016) state that the incarceration rate of women in the United States has grown at a quicker pace than a similar number for men." +
            " Accordingly, it is possible to conclude that the country is currently experiencing the phenomenon of mass female incarceration, which reflects the unfair side of the legal system."+
            "Women account for one of the groups most affected by the disparities in the legal system. Therefore, this sphere " +
            "has been an area of intense interest for feminist researchers and activists. Subsequently, various standpoints are expressed through different means of " +
            "communication, calling for an equal approach to justice for both sexes. Such kinds of activism positively affect society, as they attract the public’s attention " +
            "to a topical issue. Willison and O’Brien (2016) state that the incarceration rate of women in the United States has grown at a quicker pace than a similar number for men." +
            " Accordingly, it is possible to conclude that the country is currently experiencing the phenomenon of mass female incarceration, which reflects the unfair side of the legal system."+
            "Women account for one of the groups most affected by the disparities in the legal system. Therefore, this sphere " +
            "has been an area of intense interest for feminist researchers and activists. Subsequently, various standpoints are expressed through different means of " +
            "communication, calling for an equal approach to justice for both sexes. Such kinds of activism positively affect society, as they attract the public’s attention " +
            "to a topical issue. Willison and O’Brien (2016) state that the incarceration rate of women in the United States has grown at a quicker pace than a similar number for men." +
            " Accordingly, it is possible to conclude that the country is currently experiencing the phenomenon of mass female incarceration, which reflects the unfair side of the legal system.";

    private Blog blog=Blog.builder()
            .blogName("This is my blogName")
            .authorName("Myke Tyson")
            .category("Motivation")
            .article(article)
            .userId("10")
            .build();

    @Test
    public  void create_blog_test() throws Exception {
        Blog testBlog=blog;
        Blog savedBlog=blog;
        savedBlog.setId(Long.valueOf(5));
        String json=objectMapper.writeValueAsString(testBlog);
        Mockito.when(userRepository.save(Mockito.any(Blog.class))).thenReturn(savedBlog);
        String contentAsString = mockMvc.perform(post(BASE_URL+"/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(contentAsString.contains("blogName"),true);
        Assertions.assertEquals(contentAsString.contains("id"),true);
        Assertions.assertEquals(contentAsString.contains("Motivation"),true);
        System.out.println("Response :"+contentAsString);
    }


    @Test
    public  void create_blog_invalid_blogName_test() throws Exception {
        Blog testBlog=blog;
        testBlog.setBlogName("blog");
        Blog savedBlog=blog;
        savedBlog.setId(Long.valueOf(5));
        String json=objectMapper.writeValueAsString(testBlog);
        Mockito.when(userRepository.save(Mockito.any(Blog.class))).thenReturn(savedBlog);

            String contentAsString = mockMvc.perform(post(BASE_URL + "/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                    .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
                    .andReturn().getResponse().getContentAsString();
            Assertions.assertEquals(contentAsString.contains("message"), true);

    }

    @Test
    public  void create_blog_invalid_author_test() throws Exception {
        Blog testBlog=blog;
        testBlog.setAuthorName("author");
        Blog savedBlog=blog;
        savedBlog.setId(Long.valueOf(5));
        String json=objectMapper.writeValueAsString(testBlog);
        Mockito.when(userRepository.save(Mockito.any(Blog.class))).thenReturn(savedBlog);
            String contentAsString = mockMvc.perform(post(BASE_URL + "/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                    .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
                    .andReturn().getResponse().getContentAsString();
            Assertions.assertEquals(contentAsString.contains("message"), true);


    }

    @Test
    public  void create_blog_invalid_category_test() throws Exception {
        Blog testBlog = blog;
        testBlog.setCategory("Test");
        Blog savedBlog = blog;
        savedBlog.setId(Long.valueOf(5));
        String json = objectMapper.writeValueAsString(testBlog);
        Mockito.when(userRepository.save(Mockito.any(Blog.class))).thenReturn(savedBlog);
            String contentAsString = mockMvc.perform(post(BASE_URL + "/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                    .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
                    .andReturn().getResponse().getContentAsString();
            log.info("Response : {}", contentAsString);
            Assertions.assertEquals(contentAsString.contains("Blog category"), true);

    }

    @Test
    public  void create_blog_invalid_article_test() throws Exception {
        Blog testBlog=blog;
        testBlog.setArticle("Article");
        Blog savedBlog=blog;
        savedBlog.setId(Long.valueOf(5));
        String json=objectMapper.writeValueAsString(testBlog);
        Mockito.when(userRepository.save(Mockito.any(Blog.class))).thenReturn(savedBlog);
            String contentAsString = mockMvc.perform(post(BASE_URL + "/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                    .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
                    .andReturn().getResponse().getContentAsString();
            Assertions.assertEquals(contentAsString.contains("message"), true);
            Assertions.assertEquals(contentAsString.contains("200"), true);
    }

    @Test
    public  void create_blog_BadRequest_test() throws Exception {
        Blog testBlog=blog;
        Blog savedBlog=blog;
        testBlog.setBlogName("");
        savedBlog.setId(Long.valueOf(5));
        String json=objectMapper.writeValueAsString(testBlog);
        Mockito.when(userRepository.save(Mockito.any(Blog.class))).thenReturn(savedBlog);
        String contentAsString = mockMvc.perform(post(BASE_URL+"/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();
        System.out.println("Response :"+contentAsString);
    }

    @Test
    public  void delete_blog_test() throws Exception {
        String contentAsString = mockMvc.perform(delete(BASE_URL+"/delete/{id}",Long.valueOf(3)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(contentAsString.contains("Successfully deleted the Blog with id"),true);
        Assertions.assertEquals(contentAsString.contains("3"),true);
        log.info("Response :"+contentAsString);
    }


}
