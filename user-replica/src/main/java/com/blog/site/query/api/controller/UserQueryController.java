package com.blog.site.query.api.controller;

import com.blog.site.core.api.entity.BlogUser;
import com.blog.site.query.api.query.FindAllUsers;
import com.blog.site.query.api.query.FindUserById;
import com.blog.site.query.api.query.FindUserByUsername;
import com.blog.site.query.api.service.UserQueryService;
import com.commons.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;


@CrossOrigin(origins = {"http://localhost:3000",  "*"})
@RestController
@RequestMapping("api/v1.0/blogsite/users")
@Slf4j
public class UserQueryController {

    @Autowired
    private UserQueryService service;



    @GetMapping("/username/{username}")
    public  ResponseEntity<BlogUser> getUserByUsername (@Valid @PathVariable String username) throws ExecutionException, InterruptedException {
        FindUserByUsername findUserByUsername=FindUserByUsername.builder().username(username).build();
        BlogUser blogUser = service.findUserByUsername(findUserByUsername);
        log.info("blog user :"+blogUser);
        return  ResponseEntity.status(HttpStatus.OK).body(blogUser);

    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto>  getUserById(@Valid @PathVariable Long id) throws ExecutionException, InterruptedException {
        FindUserById findUserById= FindUserById.builder().id(id.toString()).build();
        log.info("getUserByID ");
        UserDto userDto = service.findUserById(findUserById);

        log.info("Blog User :"+userDto);
        return  ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<BlogUser>> getAllBlogUser(){
        FindAllUsers findAllUsers=new FindAllUsers();
        log.info("All User list ");
        List<BlogUser> blogUserList = service.findAllUser(findAllUsers);
        return ResponseEntity.status(HttpStatus.OK).body(blogUserList);

    }

}
