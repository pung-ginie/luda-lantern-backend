package com.genie.ludalantern.dto;

import lombok.*;

@Builder
@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String token;
    private String username;
    private String password;
    private String userId;
    private String email;
    private String id;
}
