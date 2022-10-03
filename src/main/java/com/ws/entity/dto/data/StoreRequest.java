package com.ws.entity.dto.data;

import lombok.Data;

import java.util.Set;

@Data
public class StoreRequest {

    private Long id;
    private String name;
    private Boolean status = true;
    private Set<Long> headquarters;
}
