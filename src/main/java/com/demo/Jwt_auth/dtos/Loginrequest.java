package com.demo.Jwt_auth.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Loginrequest {
    private String username;
    private String password;
}
