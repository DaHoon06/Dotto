package com.dotto.app.service.file;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocalFileServiceTest {

    LocalFileService localFileService = new LocalFileService();
    String testLocation = new File("src/test/resources/static").getAbsolutePath()+"/";

    @BeforeEach
    void beforeEach() throws IOException {
        ReflectionTestUtils.setField(localFileService,"location",testLocation);
        FileUtils.cleanDirectory(new File(testLocation));
    }

    @Test
    void uploadTest() {
        //given
        MultipartFile file = new MockMultipartFile("myfile","myfile.txt", MediaType.TEXT_PLAIN_VALUE,"test".getBytes());
        String fileName = "testFile.txt";

        //when
        localFileService.upload(file,fileName);

        //then
        assertThat(isExist(testLocation+fileName)).isTrue();
    }

    @Test
    void deletedTest(){
        //given
        MultipartFile file = new MockMultipartFile("myfile", "myfile.txt", MediaType.TEXT_PLAIN_VALUE,"test".getBytes());
        String fileName = "testFile.txt";
        localFileService.upload(file, fileName);
        boolean before = isExist(testLocation+fileName);

        //when
        localFileService.deleted(fileName);

        //then
        boolean after = isExist(testLocation+fileName);

        assertThat(before).isTrue();
        assertThat(after).isFalse();
    }

    boolean isExist(String filePath){
        return new File(filePath).exists();
    }
}