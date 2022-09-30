package com.example.tb_backent_two.controller;

import com.example.tb_backent_two.model.EmployeeNaruhsenie;
import com.example.tb_backent_two.service.EmployeeNarushenieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EmployeeNarushenieController {

    @Autowired
    private EmployeeNarushenieService employeeNarushenieService;

    @PostMapping("/upload")
    public void uploadEmplNar(@RequestParam("lastname") String lastname, @RequestParam("firstname")String firstname,
                              @RequestParam("uchastka")String uchastka, @RequestParam("tsex_uchastka")String tsex_uchastka,
                              @RequestParam("pravila")String pravila, @RequestParam("narushenie")String narushenie,
                             @RequestParam("file")MultipartFile file) throws IOException {
        employeeNarushenieService.saveEmplNar(lastname, firstname, uchastka, tsex_uchastka, pravila, narushenie, file);
    }
    @GetMapping
    public List<EmployeeNaruhsenie> getAllEmplNar(){
        return employeeNarushenieService.getAllEmplNar();
    }

}
