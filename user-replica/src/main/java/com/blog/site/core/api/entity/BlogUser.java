package com.blog.site.core.api.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Setter
@Getter
@Builder
@Document()
public class BlogUser {
    @Id
    private String id;
    @NotBlank(message = "Name is Mandatory")
    private String name;
    @NotBlank(message = "Authority is Mandatory")
    private String authority;
    @NotBlank(message = "User Name is Mandatory")
    @Indexed(unique = true)
    private String username;

}
