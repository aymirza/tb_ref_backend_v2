package com.example.tb_backent_two.controller;

import com.example.tb_backent_two.model.Narushenie;
import com.example.tb_backent_two.service.NarushenieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/narushenie")
public class NarushenieController {
    @Autowired
    private NarushenieService narushenieService;

    @GetMapping
    public ResponseEntity<List<Narushenie>> getAllNarushenie(){
        return new ResponseEntity<>(narushenieService.getAllNarushenie(), HttpStatus.OK);
    }

    @PostMapping
    public Narushenie createNarushenie(Narushenie narushenie){
        return narushenieService.saveNarushenie(narushenie);
    }

    @DeleteMapping("/{id}")
    public void deleteNarushenie(@PathVariable Long id){
        narushenieService.deleteByIdNarushenie(id);
    }
}
