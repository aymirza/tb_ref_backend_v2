package com.example.tb_backent_two.service.impl;

import com.example.tb_backent_two.model.Pravila;
import com.example.tb_backent_two.repository.PravilaRepository;
import com.example.tb_backent_two.service.PravilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PravilaServiceImpl implements PravilaService {
    @Autowired
    private PravilaRepository pravilaRepository;

    @Override
    public List<Pravila> getAllPravila() {
        return pravilaRepository.findAll();
    }

    @Override
    public Pravila savePravila(Pravila pravila) {
        return pravilaRepository.save(pravila);
    }

    @Override
    public void deleteByIdPravila(Long id) {
        pravilaRepository.deleteById(id);
    }
}
