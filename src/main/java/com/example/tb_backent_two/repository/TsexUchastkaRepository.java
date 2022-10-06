package com.example.tb_backent_two.repository;

import com.example.tb_backent_two.model.TsexUchastka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TsexUchastkaRepository extends JpaRepository<TsexUchastka, Long> {
}
