package com.blog.site.core.api.validation;

import com.blog.site.core.api.entity.BlogUser;
import com.blog.site.core.api.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
@Slf4j
@Component
public class UserValidation {
    public static boolean isValidUser(BlogUser blogUser) throws ValidationException {
       log.info("validation started : {},blogUser");
        String nameRegx = "^(([A-Z]|[a-z])+(([A-Z ]|[a-z])+)?){8,}$";
        String emailRegx = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        boolean nameValidation = Pattern.matches(nameRegx, blogUser.getName());
        if(!nameValidation){
           log.debug("Name Validation failed ");
            throw new ValidationException("Name validation Failed try with correct name and must be 8 character");
        }
        boolean emailValidation = Pattern.matches(emailRegx, blogUser.getUsername());
        if(!emailValidation){
            throw new ValidationException("Email validation Failed try with correct email");
        }

        if(blogUser.getAuthority().equalsIgnoreCase("admin") || blogUser.getAuthority().equalsIgnoreCase("user")){

        }else{
            throw  new ValidationException("Authority validation failed, it should be either Admin or User");
        }

        log.info("User data pass the validation .");
        return true;
    }
}
