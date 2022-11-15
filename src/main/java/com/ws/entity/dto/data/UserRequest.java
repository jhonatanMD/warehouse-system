package com.ws.entity.dto.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long id;
    private String user;
    private String password;
    private Boolean status = true;
    private Long employee;
    private Long role;
}
