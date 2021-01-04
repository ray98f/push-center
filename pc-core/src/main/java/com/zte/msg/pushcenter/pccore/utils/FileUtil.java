package com.zte.msg.pushcenter.pccore.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/29 16:41
 */
public class FileUtil {

    public static String readStringFromFile(String path) {
        File file = new File(path);
        return readStringFromFile(file);
    }

    public static String readStringFromFile(File file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            while ((s = br.readLine()) != null) {
                result.append(System.lineSeparator()).append(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        deleteFile(file);
        return result.toString();
    }

    public static String readStringFromFile(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String prefix = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));
        File file = File.createTempFile(String.valueOf(UUID.randomUUID()), prefix);
        multipartFile.transferTo(file);
        return readStringFromFile(file);
    }

    private static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
