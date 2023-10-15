package com.blog.site.query.api.controller;

import com.blog.site.core.api.entity.Blog;
import com.blog.site.core.api.service.UserService;
import com.blog.site.query.api.query.*;
import com.commons.dto.BlogDto;
import com.commons.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "http://3.239.98.51","http://54.84.139.0", "*"})
@RestController
@RequestMapping("api/v1.0/blogsite")
@Slf4j
public class BlogQueryController {

    @Autowired
    private UserService userService;


    private QueryGateway queryGateway;
    public BlogQueryController(QueryGateway queryGateway){
        this.queryGateway=queryGateway;
    }


    @GetMapping(value = "/blogs/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Blog>> blog() {

        FindAllBlogs findAllBlogs=new FindAllBlogs();
        List<Blog> blogList = queryGateway.query(findAllBlogs, ResponseTypes.multipleInstancesOf(Blog.class)).join();
        return ResponseEntity.ok(blogList);
    }


    @GetMapping(value = "/blogs/info/category", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> findDistinctCategory() {

        FindDistinctCategory findDistinctCategory=new FindDistinctCategory();
        List<String> distinctCategory = queryGateway.query(findDistinctCategory, ResponseTypes.multipleInstancesOf(String.class)).join();
        return ResponseEntity.status(HttpStatus.OK).body(distinctCategory);


    }

    @GetMapping(value = "/blogs/info/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Blog>> blogByCategory(@Valid @PathVariable String category) {
        FindByCategory findByCategory= FindByCategory.builder()
                .category(category)
                .build();

        List<Blog> blogByCategory = queryGateway.query(findByCategory, ResponseTypes.multipleInstancesOf(Blog.class)).join();
        log.info("Blog by Category category {} count {} ",category,blogByCategory.size());
        return ResponseEntity.status(HttpStatus.OK).body(blogByCategory);

    }

    @GetMapping(value = "/blogs/{startDate}/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Blog>> blogByCategoryTimeRange(@Valid @PathVariable String startDate ,@Valid @PathVariable String endDate) {
        FindByStartAndEndDate findByStartAndEndDate=FindByStartAndEndDate.builder()
                .start(LocalDate.parse(startDate))
                .end(LocalDate.parse(endDate))
                .build();

        List<Blog> result = queryGateway.query(findByStartAndEndDate, ResponseTypes.multipleInstancesOf(Blog.class)).join();
        log.info("Number of blog in date range {} and {} is {}",startDate,endDate,result.size());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @GetMapping(value = "/blogs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BlogDto> blog(@Valid @PathVariable Long id) {
        FindById findById = FindById.builder()
                .id(id)
                .build();
        BlogDto blogDto = queryGateway.query(findById, ResponseTypes.optionalInstanceOf(BlogDto.class)).join().get();
        log.info("Find the blog by id :"+blogDto);
        return ResponseEntity.status(HttpStatus.OK).body(blogDto);
    }
//For testing only
//    @GetMapping("/{id}")
//    public ResponseEntity<UserDto> getUserDto(@PathVariable String id){
//        UserDto userById = userService.getUserById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(userById);
//    }

}
