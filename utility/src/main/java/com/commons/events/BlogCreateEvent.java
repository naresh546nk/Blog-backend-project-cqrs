package com.commons.events;

import com.commons.model.UserEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BlogCreateEvent {
    private String uuid;
    private Long id;
    private String blogName;
    private String category;
    private String article;
    private String authorName;
    private LocalDate createdOn;
    private String userId;
}
