package com.blog.site.query.api.projection;

import com.blog.site.core.api.entity.Blog;
import com.blog.site.core.api.repository.BlogRepository;
import com.blog.site.core.api.service.BlogMongoTempletService;
import com.blog.site.core.api.service.UserServiceRestTemplet;
import com.blog.site.query.api.query.*;
import com.commons.dto.BlogDto;
import com.commons.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BlogProjection.class, BlogRepository.class,UserServiceRestTemplet.class,BlogMongoTempletService.class})
public class BlogProjectionTest {


    @Autowired
    private BlogProjection projection;

    @MockBean
    private  BlogRepository repository;

    @MockBean
    private UserServiceRestTemplet userService ;

    @MockBean
    private BlogMongoTempletService mongoTemplateService;
    private String article="Women account for one of the groups most affected by the disparities in the legal system. Therefore, this sphere " +
            "has been an area of intense interest for feminist researchers and activists. Subsequently, various standpoints are expressed through different means of " +
            "communication, calling for an equal approach to justice for both sexes. Such kinds of activism positively affect society, as they attract the public’s attention " +
            "to a topical issue. Willison and O’Brien (2016) state that the incarceration rate of women in the United States has grown at a quicker pace than a similar number for men." +
            " Accordingly, it is possible to conclude that the country is currently experiencing the phenomenon of mass female incarceration, which reflects the unfair side of the legal system."+
            "Women account for one of the groups most affected by the disparities in the legal system. Therefore, this sphere " +
            "has been an area of intense interest for feminist researchers and activists. Subsequently, various standpoints are expressed through different means of " +
            "communication, calling for an equal approach to justice for both sexes. Such kinds of activism positively affect society, as they attract the public’s attention " +
            "to a topical issue. Willison and O’Brien (2016) state that the incarceration rate of women in the United States has grown at a quicker pace than a similar number for men." +
            " Accordingly, it is possible to conclude that the country is currently experiencing the phenomenon of mass female incarceration, which reflects the unfair side of the legal system."+
            "Women account for one of the groups most affected by the disparities in the legal system. Therefore, this sphere " +
            "has been an area of intense interest for feminist researchers and activists. Subsequently, various standpoints are expressed through different means of " +
            "communication, calling for an equal approach to justice for both sexes. Such kinds of activism positively affect society, as they attract the public’s attention " +
            "to a topical issue. Willison and O’Brien (2016) state that the incarceration rate of women in the United States has grown at a quicker pace than a similar number for men." +
            " Accordingly, it is possible to conclude that the country is currently experiencing the phenomenon of mass female incarceration, which reflects the unfair side of the legal system.";

    private Blog blog=Blog.builder()
            .blogName("This is my blogName")
            .authorName("Myke Tyson")
            .category("Motivation")
            .article(article)
            .userId("10")
            .build();

    @Test
    public  void  findAllBlogHandler_test() throws Exception {
        Mockito.when(repository.findAll()).thenReturn(List.of(blog));
        List<Blog> handle = projection.handle(new FindAllBlogs());
        Assertions.assertNotNull(handle);
        Assertions.assertEquals(handle.size(),1);
    }

    @Test
    public  void  distinctCategory_test() throws Exception {
        Mockito.when(mongoTemplateService.findDistinctCategory()).thenReturn(List.of("Motivation"));
        List<String> handle = projection.handle(new FindDistinctCategory());
        Assertions.assertNotNull(handle);
        Assertions.assertEquals(handle.size(),1);
    }

    @Test
    public  void  findByCategory_test() throws Exception {
        FindByCategory findByCategory=FindByCategory.builder()
                .category("Motivation")
                .build();
        Mockito.when(repository.findByCategory(Mockito.anyString())).thenReturn(List.of(blog));
        List<Blog> handle = projection.handle(findByCategory);
        Assertions.assertNotNull(handle);
        Assertions.assertEquals(handle.size(),1);
    }


    @Test
    public  void  findByStartAndEndDate_test() throws Exception {
        FindByStartAndEndDate findByStartAndEndDate= FindByStartAndEndDate.builder()
                .start(LocalDate.now().minusDays(13))
                .end(LocalDate.now())
                .build();

        Mockito.when(mongoTemplateService.findByStartAndEndDate(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class))).thenReturn(List.of(blog));
        List<Blog> handle = projection.handle(findByStartAndEndDate);
        Assertions.assertNotNull(handle);
        Assertions.assertEquals(handle.size(),1);
    }


    @Test
    public  void  findById_test() throws Exception {
        FindById findById=FindById.builder()
                .id(Long.valueOf(5))
                .build();
        UserDto userDto = UserDto.builder()
                .id(Long.valueOf(5))
                .name("Naresh Kumar")
                .authority("ADMIN")
                .username("naresh@gmail.com")
                .build();

        Mockito.when(repository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(blog));
        Mockito.when(userService.getUserById(Mockito.anyString())).thenReturn(userDto);
        BlogDto handle = projection.handle(findById);

        Assertions.assertNotNull(handle);
        Assertions.assertEquals(handle.getCategory(),"Motivation");
        Assertions.assertEquals(handle.getUserDto().getUsername(),"naresh@gmail.com");


    }





}




