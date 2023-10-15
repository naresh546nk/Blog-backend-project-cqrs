package com.blog.site.core.api.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Blog Name is Mandatory")
    private String blogName;
    @NotBlank(message = "Blog Category is Mandatory")
    private String category;

    @Column(columnDefinition = "longtext")
    @NotBlank(message = "Blog Article is Mandatory")
    private String article;
    @NotBlank(message = "Author Name is Mandatory")
    private String authorName;
    private LocalDate createdOn=LocalDate.now();
    @NotNull(message = "Blog User can not be null")
    private String userId;
}
