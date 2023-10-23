package com.blog.site.query.api.projection;


import com.blog.site.core.api.entity.BlogUser;
import com.blog.site.core.api.repository.UserRepository;
import com.blog.site.query.api.query.FindAllUsers;
import com.blog.site.query.api.query.FindUserById;
import com.blog.site.query.api.query.FindUserByUsername;
import com.commons.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserProjection.class, UserRepository.class})
@Slf4j
public class UserProjectionTest {
    private BlogUser blogUser=BlogUser.builder()
            .id("4")
            .name("Naresh Kumar")
            .authority("ADMIN")
            .username("naresh546.nk@gmail.com")
            .build();

    @Autowired
    private UserProjection query;

    @MockBean
    private UserRepository repository;


    @Test
    public  void  findUserByUserName_test() throws Exception {
        Optional<BlogUser> optionalUser = Optional.of(this.blogUser);
        Mockito.when(repository.findByUsername(Mockito.anyString())).thenReturn(optionalUser);
        BlogUser handle = query.handle(FindUserByUsername.builder().username("naresh546.nk@gamil.com").build());
        Assertions.assertEquals(handle.getAuthority().equalsIgnoreCase("ADMIN"),true);
    }

    @Test
    public  void  findUserByUserName_null_test(){

       // Mockito.when(repository.findByUsername(Mockito.anyString())).thenReturn(optionalUser);
        BlogUser handle = query.handle(FindUserByUsername.builder().username("naresh546.nk@gamil.com").build());
        Assertions.assertNull(handle);
    }

    @Test
    public  void  findUserById_test(){
        Optional<BlogUser> optionalUser = Optional.of(this.blogUser);
        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(optionalUser);
        UserDto handle = query.handle( FindUserById.builder().id("4").build());
        Assertions.assertEquals(handle.getAuthority().equalsIgnoreCase("ADMIN"),true);
    }

    @Test
    public  void  findUserById_null_test(){
        UserDto handle = query.handle(FindUserById.builder().id("1").build());
        Assertions.assertNull(handle);
    }

    @Test
    public  void  findAllUser_test(){
        Mockito.when(repository.findAll()).thenReturn(List.of(this.blogUser));
        List<BlogUser> handle = query.handle(new FindAllUsers());
        Assertions.assertNotNull(handle);
        Assertions.assertEquals(handle.size(),1);
    }








}
