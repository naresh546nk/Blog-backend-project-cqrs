package com.commons.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class DeleteBlogCommand {
    @TargetAggregateIdentifier
    private String uuid;
    private Long id;
}
