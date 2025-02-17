package com.travelapp.travelapp.practice;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/practice")
public class PracticeController {


    @GetMapping("/file")
    public FileInfoDTOGet downloadFile() throws IOException{
        String filePath = "C:\\Users\\paulb\\Desktop\\Files For SQLite\\p2.jpg";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
        Resource file = new InputStreamResource(bis);

        return new FileInfoDTOGet("Paul", bis.readAllBytes());
    }

}