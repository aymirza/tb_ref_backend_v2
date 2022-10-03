package com.example.tb_backent_two.controller;

import com.example.tb_backent_two.model.FileData;
import com.example.tb_backent_two.model.UploadResponseMessage;
import com.example.tb_backent_two.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService){
        this.fileService=fileService;
    }

    @PostMapping
    public ResponseEntity<UploadResponseMessage> uploadFile(@RequestParam("file")MultipartFile file){
        try {
            fileService.save(file);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UploadResponseMessage("Uploaded the file successfully: "+file.getOriginalFilename()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new UploadResponseMessage("Could not upload the file: "+file.getOriginalFilename()));
        }
    }

    @DeleteMapping
    public void delete() {
        fileService.deleteAll();
    }

    @GetMapping
    public ResponseEntity<List<FileData>> getListFiles(){
        List<FileData> fileInfos = fileService.loadAll()
                .stream()
                .map(this::pathToFileData)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(fileInfos);
    }

    private FileData pathToFileData(Path path){
        FileData fileData = new FileData();
        String filename = path.getFileName()
                .toString();
        fileData.setFilename(filename);
        fileData.setUrl(MvcUriComponentsBuilder.fromMethodName(FileController.class,
                        "getFile",filename)
                .build()
                .toString());
        try {
            fileData.setSize(Files.size(path));
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Error: "+e.getMessage());
        }
        return fileData;
    }

    @GetMapping(value = "{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        Resource file = fileService.load(filename);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

}

