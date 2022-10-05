package com.example.tb_backent_two.repository;

import com.example.tb_backent_two.model.Uchastka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UchastkaRepository extends JpaRepository<Uchastka, Long> {
}
