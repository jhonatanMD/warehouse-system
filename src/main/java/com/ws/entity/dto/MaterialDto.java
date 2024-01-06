package com.ws.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDto {

    private Long id;
    private String name;
    private Boolean status = true;
    private String description;
    private HeadquartersDto headquarters;
}
