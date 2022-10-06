package com.example.tb_backent_two.service;

import com.example.tb_backent_two.model.Pravila;
import com.example.tb_backent_two.model.TsexUchastka;

import java.util.List;

public interface PravilaService {
    List<Pravila> getAllPravila();
    Pravila savePravila(Pravila pravila);
    void deleteByIdPravila(Long id);

}
