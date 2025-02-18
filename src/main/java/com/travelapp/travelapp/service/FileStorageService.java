package com.travelapp.travelapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileStorageService {

    @Value("${app.files.root}")
    private String ROOT_LOCATION;

    public byte[] getFileBytes(long userId, String location, String fileName){
        final String FILE_LOCATION = String.format("%s\\%s\\%d\\%s\\", ROOT_LOCATION, "\\users", userId, location);
        try{
            File file = new File(FILE_LOCATION + File.separator + fileName);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = bis.readAllBytes();
            bis.close();
            return bytes;
        }
        catch (IOException e){
//            System.out.println("File not found");
        }
        return new byte[]{};
    }

    public void storeFile(long userId, String location, String fileName, byte[] bytes){
        final String FILE_LOCATION = String.format("%s\\%s\\%d\\%s\\", ROOT_LOCATION, "\\users", userId, location);
        try{
            Path path = Path.of(FILE_LOCATION);
            File file = new File(FILE_LOCATION + File.separator + fileName);
            Files.createDirectories(path);

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(bytes);
            bos.close();
        }
        catch (IOException e){
            System.out.println("Error uploading file");
        }
    }


    public void deleteFile(long userId, String location, String fileName){
        final String FILE_LOCATION = String.format("%s\\%s\\%d\\%s\\", ROOT_LOCATION, "\\users", userId, location);
        try{
            File file = new File(FILE_LOCATION + File.separator + fileName);
            Files.deleteIfExists(Path.of(file.getPath()));
        }
        catch (IOException e){
            System.out.println("Error deleting file");
        }
    }


}
