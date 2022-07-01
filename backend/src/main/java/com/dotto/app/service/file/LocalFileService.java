package com.dotto.app.service.file;

import com.dotto.app.exception.FileUploadFailureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class LocalFileService implements FileService{

    @Value("${upload.image.location}")
    private String location;

    @PostConstruct
    void postConstruct(){
        File dir = new File(location);
        if(!dir.exists())
            dir.mkdirs();
    }

    @Override
    public void upload(MultipartFile file, String fileName) {
        try{
            file.transferTo(new File(location + fileName));
        }catch (IOException e){
            throw new FileUploadFailureException();
        }

    }

    @Override
    public void deleted(String fileName) {
        new File(location+fileName).delete();
    }
}
