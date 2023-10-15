package com.blog.site.command.api.controller;

import com.blog.site.core.api.entity.BlogUser;
import com.blog.site.core.api.exception.ValidationException;
import com.blog.site.core.api.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {UserCommandController.class, CommandGateway.class, UserRepository.class})
@Slf4j
public class UserCommandControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private  CommandGateway commandGateway;

    @MockBean
    private  UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    private String BASE_URL="/api/v1.0/blogsite/users";

    private BlogUser blogUser=BlogUser.builder()
            .name("Naresh Kumar")
            .authority("ADMIN")
            .username("naresh546.nk@gmail.com")
            .build();

    @Test
    public  void register_test() throws Exception {
        BlogUser user=blogUser;
        BlogUser savedUser=blogUser;
        savedUser.setId(Long.valueOf(5));
        String json=objectMapper.writeValueAsString(user);
        Mockito.when(userRepository.save(Mockito.any(BlogUser.class))).thenReturn(savedUser);
        String contentAsString = mockMvc.perform(post(BASE_URL+"/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(contentAsString.contains("authority"),true);
        Assertions.assertEquals(contentAsString.contains("ADMIN"),true);
        Assertions.assertEquals(contentAsString.contains("username"),true);
        Assertions.assertEquals(contentAsString.contains("a"),true);
        System.out.println("Response :"+contentAsString);
    }


    @Test
    public  void register_BadName_test() throws Exception {
        BlogUser user=blogUser;
        user.setName("");
        BlogUser savedUser=blogUser;
        savedUser.setId(Long.valueOf(5));

        String json=objectMapper.writeValueAsString(user);
        Mockito.when(userRepository.save(Mockito.any(BlogUser.class))).thenReturn(savedUser);

        String contentAsString = mockMvc.perform(post(BASE_URL + "/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                    .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
                    .andReturn().getResponse().getContentAsString();
    }


    @Test
    public  void register_Name_is_less_then_8_character_test() throws Exception {
        BlogUser user=blogUser;
        user.setName("Naresh");
        BlogUser savedUser=blogUser;
        savedUser.setId(Long.valueOf(5));

        String json=objectMapper.writeValueAsString(user);
        Mockito.when(userRepository.save(Mockito.any(BlogUser.class))).thenReturn(savedUser);

        Exception ex = Assertions.assertThrows(Exception.class, () -> {
            String contentAsString = mockMvc.perform(post(BASE_URL + "/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                    .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
                    .andReturn().getResponse().getContentAsString();
        });
        Assertions.assertEquals(ex.getMessage().contains("validation"),true);
    }

    @Test
    public  void register_email_test() throws Exception {
        BlogUser user=blogUser;
        user.setUsername("naresh546.nk@g.com");
        BlogUser savedUser=blogUser;
        savedUser.setId(Long.valueOf(5));

        String json=objectMapper.writeValueAsString(user);
        Mockito.when(userRepository.save(Mockito.any(BlogUser.class))).thenReturn(savedUser);

        Exception ex = Assertions.assertThrows(Exception.class, () -> {
            String contentAsString = mockMvc.perform(post(BASE_URL + "/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                    .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
                    .andReturn().getResponse().getContentAsString();
        });
        log.info("message :{}",ex.getMessage());
        Assertions.assertEquals(ex.getMessage().contains("validation"),true);
    }

    @Test
    public  void register_Authority_test() throws Exception {
        BlogUser user=blogUser;
        user.setAuthority("Developer");
        BlogUser savedUser=blogUser;
        savedUser.setId(Long.valueOf(5));

        String json=objectMapper.writeValueAsString(user);
        Mockito.when(userRepository.save(Mockito.any(BlogUser.class))).thenReturn(savedUser);

        Exception ex = Assertions.assertThrows(Exception.class, () -> {
            String contentAsString = mockMvc.perform(post(BASE_URL + "/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                    .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
                    .andReturn().getResponse().getContentAsString();
        });
        log.info("message :{}",ex.getMessage());
        Assertions.assertEquals(ex.getMessage().contains("validation"),true);
    }


    @Test
    public  void deletBlog_test() throws Exception {
        BlogUser user=blogUser;
        BlogUser savedUser=blogUser;
        savedUser.setId(Long.valueOf(5));
        String json=objectMapper.writeValueAsString(user);
        String contentAsString = mockMvc.perform(delete(BASE_URL+"/delete/{id}",1).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(contentAsString.contains("User deleted with confirmation id"),true);
    }
}
