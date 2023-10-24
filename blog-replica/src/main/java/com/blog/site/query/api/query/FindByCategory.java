package com.blog.site.query.api.query;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;


@Builder
@Getter
public class FindByCategory {
    private String category;

}
