package com.travelapp.travelapp.restcontroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/practice")
public class PracticeController {

    @PostMapping("/file")
    public void uploadFile(@RequestParam MultipartFile file){
        try{
            System.out.println(Arrays.toString(file.getBytes()));
//            writeFile("C:\\Users\\paulb\\Desktop\\picture from app.jpg", file.getBytes());
        }
        catch (IOException e){
            System.out.println("Error receiving file");
        }
    }

//    public ResponseEntity<Resource> downloadFile(){
//
//    }


    private void writeFile(String fileURI, byte[] bytes){
        try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileURI))){
            bos.write(bytes);
        }
        catch (IOException e){
            System.out.println("Error writing file");
        }
    }

}