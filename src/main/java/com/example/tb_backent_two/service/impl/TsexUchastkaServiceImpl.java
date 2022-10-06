package com.example.tb_backent_two.service.impl;

import com.example.tb_backent_two.model.TsexUchastka;
import com.example.tb_backent_two.repository.TsexUchastkaRepository;
import com.example.tb_backent_two.service.TsexUchastkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TsexUchastkaServiceImpl implements TsexUchastkaService {

    @Autowired
    private TsexUchastkaRepository tsexUchastkaRepository;

    @Override
    public List<TsexUchastka> getAllTsexUchastka() {
        return tsexUchastkaRepository.findAll();
    }

    @Override
    public TsexUchastka saveTsexUchastka(TsexUchastka uchastka) {
        return tsexUchastkaRepository.save(uchastka);
    }

    @Override
    public void deleteByTsex(Long id) {
        tsexUchastkaRepository.deleteById(id);
    }
}
