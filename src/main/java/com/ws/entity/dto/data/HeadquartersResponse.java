package com.ws.entity.dto.data;

import com.ws.entity.dto.HeadquartersDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeadquartersResponse {
    private Long id;
    private String name;


    public  HeadquartersResponse(HeadquartersDto headquartersDto){
        this.id = headquartersDto.getId();
        this.name = headquartersDto.getName();
    }

}
