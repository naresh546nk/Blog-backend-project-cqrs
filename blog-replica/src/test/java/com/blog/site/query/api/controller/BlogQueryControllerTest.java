package com.blog.site.query.api.controller;

import com.blog.site.core.api.entity.Blog;
import com.blog.site.core.api.service.BlogQueryService;
import com.blog.site.query.api.query.FindAllBlogs;
import com.commons.dto.BlogDto;
import com.commons.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BlogQueryController.class)
@Slf4j
public class BlogQueryControllerTest {

    private  String BASE_URL="/api/v1.0/blogsite/blogs";

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BlogQueryService blogQueryService;


    @Autowired
    MockMvc mockMvc;


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
    public  void getAllBlogs_test() throws Exception {
        Blog testBlog=blog;
        Blog savedBlog=blog;
        savedBlog.setId(Long.valueOf(5));
        FindAllBlogs findAllBlogs=new FindAllBlogs();
        Mockito.when(blogQueryService.findAllBlogs(Mockito.any())).thenReturn(List.of(savedBlog));
        String contentAsString = mockMvc.perform(get(BASE_URL+"/getall")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(contentAsString.contains("blogName"),true);
        Assertions.assertEquals(contentAsString.contains("id"),true);
        Assertions.assertEquals(contentAsString.contains("Motivation"),true);
        log.info("Response :"+contentAsString);
    }

    @Test
    public  void findDistinctCategory_test() throws Exception {
        Blog testBlog=blog;
        Mockito.when(blogQueryService.findDistinctCategory(Mockito.any())).thenReturn(List.of("Motivation"));
        String contentAsString = mockMvc.perform(get(BASE_URL+"/info/category")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertNotNull(contentAsString);
        Assertions.assertEquals(contentAsString.contains("Motivation"),true);
        log.info("Response :"+contentAsString);
    }

    @Test
    public  void blogByCategory_test() throws Exception {
        Blog testBlog=blog;
        Blog savedBlog=blog;
        Mockito.when(blogQueryService.findByCategory(Mockito.any())).thenReturn(List.of(savedBlog));
        String contentAsString = mockMvc.perform(get(BASE_URL+"/info/{category}","Motivation")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertNotNull(contentAsString);
        Assertions.assertEquals(contentAsString.contains("blogName"),true);
        Assertions.assertEquals(contentAsString.contains("id"),true);
        Assertions.assertEquals(contentAsString.contains("Motivation"),true);
        log.info("Response :"+contentAsString);
    }


    @Test
    public  void blogByTimeRange_test() throws Exception {
        Blog testBlog=blog;
        Blog savedBlog=blog;
        Mockito.when(blogQueryService.findByStartAndEndDate(Mockito.any())).thenReturn(List.of(savedBlog));
        String contentAsString = mockMvc.perform(get(BASE_URL+"/{startDate}/{endDate}", "2023-09-06","2023-11-10")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertNotNull(contentAsString);
        Assertions.assertEquals(contentAsString.contains("blogName"),true);
        Assertions.assertEquals(contentAsString.contains("id"),true);
        Assertions.assertEquals(contentAsString.contains("Motivation"),true);
        log.info("Response :"+contentAsString);
    }


    @Test
    public  void blogById_test() throws Exception {
        Blog testBlog=blog;
        UserDto userDto= UserDto.builder()
                .id(Long.valueOf(5))
                .name("Naresh Kumar")
                .authority("ADMIN")
                .username("naresh@gmail.com")
                .build();
        BlogDto blogDto=new BlogDto();
        BeanUtils.copyProperties(blog,blogDto);

        blogDto.setId(Long.valueOf(1));
        blogDto.setUserDto(userDto);

        Mockito.when(blogQueryService.findById(Mockito.any())).thenReturn(blogDto);
        String contentAsString = mockMvc.perform(get(BASE_URL+"/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertNotNull(contentAsString);
        Assertions.assertEquals(contentAsString.contains("blogName"),true);
        Assertions.assertEquals(contentAsString.contains("id"),true);
        Assertions.assertEquals(contentAsString.contains("Motivation"),true);
        log.info("Response :"+contentAsString);
    }



}
