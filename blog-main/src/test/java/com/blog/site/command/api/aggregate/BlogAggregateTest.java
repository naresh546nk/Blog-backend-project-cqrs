package com.blog.site.command.api.aggregate;

import com.commons.commands.CreateBlogCommand;
import com.commons.commands.DeleteBlogCommand;
import com.commons.events.BlogCreateEvent;
import com.commons.events.DeleteBlogEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.util.UUID;


public class BlogAggregateTest {
    private AggregateTestFixture<BlogAggregate> fixture;
    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(BlogAggregate.class);
    }

    @Test
    public  void  createUserCommandTest(){
        CreateBlogCommand command=CreateBlogCommand.builder()
                .uuid(UUID.randomUUID().toString())
                .blogName("This my blog name")
                .article("Articles of the blog")
                .build();
        BlogCreateEvent event=new BlogCreateEvent();
        BeanUtils.copyProperties(command,event);
        fixture.givenNoPriorActivity()
                .when(command)
                .expectEvents(event);
    }
    @Test
    public  void deleteBlogCommandTest(){
        DeleteBlogCommand command=DeleteBlogCommand.builder()
                .uuid(UUID.randomUUID().toString())
                .id(Long.valueOf(1))
                .build();
        DeleteBlogEvent event=DeleteBlogEvent.builder()
                .uuid(command.getUuid())
                .id(command.getId())
                .build();
        fixture.givenNoPriorActivity()
                .when(command)
                .expectEvents(event);

    }

}
