package com.example.tb_backent_two.service;

import com.example.tb_backent_two.model.TsexUchastka;

import java.util.List;

public interface TsexUchastkaService {
    List<TsexUchastka> getAllTsexUchastka();
    TsexUchastka saveTsexUchastka(TsexUchastka uchastka);

    void deleteByTsex(Long id);



}
