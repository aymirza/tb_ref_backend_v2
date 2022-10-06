package com.example.tb_backent_two.service;

import com.example.tb_backent_two.model.Uchastka;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UchastkaService {
    List<Uchastka> getAll();
    Uchastka saveUchastka(Uchastka uchastka);
    void deleteByUchastka(Long id);

}
