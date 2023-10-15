package com.commons.commands;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateUserCommand{
    @TargetAggregateIdentifier
    private String uuid;
    private String id ;
    private String name;
    private String authority;
    private String username;

}
