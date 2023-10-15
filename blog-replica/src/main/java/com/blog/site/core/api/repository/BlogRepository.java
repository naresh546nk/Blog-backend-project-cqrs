package com.blog.site.core.api.repository;

import com.blog.site.core.api.entity.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BlogRepository extends MongoRepository<Blog, Long> {

    List<Blog> findByCategory(String category);

}
