package com.blog.site.core.api.service;

import com.commons.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Value("${user.replica.url}")
    private String URL;

    @Autowired
    private  RestTemplate restTemplate;

    public UserDto getUserById(String id){
        log.info("Find the user data by URL :"+URL);
        ResponseEntity<UserDto> response = restTemplate.getForEntity(URL + id, UserDto.class);
        return response.getBody();
    }


}
