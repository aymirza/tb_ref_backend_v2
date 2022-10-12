package com.example.tb_backent_two.repository;

import com.example.tb_backent_two.model.Uchastka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UchastkaRepository extends JpaRepository<Uchastka, Long> {
    @Query(value = "select u from Uchastka u where u.name like %?1%")
    List<Uchastka> findByName(String name);
}
