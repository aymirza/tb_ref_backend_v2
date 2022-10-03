package com.example.tb_backent_two.controller;

import com.example.tb_backent_two.model.EmployeeNaruhsenie;
import com.example.tb_backent_two.service.EmployeeNarushenieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EmployeeNarushenieController {

    @Autowired
    private EmployeeNarushenieService employeeNarushenieService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadEmplNar(@RequestParam("lastname") String lastname, @RequestParam("firstname")String firstname,
                                        @RequestParam("uchastka")String uchastka, @RequestParam("tsex_uchastka")String tsex_uchastka,
                                        @RequestParam("pravila")String pravila, @RequestParam("narushenie")String narushenie,
                                        @RequestParam("file")MultipartFile file) throws IOException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeNarushenieService.saveEmplNar(lastname, firstname, uchastka, tsex_uchastka, pravila, narushenie, file));
    }

    @GetMapping(value = "{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename){
        Resource file = employeeNarushenieService.loadFile(filename);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeNaruhsenie>> getAllEmplNar(){
        List<EmployeeNaruhsenie> fileInfos = employeeNarushenieService.getAllEmplNar()
                .stream()
                .map(this::pathToFileData)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(fileInfos);

    }

    private EmployeeNaruhsenie pathToFileData(Path path){
        EmployeeNaruhsenie employeeNaruhsenie = new EmployeeNaruhsenie();
        String filename = path.getFileName().toString();
        employeeNaruhsenie.setImg_fullname(filename);
        employeeNaruhsenie.setImg_url(MvcUriComponentsBuilder.fromMethodName(EmployeeNaruhsenie.class,
                "getFile",filename)
                .build()
                .toString());
        try {
            employeeNaruhsenie.setImg_type(Files.probeContentType(path));
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Error: "+e.getMessage());
        }return employeeNaruhsenie;
    }

}
