package com.blog.site.command.api.aggregate;


import com.blog.site.command.api.aggrigate.UserAggregate;
import com.blog.site.core.api.entity.BlogUser;
import com.blog.site.core.api.repository.UserRepository;
import com.blog.site.quary.api.service.UserQueryService;
import com.commons.events.DeleteUserEvent;
import com.commons.events.UserCreateEvent;
import org.apache.catalina.User;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserAggregate.class, UserRepository.class})
public class UserAggregateTest {
    private AggregateTestFixture<UserAggregate> fixture;

    private BlogUser user=BlogUser.builder()
            .id("1")
            .name("Naresh Kumar")
            .authority("ADMIN")
            .username("naresh546.nk@gmail.com")
            .build();

    QueryUpdateEmitter emitter = mock(QueryUpdateEmitter.class);

    @MockBean
    private  UserRepository repository;
    @Autowired
    private UserAggregate handler;

    @BeforeEach
    void setup(){
    }

    @Test
    void userCreatedEvent() {
        UserCreateEvent event =new UserCreateEvent();
        BeanUtils.copyProperties(user,event);
        event.setUuid(UUID.randomUUID().toString());
        handler.on(event);
       // verify(emitter, times(1)).emit(eq(UserCreateEvent.class), any(), any(BlogUser.class));
    }

    @Test
    void deleteUserEvent() {
        DeleteUserEvent event = DeleteUserEvent.builder()
                .uuid(UUID.randomUUID().toString())
                .id(Long.valueOf(5))
                .build();
        event.setUuid(UUID.randomUUID().toString());
        handler.on(event);
    }
}
