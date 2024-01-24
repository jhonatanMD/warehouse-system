package com.ws.business;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileBusiness {

    void upload (MultipartFile file , Long headquarters);
}
