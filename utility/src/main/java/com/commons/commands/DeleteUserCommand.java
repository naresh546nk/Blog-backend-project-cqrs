package com.commons.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class DeleteUserCommand {
    @TargetAggregateIdentifier
    private  String uuid;
    private Long id;
}
