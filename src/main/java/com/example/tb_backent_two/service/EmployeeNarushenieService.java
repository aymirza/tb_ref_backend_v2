package com.example.tb_backent_two.service;

import com.example.tb_backent_two.model.EmployeeNaruhsenie;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Component
public interface EmployeeNarushenieService {
    EmployeeNaruhsenie saveEmplNar(String lastname,
                                   String firstname,
                                   String uchastka,
                                   String tsex_uchastka,
                                   String pravila,
                                   String narushenie,
                                   MultipartFile file
    ) throws IOException;

    List<Path> getAllEmplNar(

    );

    EmployeeNaruhsenie getById(Long id);

    EmployeeNaruhsenie updateEmplNar(EmployeeNaruhsenie employeeNaruhsenie);

    void deleteById(Long id);

    Resource loadFile(String filename);

}
