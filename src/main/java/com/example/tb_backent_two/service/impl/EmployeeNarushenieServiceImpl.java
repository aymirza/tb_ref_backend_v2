package com.example.tb_backent_two.service.impl;

import com.example.tb_backent_two.model.EmplNarUchastkaChartDTO;
import com.example.tb_backent_two.model.EmployeeNaruhsenie;
import com.example.tb_backent_two.repository.EmplUchastkaRepository;
import com.example.tb_backent_two.repository.EmployeeNarushenieRepository;
import com.example.tb_backent_two.service.EmployeeNarushenieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeNarushenieServiceImpl implements EmployeeNarushenieService {

    @Autowired
    private EmployeeNarushenieRepository employeeNarushenieRepository;
    @Autowired
    private EmplUchastkaRepository emplUchastkaRepository;


    @Value("${upload.path}")
    private String uploadPath;

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadPath));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload folder");
        }
    }

    @Override
    public EmployeeNaruhsenie saveEmplNar(String lastname, String firstname,
                                          String uchastka, String tsex_uchastka,
                                          String pravila, String narushenie,String date_narushenie,
                                          String predlojenie, MultipartFile file) throws IOException {
        try {
            Path root = Paths.get(uploadPath);
            if (!Files.exists(root)) {
                init();
            }
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
            String fullPath = uploadPath + file.getOriginalFilename();
            EmployeeNaruhsenie employeeNaruhsenie = new EmployeeNaruhsenie();
            employeeNaruhsenie.setLastname(lastname);
            employeeNaruhsenie.setFirstname(firstname);
            employeeNaruhsenie.setUchastka(uchastka);
            employeeNaruhsenie.setTsex_uchastka(tsex_uchastka);
            employeeNaruhsenie.setPravila(pravila);
            employeeNaruhsenie.setNarushenie(narushenie);
            employeeNaruhsenie.setImgurl(fullPath);
            employeeNaruhsenie.setImgfullname(file.getOriginalFilename());
            employeeNaruhsenie.setImgtype(file.getContentType());
            employeeNaruhsenie.setPredlojenie(predlojenie);
            employeeNaruhsenie.setDate_narushenie(date_narushenie);

            return employeeNarushenieRepository.save(employeeNaruhsenie);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file,Error: " + e.getMessage());
        }
    }

    public Resource loadFile(String imgfullname) {
        try {
            Path file = Paths.get(uploadPath)
                    .resolve(imgfullname);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public EmployeeNaruhsenie loadData(String imgfullname) {
        try {
            return employeeNarushenieRepository.getByImgfullname(imgfullname);

        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }



    @Override
    public List<Path> getAllEmplNar() {
        try {
            Path root = Paths.get(uploadPath);
            if (Files.exists(root)) {
                return Files.walk(root, 1)
                        .filter(path -> !path.equals(root))
                        .collect(Collectors.toList());
            }
            return Collections.emptyList();
        } catch (IOException e) {
            throw new RuntimeException("Could not list the files");
        }
    }


    @Override
    public List<EmployeeNaruhsenie> getAllEmplN() {
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

    // charts

    @Override
    public List<EmployeeNaruhsenie> allempln() {
        return employeeNarushenieRepository.findAll();
    }

    @Override
    public List<Object[]> findAllEmplNarUchastkaChartDTO(int y, int m) {

        return emplUchastkaRepository.findAllEmplNarUchastkaChartDTO(y,m);
    }

    @Override
    public List<Object[]> findAllEmplNarTsexUchastkaChartDTO(int y, int m) {

        return emplUchastkaRepository.findAllEmplNarTsexUchastkaChartDTO(y,m);
    }

    @Override
    public List<Object[]> findAllEmplNarPravilaChartDTO(int y, int m) {

        return emplUchastkaRepository.findAllEmplNarPravilaChartDTO(y,m);
    }

    @Override
    public List<Object[]> findAllEmplNarNarushenieChartDTO(int y, int m) {

        return emplUchastkaRepository.findAllEmplNarnarushenieChartDTO(y,m);
    }


}
