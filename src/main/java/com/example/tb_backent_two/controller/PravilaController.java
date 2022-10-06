package com.example.tb_backent_two.controller;

import com.example.tb_backent_two.model.Pravila;
import com.example.tb_backent_two.service.PravilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pravila")
public class PravilaController {
    @Autowired
    private PravilaService pravilaService;

    @GetMapping
    public ResponseEntity<List<Pravila>> getAllPravila(){
        return new ResponseEntity<>(pravilaService.getAllPravila(), HttpStatus.OK);
    }
    @PostMapping
    public Pravila createPravila(Pravila pravila){
        return pravilaService.savePravila(pravila);
    }

    @DeleteMapping("/{id}")
    public void deletePravila(@PathVariable Long id){
        pravilaService.deleteByIdPravila(id);
    }
}
