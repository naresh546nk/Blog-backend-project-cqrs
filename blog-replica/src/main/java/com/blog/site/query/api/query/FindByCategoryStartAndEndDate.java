package com.blog.site.query.api.query;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;


@Builder
@Getter
public class FindByCategoryStartAndEndDate {
    private String category;
    private LocalDate start;
    private LocalDate end;

}
