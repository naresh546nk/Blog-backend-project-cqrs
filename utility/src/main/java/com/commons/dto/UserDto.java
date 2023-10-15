package com.commons.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String authority;
    private String username;
}
