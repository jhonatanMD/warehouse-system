package com.ws.entity.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadExcelDataDto {

    private String code;
    private String name;
    private String brand;
    private String category;
    private String material;
    private String model;
    private String type;
    private String status;
    private String store;
    private String stock;
    private String price;


    public boolean areAllFieldsNotNull() {
        return this.name != null &&
                this.code != null &&
                this.brand != null &&
                this.category != null &&
                this.material != null &&
                //model != null &&
                this.type != null &&
                this.store != null &&
                this.stock != null &&
                this.price != null;
    }

}
