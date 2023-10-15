package com.blog.site.command.api.controller;

import com.blog.site.core.api.entity.BlogUser;
import com.blog.site.core.api.exception.ValidationException;
import com.blog.site.core.api.repository.UserRepository;
import com.blog.site.core.api.validation.UserValidation;
import com.commons.commands.CreateUserCommand;
import com.commons.commands.DeleteUserCommand;
import com.google.api.Http;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000", "*"})
@RestController
@RequestMapping("api/v1.0/blogsite/users")
@Slf4j
public class UserCommandController {
    @Autowired
    private UserRepository repository;

    private CommandGateway commandGateway;
    public UserCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping("/add")
    @SneakyThrows
    public  ResponseEntity<BlogUser> register(@Valid @RequestBody BlogUser blogUser) throws ValidationException {
        UserValidation.isValidUser(blogUser);
        log.info("User Validation passed ..");
        log.info("Saving user to MYSQL"+blogUser);
        BlogUser blogUserSaved = repository.save(blogUser);
        log.info("User Saved "+blogUserSaved);
        CreateUserCommand userCommand= CreateUserCommand.builder()
                .uuid(UUID.randomUUID().toString())
                //.id(blogUserSaved.getId().toString())
                .name(blogUser.getName())
                .username(blogUser.getUsername())
                .authority(blogUser.getAuthority())
                .build();
        String result = commandGateway.sendAndWait(userCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(blogUserSaved);
    }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteUser(@PathVariable Long id){
            repository.deleteById(id);
            DeleteUserCommand deleteUserCommand=DeleteUserCommand.builder()
                    .uuid(UUID.randomUUID().toString())
                    .id(id)
                    .build();
            String uuid = commandGateway.sendAndWait(deleteUserCommand);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted with confirmation id : "+uuid);
        }

}
