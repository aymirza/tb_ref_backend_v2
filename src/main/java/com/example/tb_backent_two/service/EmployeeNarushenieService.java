package com.example.tb_backent_two.service;

import com.example.tb_backent_two.model.EmplNarUchastkaChartDTO;
import com.example.tb_backent_two.model.EmployeeNaruhsenie;
import org.springframework.core.io.Resource;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public interface EmployeeNarushenieService {
    EmployeeNaruhsenie saveEmplNar(String lastname,
                                   String firstname,
                                   String uchastka,
                                   String tsex_uchastka,
                                   String pravila,
                                   String narushenie,
                                   String date_narushenie,
                                   String predlojenie,
                                   MultipartFile file
    ) throws IOException;

    List<Path> getAllEmplNar(
    );
    List<EmployeeNaruhsenie> getAllEmplN(
    );

    EmployeeNaruhsenie getById(Long id);

    EmployeeNaruhsenie updateEmplNar(EmployeeNaruhsenie employeeNaruhsenie);

    void deleteById(Long id);

    Resource loadFile(String imgfullname);
    EmployeeNaruhsenie loadData(String imgfullname);


    // charts
    List<EmployeeNaruhsenie> allempln();



    List<Object[]> findAllEmplNarUchastkaChartDTO(int y, int m);
    List<Object[]> findAllEmplNarTsexUchastkaChartDTO(int y, int m);
    List<Object[]> findAllEmplNarPravilaChartDTO(int y, int m);
    List<Object[]> findAllEmplNarNarushenieChartDTO(int y, int m);

}
