
package com.commons.events;


import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCreateEvent{
      private  String uuid;
      private String id ;
      private String name;
      private String authority;
      private String username;

    
}
