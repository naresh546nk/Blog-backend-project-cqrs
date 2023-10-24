package com.blog.site.query.api.query;

import lombok.*;

@Builder
@Getter
public class FindUserByUsername {
    private String username;
}
