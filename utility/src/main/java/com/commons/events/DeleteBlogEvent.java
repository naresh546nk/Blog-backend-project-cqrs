package com.commons.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteBlogEvent {
    private String uuid;
    private  Long id;
}
