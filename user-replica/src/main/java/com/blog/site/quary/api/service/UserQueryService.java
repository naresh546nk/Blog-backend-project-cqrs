package com.blog.site.quary.api.service;

import com.blog.site.core.api.entity.BlogUser;
import com.blog.site.quary.api.query.FindAllUsers;
import com.blog.site.quary.api.query.FindUserById;
import com.blog.site.quary.api.query.FindUserByUsername;
import com.commons.dto.UserDto;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryService {
    @Autowired
    private QueryGateway queryGateway;
    public  void test(){

    }

    public BlogUser findUserByUsername(FindUserByUsername query){
        return queryGateway.query(query, ResponseTypes.instanceOf(BlogUser.class)).join();
    }

    public UserDto findUserById(FindUserById query) {
        return  queryGateway.query(query, ResponseTypes.instanceOf(UserDto.class)).join();
    }

    public List<BlogUser> findAllUser(FindAllUsers query) {
        return queryGateway.query(query,ResponseTypes.multipleInstancesOf(BlogUser.class)).join();
    }
}
