package com.blog.site.core.api.service;

import com.blog.site.core.api.entity.Blog;
import com.blog.site.query.api.query.*;
import com.commons.dto.BlogDto;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class BlogQueryService {

    @Autowired
    private QueryGateway queryGateway;

    public List<Blog> findAllBlogs(FindAllBlogs findAllBlogs) {
        CompletableFuture<List<Blog>> query = queryGateway.query(findAllBlogs, ResponseTypes.multipleInstancesOf(Blog.class));
        List<Blog> join = query.join();
        return join;
    }

    public List<String> findDistinctCategory(FindDistinctCategory findDistinctCategory) {
        CompletableFuture<List<String>> query = queryGateway.query(findDistinctCategory, ResponseTypes.multipleInstancesOf(String.class));
        List<String> join = query.join();
        return join;
    }
    public List<Blog> findByCategory(FindByCategory findByCategory) {
        CompletableFuture<List<Blog>> query = queryGateway.query(findByCategory, ResponseTypes.multipleInstancesOf(Blog.class));
        List<Blog> blogByCategory = query.join();
        return blogByCategory;
    }
    public List<Blog> findByStartAndEndDate(FindByStartAndEndDate startAndEndDate) {
        CompletableFuture<List<Blog>> query = queryGateway.query(startAndEndDate, ResponseTypes.multipleInstancesOf(Blog.class));
        List<Blog> join = query.join();
        return join;
    }
    public BlogDto findById(FindById findById){
        CompletableFuture<BlogDto> query = queryGateway.query(findById, ResponseTypes.instanceOf(BlogDto.class));
        BlogDto blogDto = query.join();
        return  blogDto;
    }

}
