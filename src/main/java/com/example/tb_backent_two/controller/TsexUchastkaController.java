package com.example.tb_backent_two.controller;

import com.example.tb_backent_two.model.TsexUchastka;
import com.example.tb_backent_two.service.TsexUchastkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/tsex")
public class TsexUchastkaController {

    @Autowired
    private TsexUchastkaService tsexUchastkaService;

    @GetMapping
    public ResponseEntity<List<TsexUchastka>> getAllTsexUchastka(){
        return new ResponseEntity<>(tsexUchastkaService.getAllTsexUchastka(), HttpStatus.OK);
    }
    @PostMapping
    public TsexUchastka createTsexUchastka(TsexUchastka uchastka){
        return tsexUchastkaService.saveTsexUchastka(uchastka);
    }
    @DeleteMapping("/{id}")
    public void deleteTsex(@PathVariable Long id){
        tsexUchastkaService.deleteByTsex(id);
    }
}
