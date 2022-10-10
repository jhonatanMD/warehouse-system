package com.ws.entity.dto.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRoleRequest {

    private Long id;
    private Boolean insert;
    private Boolean consult;
    private Boolean update;
    private Boolean delete;
    private Long module;
    private Long role;
    private Boolean status = true;
}
