package com.blog.site.command.api.aggregate;

import com.commons.commands.CreateUserCommand;
import com.commons.commands.DeleteUserCommand;
import com.commons.events.DeleteUserEvent;
import com.commons.events.UserCreateEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class UserAggregateTest {
    private AggregateTestFixture<UserAggregate> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(UserAggregate.class);
    }

    @Test
    public  void  createUserCommandTest(){
        CreateUserCommand command=CreateUserCommand.builder()
                .uuid(UUID.randomUUID().toString())
                .id("1")
                .name("Naresh Kumar")
                .authority("ADMIN")
                .username("naresh546.nk@gmail.com")
                .build();
        UserCreateEvent event=new UserCreateEvent();
        BeanUtils.copyProperties(command,event);
        fixture.givenNoPriorActivity()
                .when(command)
                .expectEvents(event);
    }
    @Test
    public  void deleteUserCommandTest(){
        DeleteUserCommand command=DeleteUserCommand.builder()
                .uuid(UUID.randomUUID().toString())
                .id(Long.valueOf(1))
                .build();
        DeleteUserEvent event=DeleteUserEvent.builder()
                .uuid(command.getUuid())
                .id(command.getId())
                .build();
        fixture.givenNoPriorActivity()
                .when(command)
                .expectEvents(event);

    }

}
