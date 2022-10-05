package com.example.tb_backent_two.controller;

import com.example.tb_backent_two.model.EmplNarushenieDTO;
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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EmployeeNarushenieController {

    @Autowired
    private EmployeeNarushenieService employeeNarushenieService;

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadEmplNar(@RequestParam("lastname") String lastname, @RequestParam("firstname") String firstname,
                                           @RequestParam("uchastka") String uchastka, @RequestParam("tsex_uchastka") String tsex_uchastka,
                                           @RequestParam("pravila") String pravila, @RequestParam("narushenie") String narushenie,
                                           @RequestParam(value = "file") MultipartFile file) throws IOException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeNarushenieService.saveEmplNar(lastname, firstname, uchastka, tsex_uchastka, pravila, narushenie, file));
    }


    @GetMapping
    public ResponseEntity<List<EmplNarushenieDTO>> getAllEmplNar() {
        List<EmplNarushenieDTO> fileInfos = employeeNarushenieService.getAllEmplNar()
                .stream()
                .map(this::pathToFileData2)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(fileInfos);

    }

    private EmployeeNaruhsenie pathToFileData(Path path) {
        EmployeeNaruhsenie employeeNaruhsenie = new EmployeeNaruhsenie();
        String filename = path.getFileName().toString();

        employeeNaruhsenie.setImg_fullname(filename);
        employeeNaruhsenie.setImg_url(MvcUriComponentsBuilder.fromMethodName(
                        EmployeeNarushenieController.class,
                        "getImage",
                        filename)
                .build()
                .toString());
        try {
            employeeNaruhsenie.setImg_type(Files.probeContentType(path));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return employeeNaruhsenie;
    }

    private EmplNarushenieDTO pathToFileData2(Path path) {
        EmployeeNaruhsenie employeeNaruhsenie = new EmployeeNaruhsenie();
        String filename = path.getFileName().toString();

        employeeNaruhsenie.setImg_url(MvcUriComponentsBuilder.fromMethodName(
                        EmployeeNarushenieController.class,
                        "getAllData",
                        filename)
                .build()
                .toString());
        EmployeeNaruhsenie employeeNaruhsenie1 = employeeNarushenieService.loadData(filename);
        String lastname = employeeNaruhsenie1.getLastname();
        String firstname = employeeNaruhsenie1.getFirstname();
        String uchastka = employeeNaruhsenie1.getUchastka();
        String tsex_uchastka = employeeNaruhsenie1.getTsex_uchastka();
        String pravila = employeeNaruhsenie1.getPravila();
        String narushenie = employeeNaruhsenie1.getNarushenie();
        String img_url = employeeNaruhsenie1.getImg_url();
        String img_fullname = employeeNaruhsenie1.getImg_fullname();
        String img_type = employeeNaruhsenie1.getImg_type();

        EmplNarushenieDTO emplNarushenieDTO = new EmplNarushenieDTO();
        emplNarushenieDTO.setLastname(lastname);
        emplNarushenieDTO.setFirstname(firstname);
        emplNarushenieDTO.setUchastka(uchastka);
        emplNarushenieDTO.setTsex_uchastka(tsex_uchastka);
        emplNarushenieDTO.setPravila(pravila);
        emplNarushenieDTO.setNarushenie(narushenie);
        emplNarushenieDTO.setImg_url(MvcUriComponentsBuilder.fromMethodName(
                EmployeeNarushenieController.class, "getImage",img_fullname).build().toString());
        emplNarushenieDTO.setImg_fullname(img_fullname);
        emplNarushenieDTO.setImg_type(img_type);

        return emplNarushenieDTO;
    }


    @GetMapping(value = "{img_fullname:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<?> getImage(@PathVariable String img_fullname) {
        Resource file = employeeNarushenieService.loadFile(img_fullname);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

    @GetMapping(value = "/file/{img_fullname:.+}")
    @ResponseBody
    public ResponseEntity<?> getAllData(@PathVariable String img_fullname) {
        EmployeeNaruhsenie file = employeeNarushenieService.loadData(img_fullname);
        EmployeeNaruhsenie employeeNaruhsenie = new EmployeeNaruhsenie();
        employeeNaruhsenie.setLastname(file.getLastname());

        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeNaruhsenie);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmplN() {
        EmployeeNaruhsenie employeeNaruhsenie = (EmployeeNaruhsenie) employeeNarushenieService.getAllEmplN();
        String lastname = employeeNaruhsenie.getLastname();
        EmplNarushenieDTO emplNarushenieDTO = new EmplNarushenieDTO();
        emplNarushenieDTO.setLastname(lastname);

        return new ResponseEntity<>(employeeNaruhsenie, HttpStatus.OK);
    }


}
