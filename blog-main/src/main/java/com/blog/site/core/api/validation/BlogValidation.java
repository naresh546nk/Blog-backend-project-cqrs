package com.blog.site.core.api.validation;

import com.blog.site.core.api.entity.Blog;
import com.blog.site.core.api.exception.ValidationException;

import java.util.regex.Pattern;

public class BlogValidation {
    public static boolean isValidUser(Blog blog) throws ValidationException {
        int len=blog.getArticle().split(" ").length;
        if(len<200){
            throw new ValidationException("Number of word should be in between 200 to 2000, You have entered only "+len);
        }
        if(blog.getBlogName().length()<8){
            throw new ValidationException("Blog name should be more then 8 characters");
        }
        if(blog.getCategory().length()<8){
            throw new ValidationException("Blog category should be more then 8 characters");
        }
        if(blog.getAuthorName().length()<8){
            throw new ValidationException("Blog Author name should be more then 8 characters");
        }
        return true;
    }
}
