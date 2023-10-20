package com.blog.site.command.api.aggregate;

import com.blog.site.command.api.aggrigates.BlogAggrigate;
import com.blog.site.core.api.entity.Blog;
import com.blog.site.core.api.repository.BlogRepository;
import com.commons.events.BlogCreateEvent;
import com.commons.events.DeleteBlogEvent;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BlogRepository.class, BlogAggrigate.class})
public class BlogAggregateTest {

    @Autowired
    private  BlogAggrigate handler;

    @MockBean
    private  BlogRepository repository;

    @Test
    public void blogCreateEventHandler_test(){
        BlogCreateEvent event=BlogCreateEvent.builder()
        .id(Long.valueOf(10))
        .blogName("Test Blog Name")
        .category("Motivation")
        .userId("20")
        .build();
        handler.on(event);
    }

    @Test
    public void deleteBlogEvent_test(){
        DeleteBlogEvent event= DeleteBlogEvent.builder()
                .id(Long.valueOf(3))
                .build();
        handler.on(event);
    }

}
