
package com.commons.events;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCreateEventMain {
      private  String uuid;
      private String id ;
      private String name;
      private String authority;
      private String username;

    
}
