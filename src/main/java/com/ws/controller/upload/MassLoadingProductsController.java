package com.ws.controller.upload;

import com.ws.business.impl.UploadFileBusiness;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MassLoadingProductsController {

    private final UploadFileBusiness uploadFileBusiness;

    @PostMapping(value = "/import", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> importFile(@RequestParam("file") MultipartFile file ,
                                             @RequestAttribute("headquarters") Long headquarters) {
        uploadFileBusiness.upload(file, headquarters);
        return ResponseEntity.ok().build();
    }

}
