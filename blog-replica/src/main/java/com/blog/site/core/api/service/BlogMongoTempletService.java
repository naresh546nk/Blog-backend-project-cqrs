package com.blog.site.core.api.service;

import com.blog.site.core.api.entity.Blog;
import com.blog.site.core.api.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogMongoTempletService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<String> findDistinctCategory(){
        List<String> categories = mongoTemplate.findDistinct("category", Blog.class, String.class);
        return categories;

    }
    public List<Blog> findByStartAndEndDate(LocalDate start, LocalDate end) {
        Query query=new Query();
        query.addCriteria(
                new Criteria().andOperator(
                        Criteria.where("createdOn").gte(start),
                        Criteria.where("createdOn").lte(end)
                )

        );
        List<Blog> blogs = mongoTemplate.find(query, Blog.class);

        return blogs;
    }
}
