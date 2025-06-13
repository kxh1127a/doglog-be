package com.example.doglogbe.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class CommonFile {
    public static File multipartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = File.createTempFile("upload", "_"+ multipartFile.getOriginalFilename());
        convFile.deleteOnExit();
        multipartFile.transferTo(convFile);
        return convFile;
    }
}
