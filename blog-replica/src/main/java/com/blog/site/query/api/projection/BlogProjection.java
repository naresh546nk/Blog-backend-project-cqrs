package com.blog.site.query.api.projection;

import com.blog.site.core.api.entity.Blog;
import com.blog.site.core.api.repository.BlogRepository;
import com.blog.site.core.api.service.BlogMongoTempletService;
import com.blog.site.core.api.service.UserService;
import com.blog.site.query.api.query.*;
import com.commons.dto.BlogDto;
import com.commons.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BlogProjection {

    @Autowired
    private BlogRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private BlogMongoTempletService service;

    @QueryHandler
    public List<Blog> handle(FindAllBlogs findAllBlogs){
        log.info("Fetching all the Blogs");
         return repository.findAll();
    }
    @QueryHandler
    public List<String> handle(FindDistinctCategory distinctCategory){
        log.info("Fetching all Category ");
        return service.findDistinctCategory();
    }
    @QueryHandler
    public List<Blog> handle(FindByCategory findByCategory){
        log.info("Fetching by Category ");
        List<Blog> byCategory = repository.findByCategory(findByCategory.getCategory());
        return byCategory;
    }
    @QueryHandler
    public List<Blog> handle(FindByStartAndEndDate findByStartAndEndDate){
        log.info("Fetching by start and end data ");
        return service.findByStartAndEndDate(findByStartAndEndDate.getStart(),findByStartAndEndDate.getEnd());
    }

    @QueryHandler
    public BlogDto handle(FindById findById){
        log.info("Fetching Blog by Id ");

        Blog blog= repository.findById(findById.getId()).get();
        UserDto userById = userService.getUserById(blog.getUserId());

        BlogDto blogDto=BlogDto.builder()
                .id(blog.getId())
                .blogName(blog.getBlogName())
                .article(blog.getArticle())
                .category(blog.getCategory())
                .authorName(blog.getAuthorName())
                .createdOn(blog.getCreatedOn())
                .userDto(userById)
                .build();
        return blogDto;

    }

}
