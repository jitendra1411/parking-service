/*
 * 1/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.parkingserviceweb;

import com.google.common.io.Files;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public static File multipartToFile(MultipartFile multipart) throws Exception {

        try {
            File convFile = createTempFile(multipart.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(convFile);
            fileOutputStream.write(multipart.getBytes());
            fileOutputStream.close();
            return convFile;
        } catch (IllegalStateException | IOException e) {
            throw new Exception(e);
        }
    }

    public static File createTempFile(String fileName) {

        return new File(Files.createTempDir(), fileName);

    }
}
