package com.blog.site.core.api.repository;

import com.blog.site.core.api.entity.BlogUser;
import org.springframework.data.jpa.repository .JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<BlogUser, Long> {
    Optional<BlogUser> findByName(String name);
    Optional<BlogUser> findByUsername(String username);
}
