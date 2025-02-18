package com.travelapp.travelapp.practice;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/practice")
public class PracticeController {


    @PostMapping("/someEndpoint/{id}")
    public void justSomeEndpoint(@PathVariable long id,
                                 @RequestParam boolean delete){
        System.out.println(id);
        System.out.println(delete);
    }


    @PostMapping("/file")
    public void upload(@RequestParam List<MultipartFile> files) throws IOException {
        System.out.println(files.size());
    }

//    @GetMapping("/file")
//    public FileInfoDTOGet downloadFile() throws IOException{
//        String filePath = "C:\\Users\\paulb\\Desktop\\Files For SQLite\\p2.jpg";
//        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
//        Resource file = new InputStreamResource(bis);
//
//        return new FileInfoDTOGet("Paul", bis.readAllBytes());
//    }

}