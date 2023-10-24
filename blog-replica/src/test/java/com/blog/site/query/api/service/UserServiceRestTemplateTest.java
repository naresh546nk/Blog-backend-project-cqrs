package com.blog.site.query.api.service;


import com.blog.site.core.api.service.UserServiceRestTemplet;
import com.commons.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RestTemplate.class, UserServiceRestTemplet.class})
public class UserServiceRestTemplateTest {

    @Autowired
    private  UserServiceRestTemplet userServiceRestTemplet;

    @MockBean
    private  RestTemplate restTemplate;

    @Value("${user.replica.url}")
    private String URL;

    private  UserDto user=UserDto.builder()
            .id(1L)
            .username("naresh@gmail.com")
            .authority("ADMIN")
            .name("Naresh Kumar")
            .build();
    @Test
    public  void gerUserByById_test(){
        ResponseEntity<UserDto> userDto = ResponseEntity.status(HttpStatus.OK).body(user);
        Mockito.when(restTemplate.getForEntity(URL+1, UserDto.class)).thenReturn(userDto);
        UserDto userById = userServiceRestTemplet.getUserById("1");
        Assertions.assertNotNull(userById);
        Assertions.assertEquals(userById.getUsername().equalsIgnoreCase("naresh@gmail.com"),true);


    }

}
