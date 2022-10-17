package com.example.tb_backent_two.repository;

import com.example.tb_backent_two.model.EmplNarUchastkaChartDTO;
import com.example.tb_backent_two.model.EmployeeNaruhsenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeNarushenieRepository extends JpaRepository<EmployeeNaruhsenie, Long> {

    @Query("select empln from EmployeeNaruhsenie empln where empln.imgfullname=?1")
    EmployeeNaruhsenie getByImgfullname(String imgfullname);

    @Query("select count(empln.uchastka) as uchastka_count from EmployeeNaruhsenie empln where year(empln.date_narushenie)=?1 and month(empln.date_narushenie) =?2 group by empln.uchastka")
    int countByDate_narushenie(int y, int m);

    @Query(value = "select empln.uchastka, count(*) as count  from empl_narushenie empln where year(empln.date_narushenie)=?1 and month(empln.date_narushenie) =?2  group by empln.uchastka ", nativeQuery = true )
    Collection<EmplNarUchastkaChartDTO> findAll(int y, int m);





}
