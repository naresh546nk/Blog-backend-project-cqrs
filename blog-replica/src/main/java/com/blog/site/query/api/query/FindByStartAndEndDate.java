package com.blog.site.query.api.query;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class FindByStartAndEndDate {
    private LocalDate start;
    private LocalDate end;

}
