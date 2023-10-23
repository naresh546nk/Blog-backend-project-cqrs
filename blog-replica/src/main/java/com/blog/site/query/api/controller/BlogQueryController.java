package com.blog.site.query.api.controller;

import com.blog.site.core.api.entity.Blog;
import com.blog.site.core.api.service.BlogQueryService;
import com.blog.site.query.api.query.*;
import com.commons.dto.BlogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://3.239.98.51","http://54.84.139.0", "*"})
@RestController
@RequestMapping("api/v1.0/blogsite")
@Slf4j
public class BlogQueryController {

    @Autowired
    private BlogQueryService blogQueryService;


    @GetMapping(value = "/blogs/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Blog>> getAllBlogs() {
        FindAllBlogs findAllBlogs=new FindAllBlogs();
        List<Blog> join = blogQueryService.findAllBlogs(findAllBlogs);
        return ResponseEntity.ok(join);
    }


    @GetMapping(value = "/blogs/info/category", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> findDistinctCategory() {

        FindDistinctCategory findDistinctCategory=new FindDistinctCategory();
        List<String> distinctCategory = blogQueryService.findDistinctCategory(findDistinctCategory);
        return ResponseEntity.status(HttpStatus.OK).body(distinctCategory);


    }

    @GetMapping(value = "/blogs/info/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Blog>> blogByCategory(@Valid @PathVariable String category) {
        FindByCategory findByCategory= FindByCategory.builder()
                .category(category)
                .build();

        List<Blog> blogByCategory = blogQueryService.findByCategory(findByCategory);
        log.info("Blog by Category category {} count {} ",category,blogByCategory.size());
        return ResponseEntity.status(HttpStatus.OK).body(blogByCategory);

    }

    @GetMapping(value = "/blogs/info/{category}/{startDate}/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Blog>> blogByCategoryDateRange(@PathVariable String category, @PathVariable String startDate ,@Valid @PathVariable String endDate) {
        FindByCategoryStartAndEndDate findByStartAndEndDate=FindByCategoryStartAndEndDate.builder()
                .category(category)
                .start(LocalDate.parse(startDate))
                .end(LocalDate.parse(endDate))
                .build();

        List<Blog> result = blogQueryService.findByStartAndEndDate(findByStartAndEndDate);
        log.info("Number of blog in date range {} and {} is {}",startDate,endDate,result.size());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @GetMapping(value = "/blogs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BlogDto> blogById(@Valid @PathVariable Long id) {
        FindById findById = FindById.builder()
                .id(id)
                .build();
        BlogDto blogDto = blogQueryService.findById(findById);
        log.info("Find the blog by id :"+blogDto);
        return ResponseEntity.status(HttpStatus.OK).body(blogDto);
    }

}
