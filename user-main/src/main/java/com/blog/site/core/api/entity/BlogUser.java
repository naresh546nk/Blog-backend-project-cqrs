package com.blog.site.core.api.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class BlogUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "Name is Mandatory")
    private String name;
    @NotBlank(message = "Authority is Mandatory")
    private String authority;
    @NotBlank(message = "User Name is Mandatory")
    @Email(message = "Not a valid email check again")
    @Column(unique = true)
    private String username;
}
