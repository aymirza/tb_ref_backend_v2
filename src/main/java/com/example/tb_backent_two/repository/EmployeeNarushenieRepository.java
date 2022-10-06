package com.example.tb_backent_two.repository;

import com.example.tb_backent_two.model.EmployeeNaruhsenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeNarushenieRepository extends JpaRepository<EmployeeNaruhsenie, Long> {

    @Query("select empln from EmployeeNaruhsenie empln where empln.imgfullname=?1")
    EmployeeNaruhsenie getByImgfullname(String imgfullname);
}
