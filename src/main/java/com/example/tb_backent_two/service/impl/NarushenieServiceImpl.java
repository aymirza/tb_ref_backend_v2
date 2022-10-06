package com.example.tb_backent_two.service.impl;

import com.example.tb_backent_two.model.Narushenie;
import com.example.tb_backent_two.repository.NarushenieRepository;
import com.example.tb_backent_two.service.NarushenieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NarushenieServiceImpl implements NarushenieService {
    @Autowired
    private NarushenieRepository narushenieRepository;

    @Override
    public List<Narushenie> getAllNarushenie() {
        return narushenieRepository.findAll();
    }

    @Override
    public Narushenie saveNarushenie(Narushenie narushenie) {
        return narushenieRepository.save(narushenie);
    }

    @Override
    public void deleteByIdNarushenie(Long id) {
        narushenieRepository.deleteById(id);
    }
}
