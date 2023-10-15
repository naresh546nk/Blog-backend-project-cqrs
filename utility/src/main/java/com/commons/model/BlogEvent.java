package com.commons.model;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BlogEvent {
    private Long id;
    private String blogName;
    private String category;
    private String article;
    private String authorName;
    private LocalDate createdOn=LocalDate.now();
    private UserEvent userEvent;
}
