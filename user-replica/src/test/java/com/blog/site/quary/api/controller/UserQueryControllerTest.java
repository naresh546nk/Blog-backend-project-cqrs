package com.blog.site.quary.api.controller;

import com.blog.site.core.api.entity.BlogUser;
import com.blog.site.quary.api.query.FindAllUsers;
import com.blog.site.quary.api.query.FindUserById;
import com.blog.site.quary.api.query.FindUserByUsername;
import com.blog.site.quary.api.service.UserQueryService;
import com.commons.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserQueryController.class)
@Slf4j
public class UserQueryControllerTest {

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private UserQueryService service;

        @Autowired
        MockMvc mockMvc;


        private String BASE_URL="/api/v1.0/blogsite/users";

        private BlogUser blogUser=BlogUser.builder()
                .name("Naresh Kumar")
                .authority("ADMIN")
                .username("naresh546.nk@gmail.com")
                .build();

        @Test
        public  void gerUserByUserName_Test() throws Exception {
            BlogUser user=blogUser;
            BlogUser savedUser=blogUser;
            savedUser.setId("5");
            String json=objectMapper.writeValueAsString(user);
            FindUserByUsername byUsername=new FindUserByUsername();
            Mockito.when(service.findUserByUsername(Mockito.any(FindUserByUsername.class))).thenReturn(savedUser);
            String contentAsString = mockMvc.perform(get(BASE_URL+"/username/{username}","naresh546.nk@gmail.com")
                    .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                    .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            Assertions.assertEquals(contentAsString.contains("name"),true);
            Assertions.assertEquals(contentAsString.contains("authority"),true);
            Assertions.assertEquals(contentAsString.contains("username"),true);
            Assertions.assertEquals(contentAsString.contains("id"),true);
            log.info("Response :"+contentAsString);
        }

    @Test
    public  void gerUserById_Test() throws Exception {
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(blogUser,userDto);
        BlogUser savedUser=blogUser;
        savedUser.setId("5");
        Mockito.when(service.findUserById(Mockito.any(FindUserById.class))).thenReturn(userDto);
        String contentAsString = mockMvc.perform(get(BASE_URL+"/{id}",Long.valueOf(4))
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(contentAsString.contains("name"),true);
        Assertions.assertEquals(contentAsString.contains("authority"),true);
        Assertions.assertEquals(contentAsString.contains("username"),true);
        Assertions.assertEquals(contentAsString.contains("id"),true);
        log.info("Response :"+contentAsString);
    }
    @Test
    public  void gerAllUsers_Test() throws Exception {
        BlogUser user=blogUser;

        String json=objectMapper.writeValueAsString(user);
        FindUserByUsername byUsername=new FindUserByUsername();
        Mockito.when(service.findAllUser(Mockito.any(FindAllUsers.class))).thenReturn(List.of(user));
        String contentAsString = mockMvc.perform(get(BASE_URL+"/getall")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(contentAsString.contains("name"),true);
        Assertions.assertEquals(contentAsString.contains("authority"),true);
        Assertions.assertEquals(contentAsString.contains("username"),true);
        Assertions.assertEquals(contentAsString.contains("id"),true);
        log.info("Response :"+contentAsString);
    }


    }
