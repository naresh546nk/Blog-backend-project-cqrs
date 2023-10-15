package com.commons.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteUserEvent {
    private  String uuid;
    private  Long id;
}
