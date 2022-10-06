package com.example.tb_backent_two.service;

import com.example.tb_backent_two.model.Narushenie;
import com.example.tb_backent_two.model.Pravila;

import java.util.List;

public interface NarushenieService {

    List<Narushenie> getAllNarushenie();
    Narushenie saveNarushenie(Narushenie narushenie);
    void deleteByIdNarushenie(Long id);
}
