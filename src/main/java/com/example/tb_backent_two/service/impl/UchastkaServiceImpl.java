package com.example.tb_backent_two.service.impl;

import com.example.tb_backent_two.model.Uchastka;
import com.example.tb_backent_two.repository.UchastkaRepository;
import com.example.tb_backent_two.service.UchastkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UchastkaServiceImpl implements UchastkaService {
    @Autowired
    private UchastkaRepository uchastkaRepository;

    @Override
    public List<Uchastka> getAll() {
        return uchastkaRepository.findAll();
    }

    @Override
    public Uchastka saveUchastka(Uchastka uchastka) {
        return uchastkaRepository.save(uchastka);
    }

    @Override
    public void deleteByUchastka(Long id) {
        uchastkaRepository.deleteById(id);
    }

    @Override
    public List<Uchastka> findByName(String name) {
        return uchastkaRepository.findByName(name);
    }
}
