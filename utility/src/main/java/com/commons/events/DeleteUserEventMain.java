package com.commons.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteUserEventMain {
    private  String uuid;
    private  Long id;
}
