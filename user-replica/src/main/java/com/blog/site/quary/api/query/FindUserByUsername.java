package com.blog.site.quary.api.query;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindUserByUsername {
    private String username;
}
