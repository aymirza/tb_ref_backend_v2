package com.example.tb_backent_two.repository;

import com.example.tb_backent_two.model.Pravila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PravilaRepository extends JpaRepository<Pravila,Long> {
}
