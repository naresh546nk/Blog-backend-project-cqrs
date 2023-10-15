package com.blog.site.command.api.controller;

import com.blog.site.core.api.entity.Blog;
import com.blog.site.core.api.exception.ValidationException;
import com.blog.site.core.api.repository.BlogRepository;
import com.blog.site.core.api.validation.BlogValidation;
import com.commons.commands.CreateBlogCommand;
import com.commons.commands.DeleteBlogCommand;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000", "http://3.239.98.51","http://54.84.139.0", "*"})
@RestController
@RequestMapping("api/v1.0/blogsite")
@Slf4j
public class BlogCommandController {

    @Autowired
    private BlogRepository repository;
    private CommandGateway commandGateway;

    public BlogCommandController(CommandGateway commandGateway){
        this.commandGateway=commandGateway;
    }



    @PostMapping(value = "/blogs/add")
    public ResponseEntity<Blog> createBlog(@Valid @RequestBody  Blog blog) throws ValidationException {
        BlogValidation.isValidUser(blog);
        Blog savedBlog = repository.save(blog);
        CreateBlogCommand createBlogCommand= CreateBlogCommand.builder()
                .uuid(UUID.randomUUID().toString())
                .id(savedBlog.getId())
                .blogName(blog.getBlogName())
                .article(blog.getArticle())
                .authorName(blog.getAuthorName())
                .category(blog.getCategory())
                .createdOn(LocalDate.now())
                .userId(blog.getUserId())
                .build();
        String uuid = commandGateway.sendAndWait(createBlogCommand);
        log.info("Command Created and Sent to commandGateway :"+uuid);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBlog);
    }

    @DeleteMapping(value = "/blogs/delete/{id}")
    public ResponseEntity<String> deleteBlog(@Valid @PathVariable Long id) {
        repository.deleteById(id);
        DeleteBlogCommand deleteBlogCommand=DeleteBlogCommand.builder()
                .uuid(UUID.randomUUID().toString())
                .id(id)
                .build();
        String uuid= commandGateway.sendAndWait(deleteBlogCommand);
        log.info("Blog deletion event sent with confirmation id {}",uuid);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted the Blog with id: "+id);
    }

}
