package com.blog.site.query.api.service;

import com.blog.site.core.api.entity.Blog;
import com.blog.site.core.api.service.BlogQueryService;
import com.blog.site.query.api.query.*;
import com.commons.dto.BlogDto;
import com.commons.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BlogQueryService.class, QueryGateway.class})
@Slf4j
public class BlogQueryServiceTest {

    @Autowired
    private  BlogQueryService blogQueryService;

    @MockBean
    private  QueryGateway queryGateway;

    private Blog blog=Blog.builder()
            .blogName("This is my blogName")
            .authorName("Myke Tyson")
            .category("Motivation")
            .article("article")
            .userId("10")
            .build();

    @Test
    public  void findAllBlogs_test(){
        FindAllBlogs allBlogs=new FindAllBlogs();
        CompletableFuture<List<Blog>> feature = CompletableFuture.supplyAsync(() -> List.of(blog));
        Mockito.when(queryGateway.query(allBlogs, ResponseTypes.multipleInstancesOf(Blog.class))).thenReturn(feature);
        List<Blog> result = blogQueryService.findAllBlogs(allBlogs);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(),1);
    }

    @Test
    public  void findDistinctCategory(){
        FindDistinctCategory findDistinctCategory=new FindDistinctCategory();
        CompletableFuture<List<String>> feature = CompletableFuture.supplyAsync(() -> List.of("Motivation"));
        Mockito.when(queryGateway.query(findDistinctCategory, ResponseTypes.multipleInstancesOf(String.class))).thenReturn(feature);
        List<String> result = blogQueryService.findDistinctCategory(findDistinctCategory);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(),1);
    }

    @Test
    public  void findByCategory_test(){
        FindByCategory findByCategory=FindByCategory.builder()
                .category("Motivation")
                .build();
        CompletableFuture<List<Blog>> feature = CompletableFuture.supplyAsync(() -> List.of(blog));
        Mockito.when(queryGateway.query(findByCategory, ResponseTypes.multipleInstancesOf(Blog.class))).thenReturn(feature);
        List<Blog> result = blogQueryService.findByCategory(findByCategory);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(),1);
    }

    @Test
    public  void findByStartAndEndDate_test(){
        FindByCategoryStartAndEndDate findByStartAndEndDate= FindByCategoryStartAndEndDate.builder()
                .category("Motivation")
                .start(LocalDate.now())
                .end(LocalDate.now())
                .build();
        CompletableFuture<List<Blog>> feature = CompletableFuture.supplyAsync(() -> List.of(blog));
        Mockito.when(queryGateway.query(findByStartAndEndDate, ResponseTypes.multipleInstancesOf(Blog.class))).thenReturn(feature);
        List<Blog> result = blogQueryService.findByStartAndEndDate(findByStartAndEndDate);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(),1);
    }

    @Test
    public  void findById_test(){
        BlogDto blogDto=new BlogDto();
        BeanUtils.copyProperties(blog,blogDto);
        UserDto userDto= UserDto.builder()
                .id(Long.valueOf(10))
                .username("naresh@gmail.com")
                .build();
        blogDto.setUserDto(userDto);
        FindById findById=FindById.builder()
                .id(Long.valueOf(4))
                .build();
        CompletableFuture<BlogDto> feature = CompletableFuture.supplyAsync(() -> blogDto);
        Mockito.when(queryGateway.query(findById, ResponseTypes.instanceOf(BlogDto.class))).thenReturn(feature);
        BlogDto result = blogQueryService.findById(findById);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getUserDto().getUsername(),"naresh@gmail.com");

    }








}
