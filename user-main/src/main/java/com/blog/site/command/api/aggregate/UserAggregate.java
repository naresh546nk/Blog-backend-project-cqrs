package com.blog.site.command.api.aggregate;

import com.blog.site.core.api.entity.BlogUser;
import com.blog.site.core.api.repository.UserRepository;
import com.commons.commands.CreateUserCommand;
import com.commons.commands.DeleteUserCommand;
import com.commons.events.DeleteUserEvent;
import com.commons.events.UserCreateEvent;
import com.commons.events.UserCreateEventMain;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

//Command Handler
@Aggregate
@Slf4j
@Service
public class UserAggregate {
    @AggregateIdentifier
    private String uuid;
    private String id;
    private String name;
    private String authority;
    private String username;
    UserAggregate(){
    }
    //add to the event source
    //
    //
    @CommandHandler
   public UserAggregate(CreateUserCommand createUserCommand){
        //Any validation can be done here
        log.info("CommandCreated :"+createUserCommand);
       UserCreateEvent userCreateEvent = new UserCreateEvent();
       BeanUtils.copyProperties(createUserCommand,userCreateEvent);
       AggregateLifecycle.apply(userCreateEvent);
   }

   @EventSourcingHandler
   public void onEventHandler(UserCreateEvent userCreateEvent){
        this.uuid=userCreateEvent.getUuid();
        this.id = userCreateEvent.getId();
        this.name = userCreateEvent.getName();
        this.authority = userCreateEvent.getAuthority();
        this.username = userCreateEvent.getUsername();
   }
   @CommandHandler
    public  UserAggregate(DeleteUserCommand deleteUserCommand){
       DeleteUserEvent deleteUserEvent=DeleteUserEvent.builder()
               .uuid(deleteUserCommand.getUuid())
               .id(deleteUserCommand.getId())
               .build();
            log.info("Delete Event Created : {}",deleteUserEvent);

       AggregateLifecycle.apply(deleteUserEvent);
   }

   @EventSourcingHandler
    public void on(DeleteUserEvent deleteUserEvent){
        this.uuid=deleteUserEvent.getUuid();
        this.id=deleteUserEvent.getId().toString();
        log.info("Event published to db : {}",deleteUserEvent);
   }

}
