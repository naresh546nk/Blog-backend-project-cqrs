package com.blog.site.quary.api.projection;

import com.blog.site.core.api.entity.BlogUser;

import com.blog.site.core.api.exception.NoUserFoundException;
import com.blog.site.quary.api.query.FindAllUsers;
import com.blog.site.quary.api.query.FindUserById;
import com.blog.site.quary.api.query.FindUserByUsername;
import com.blog.site.core.api.repository.UserRepository;
import com.commons.dto.BlogDto;
import com.commons.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserProjection {
    @Autowired
    private UserRepository userRepository;
    @QueryHandler
    public List<BlogUser> handle(FindAllUsers findAllUsers) {

        List<BlogUser> allUser = userRepository.findAll();
        return allUser;
    }

    @QueryHandler
    public  BlogUser handle(FindUserByUsername findUserByUsername) {
        log.info("Query Handler : "+findUserByUsername);
        Optional<BlogUser> byUsername = userRepository.findByUsername(findUserByUsername.getUsername());
        if(byUsername.isPresent()){
            return byUsername.get();
        }else{
            return null;
        }
    }

    @QueryHandler
    public  UserDto handle(FindUserById findUserById) {
        log.info("QueryHandler");
        Optional<BlogUser> userById = userRepository.findById(findUserById.getId());
        if(userById.isPresent()){
            BlogUser user=userById.get();
            return UserDto.builder()
                    .id(Long.valueOf(user.getId()))
                    .name(user.getName())
                    .authority(user.getAuthority())
                    .username(user.getUsername())
                    .build();

        }else{
          return null;
        }
    }
}
