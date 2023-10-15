package com.blog.site.core.api.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Document
public class Blog {
    @Id
    private Long id;
    private String blogName;
    private String category;
    private String article;
    private String authorName;
    private LocalDate createdOn=LocalDate.now();
    private String userId;
}
