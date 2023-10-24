package com.blog.site.query.api.service;

import com.blog.site.core.api.entity.BlogUser;
import com.blog.site.query.api.query.FindAllUsers;
import com.blog.site.query.api.query.FindUserById;
import com.blog.site.query.api.query.FindUserByUsername;
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

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {QueryGateway.class,UserQueryService.class})
@Slf4j
public class UserQueryServiceTest {

    @MockBean
    private QueryGateway queryGateway;

    @Autowired
    private UserQueryService service;

    BlogUser user=BlogUser.builder()
            .id("4")
            .name("Naresh Kumar")
            .authority("USER")
            .username("nares@gmail.com")
            .build();

    @Test
    public  void findUserByUsernameTest() throws ExecutionException, InterruptedException {
        FindUserByUsername findUsername = FindUserByUsername.builder().username("naresh546.nk@gmail.com").build();
        CompletableFuture<BlogUser> feature = CompletableFuture.supplyAsync(() -> getUser());
        log.info("Feature : {}",feature);
        log.info("Get data for featur : {} ",feature.get());
        Mockito.when(queryGateway.query(findUsername, ResponseTypes.instanceOf(BlogUser.class))).thenReturn(feature);
        BlogUser blogUser = service.findUserByUsername(findUsername);
        Assertions.assertNotNull(blogUser);
    }



    @Test
    public  void findUserByIdTest() throws ExecutionException, InterruptedException {
        FindUserById userById =FindUserById.builder().id("3").build();

        CompletableFuture<UserDto> userDtoFeature = CompletableFuture.supplyAsync(() -> getUserDto());
        Mockito.when(queryGateway.query(userById, ResponseTypes.instanceOf(UserDto.class))).thenReturn(userDtoFeature);
        UserDto result = service.findUserById(userById);
        Assertions.assertNotNull(result);
    }

    @Test
    public  void findAllUserTest() throws ExecutionException, InterruptedException {

        FindAllUsers users=new FindAllUsers();
        CompletableFuture<List<BlogUser>> feature = CompletableFuture.supplyAsync(() -> List.of(user));

        Mockito.when(queryGateway.query(users, ResponseTypes.multipleInstancesOf(BlogUser.class))).thenReturn(feature);
        List<BlogUser> result = service.findAllUser(users);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(),1);
    }

    private BlogUser getUser(){
        return  user;
    }
    private UserDto getUserDto(){
        UserDto dto=new UserDto();
        BeanUtils.copyProperties(user,dto);
        return  dto;
    }

}
