package com.example.tb_backent_two.service.impl;

import com.example.tb_backent_two.model.EmployeeNaruhsenie;
import com.example.tb_backent_two.repository.EmployeeNarushenieRepository;
import com.example.tb_backent_two.service.EmployeeNarushenieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class EmployeeNarushenieServiceImpl implements EmployeeNarushenieService {
    @Autowired
    private EmployeeNarushenieRepository employeeNarushenieRepository;

    private final String Path = "C:/ayka/safety_project/tb_backent_two/photos";


    @Override
    public EmployeeNaruhsenie saveEmplNar(String lastname, String firstname,
                                          String uchastka, String tsex_uchastka,
                                          String pravila, String narushenie,
                                          MultipartFile file) throws IOException {
        String fullPath = Path+file.getOriginalFilename();
        EmployeeNaruhsenie employeeNaruhsenie = new EmployeeNaruhsenie();
        employeeNaruhsenie.setLastname(lastname);
        employeeNaruhsenie.setFirstname(firstname);
        employeeNaruhsenie.setUchastka(uchastka);
        employeeNaruhsenie.setTsex_uchastka(tsex_uchastka);
        employeeNaruhsenie.setPravila(pravila);
        employeeNaruhsenie.setImg_url(fullPath);
        employeeNaruhsenie.setImg_fullname(file.getOriginalFilename());
        employeeNaruhsenie.setImg_type(file.getContentType());
        file.transferTo(new File(fullPath));
        return employeeNarushenieRepository.save(employeeNaruhsenie);
    }

    @Override
    public List<EmployeeNaruhsenie> getAllEmplNar() {
        return employeeNarushenieRepository.findAll();
    }

    @Override
    public EmployeeNaruhsenie getById(Long id) {
        return null;
    }

    @Override
    public EmployeeNaruhsenie updateEmplNar(EmployeeNaruhsenie employeeNaruhsenie) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
