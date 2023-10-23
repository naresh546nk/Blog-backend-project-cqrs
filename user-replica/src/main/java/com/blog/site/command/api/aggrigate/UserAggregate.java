package com.blog.site.command.api.aggrigate;



import com.blog.site.core.api.entity.BlogUser;
import com.blog.site.core.api.repository.UserRepository;
import com.commons.events.DeleteUserEvent;
import com.commons.events.UserCreateEvent;
import lombok.extern.slf4j.Slf4j;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserAggregate {

    @Autowired
    private UserRepository repository;

    @EventHandler
    public void on(UserCreateEvent event) {
        log.info("Event :"+event);
        BlogUser user= BlogUser.builder().build();
        BeanUtils.copyProperties(event,user);
        log.info("user :"+user);
        repository.save(user);
    }

    @EventHandler
    public  void on(DeleteUserEvent event){
        log.info("Event : {}",event);
        repository.deleteById(event.getId().toString());
        log.info("User deleted successfully with Id : {}",event.getId());

    }

}
