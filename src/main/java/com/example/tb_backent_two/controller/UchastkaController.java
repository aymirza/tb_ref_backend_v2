package com.example.tb_backent_two.controller;

import com.example.tb_backent_two.model.Uchastka;
import com.example.tb_backent_two.service.UchastkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/uchastka")
public class UchastkaController {

    @Autowired
    private UchastkaService uchastkaService;

    @GetMapping
    public List<Uchastka> getAllUchastka() {
        return uchastkaService.getAll();
    }

    @PostMapping
    public Uchastka saveUchastka(Uchastka uchastka) {
        return uchastkaService.saveUchastka(uchastka);
    }

    @DeleteMapping("/{id}")
    public void deleteUchastka(@PathVariable Long id) {
        uchastkaService.deleteByUchastka(id);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Uchastka>> getByNameUchastka(@PathVariable String name) {
        return new ResponseEntity<>(uchastkaService.findByName(name), HttpStatus.OK);
    }
}
