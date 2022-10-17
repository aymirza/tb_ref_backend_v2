package com.example.tb_backent_two.repository;

import com.example.tb_backent_two.model.EmployeeNaruhsenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EmplUchastkaRepository extends JpaRepository<EmployeeNaruhsenie, String> {

    @Query(value = "select empln.uchastka, count(*) as resultcount from empl_narushenie empln where year(empln.date_narushenie)=?1 and month(empln.date_narushenie) =?2  group by empln.uchastka ", nativeQuery = true)
    List<Object[]> findAllEmplNarUchastkaChartDTO(int y, int m);

    @Query(value = "select empln.tsex_uchastka, count(*) as resultcount2 from empl_narushenie empln where year(empln.date_narushenie)=?1 and month(empln.date_narushenie) =?2  group by empln.tsex_uchastka ", nativeQuery = true)
    List<Object[]> findAllEmplNarTsexUchastkaChartDTO(int y, int m);

    @Query(value = "select empln.pravila, count(*) as resultcount3 from empl_narushenie empln where year(empln.date_narushenie)=?1 and month(empln.date_narushenie) =?2  group by empln.pravila ", nativeQuery = true)
    List<Object[]> findAllEmplNarPravilaChartDTO(int y, int m);

    @Query(value = "select empln.narushenie, count(*) as resultcount4 from empl_narushenie empln where year(empln.date_narushenie)=?1 and month(empln.date_narushenie) =?2  group by empln.narushenie ", nativeQuery = true)
    List<Object[]> findAllEmplNarnarushenieChartDTO(int y, int m);
}
