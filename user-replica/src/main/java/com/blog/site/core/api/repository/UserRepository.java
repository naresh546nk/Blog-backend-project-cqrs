package com.blog.site.core.api.repository;
import com.blog.site.core.api.entity.BlogUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<BlogUser, String> {
    Optional<BlogUser> findByName(String name);
    Optional<BlogUser> findByUsername(String username);
}
