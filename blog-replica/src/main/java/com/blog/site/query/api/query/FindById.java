package com.blog.site.query.api.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindById {
    private Long id;
}
