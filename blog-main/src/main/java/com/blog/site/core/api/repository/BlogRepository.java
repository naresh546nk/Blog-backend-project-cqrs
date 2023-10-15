package com.blog.site.core.api.repository;

import com.blog.site.core.api.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findByCategory(String category);

    @Query("SELECT b FROM Blog b WHERE b.category=?1 and b.createdOn >=?2 and b.createdOn < ?3 ")
    List<Blog> findByCategoryAndTimeRange(String category, LocalDate createTimeFrom, LocalDate createTimeTo);

    @Query("SELECT DISTINCT b.category FROM Blog b")
    List<String> findDistinctCategory();


}
