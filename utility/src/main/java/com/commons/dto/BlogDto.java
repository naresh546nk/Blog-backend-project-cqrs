package com.commons.dto;

import com.commons.model.UserEvent;
import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class BlogDto {
    private Long id;
    private String blogName;
    private String category;
    private String article;
    private String authorName;
    private LocalDate createdOn;
    private UserDto userDto;
}
