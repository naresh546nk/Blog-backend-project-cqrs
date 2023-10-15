package com.blog.site.command.api.aggrigates;

import com.blog.site.core.api.entity.Blog;
import com.blog.site.core.api.repository.BlogRepository;
import com.commons.events.BlogCreateEvent;
import com.commons.events.DeleteBlogEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BlogAggrigate {
    @Autowired
    private BlogRepository repository;

    @EventHandler
    public  void on(BlogCreateEvent event){
        log.info("EventHandler called :"+event);
        Blog blog=new Blog();
        BeanUtils.copyProperties(event,blog);
        log.info("events copied to blog :"+blog);
        Blog save = repository.save(blog);
        log.info("Blog saved to Mongo Db :"+save);
    }

    @EventHandler
    public  void on(DeleteBlogEvent deleteBlogEvent){
        log.info("Delete blog with id :{}",deleteBlogEvent.getId());
        repository.deleteById(deleteBlogEvent.getId());
        log.info("Blog deleted successfully ..");
    }
}
