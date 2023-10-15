package com.blog.site.command.api.aggregate;

import com.blog.site.core.api.repository.BlogRepository;
import com.commons.commands.CreateBlogCommand;
import com.commons.commands.CreateUserCommand;
import com.commons.commands.DeleteBlogCommand;
import com.commons.events.BlogCreateEvent;
import com.commons.events.DeleteBlogEvent;
import com.commons.events.UserCreateEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.UUID;

@Aggregate
@Slf4j
public class BlogAggregate {
    @AggregateIdentifier
    private String uuid;
    private Long id;
    private String blogName;
    private String category;
    private String article;
    private String authorName;
    private LocalDate createdOn;
    private String userId;

    BlogAggregate(){}
    //add to the event source
    //
    @CommandHandler
    public BlogAggregate(CreateBlogCommand createBlogCommand){
        //Any validation can be done here
        log.info("CommandCreated :"+createBlogCommand);
        BlogCreateEvent blogCreateEvent = new BlogCreateEvent();
        BeanUtils.copyProperties(createBlogCommand,blogCreateEvent);
        log.info("Coppied properties :"+blogCreateEvent);
        AggregateLifecycle.apply(blogCreateEvent);
    }

    @EventSourcingHandler
    public void onEventHandler(BlogCreateEvent event){
        log.info("Event sent :"+event);
        this.uuid=event.getUuid();
        this.id=event.getId();
        this.blogName=event.getBlogName();
        this.category=event.getCategory();
        this.article=event.getArticle();
        this.authorName=event.getAuthorName();
        this.createdOn=event.getCreatedOn();
        this.userId=event.getUserId();
    }

    @CommandHandler
    public  BlogAggregate(DeleteBlogCommand deleteBlogCommand){
        log.info("Blog delete command {}", deleteBlogCommand);
        DeleteBlogEvent deleteBlogEvent=DeleteBlogEvent.builder()
                .uuid(deleteBlogCommand.getUuid())
                .id(deleteBlogCommand.getId())
                .build();
        AggregateLifecycle.apply(deleteBlogEvent);
    }

    @EventSourcingHandler
    public void on(DeleteBlogEvent deleteBlogEvent){
        log.info("Delete Event sent :"+deleteBlogEvent);
        this.uuid= deleteBlogEvent.getUuid();
        this.id=deleteBlogEvent.getId();
    }

}
