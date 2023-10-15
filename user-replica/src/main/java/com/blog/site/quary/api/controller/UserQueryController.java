package com.blog.site.quary.api.controller;

import com.blog.site.core.api.entity.BlogUser;
import com.blog.site.quary.api.query.FindAllUsers;
import com.blog.site.quary.api.query.FindUserById;
import com.blog.site.quary.api.query.FindUserByUsername;
import com.commons.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@CrossOrigin(origins = {"http://localhost:3000",  "*"})
@RestController
@RequestMapping("api/v1.0/blogsite/users")
@Slf4j
public class UserQueryController {

    private QueryGateway queryGateway;
    public UserQueryController(QueryGateway queryGateway){
        this.queryGateway = queryGateway;
    }

    @GetMapping("/username/{username}")
    public  ResponseEntity<BlogUser> getUserByUsername (@Valid @PathVariable String username) throws ExecutionException, InterruptedException {
        FindUserByUsername findUserByUsername=new FindUserByUsername(username);

        CompletableFuture<BlogUser> response = queryGateway.query(findUserByUsername, ResponseTypes.instanceOf(BlogUser.class));
        BlogUser blogUser = response.get();
        log.info("blog user :"+blogUser);
        return  ResponseEntity.status(HttpStatus.OK).body(blogUser);

    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto>  getUserById(@Valid @PathVariable Long id) throws ExecutionException, InterruptedException {
        FindUserById findUserById=new FindUserById(id.toString());
        log.info("getUserByID ");
        UserDto userDto = queryGateway.query(findUserById, ResponseTypes.instanceOf(UserDto.class)).join();

        log.info("Blog User :"+userDto);
        return  ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<BlogUser>> getAllBlogUser(){
        FindAllUsers findAllUsers=new FindAllUsers();
        log.info("All User list ");
        List<BlogUser> blogUserList = queryGateway.query(findAllUsers,
                ResponseTypes.multipleInstancesOf(BlogUser.class))
                .join();
        return ResponseEntity.status(HttpStatus.OK).body(blogUserList);

    }

}
