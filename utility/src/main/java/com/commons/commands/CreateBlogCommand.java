package com.commons.commands;

import com.commons.model.UserEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class CreateBlogCommand {
    @TargetAggregateIdentifier
    private String uuid;
    private Long id;
    private String blogName;
    private String category;
    private String article;
    private String authorName;
    private LocalDate createdOn;
    private String userId;
}
