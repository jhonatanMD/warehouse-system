package com.ws.entity.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidateExcelDataDto implements Serializable {
    private ProductDataDto productData;
    private List<String> errors;

}
