package com.commons.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEvent {
    private Long id;
    private String name;
    private String authority;
    private String username;
}
