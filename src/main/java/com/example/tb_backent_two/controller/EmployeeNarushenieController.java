package com.example.tb_backent_two.controller;

import com.example.tb_backent_two.model.*;
import com.example.tb_backent_two.service.EmployeeNarushenieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class EmployeeNarushenieController {

    @Autowired
    private EmployeeNarushenieService employeeNarushenieService;

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadEmplNar(@RequestParam("lastname") String lastname, @RequestParam("firstname") String firstname,
                                           @RequestParam("uchastka") String uchastka, @RequestParam("tsex_uchastka") String tsex_uchastka,
                                           @RequestParam("pravila") String pravila, @RequestParam("narushenie") String narushenie,
                                           @RequestParam("date_narushenie") String date_narushenie,
                                           @RequestParam("predlojenie") String predlojenie, @RequestParam(value = "file") MultipartFile file) throws IOException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeNarushenieService.saveEmplNar(lastname, firstname, uchastka, tsex_uchastka, pravila, narushenie, date_narushenie, predlojenie, file));
    }


    @GetMapping
    public ResponseEntity<List<EmplNarushenieDTO>> getAllEmplNar() {
        List<EmplNarushenieDTO> fileInfos = employeeNarushenieService.getAllEmplNar()
                .stream()
                .map(this::pathToFileData2)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(fileInfos);

    }

    private EmployeeNaruhsenie pathToFileData(Path path) {
        EmployeeNaruhsenie employeeNaruhsenie = new EmployeeNaruhsenie();
        String filename = path.getFileName().toString();

        employeeNaruhsenie.setImgfullname(filename);
        employeeNaruhsenie.setImgurl(MvcUriComponentsBuilder.fromMethodName(
                        EmployeeNarushenieController.class,
                        "getImage",
                        filename)
                .build()
                .toString());
        try {
            employeeNaruhsenie.setImgtype(Files.probeContentType(path));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return employeeNaruhsenie;
    }

    private EmplNarushenieDTO pathToFileData2(Path path) {
        EmployeeNaruhsenie employeeNaruhsenie = new EmployeeNaruhsenie();
        String filename = path.getFileName().toString();

        employeeNaruhsenie.setImgurl(MvcUriComponentsBuilder.fromMethodName(
                        EmployeeNarushenieController.class,
                        "getAllData",
                        filename)
                .build()
                .toString());
        EmployeeNaruhsenie employeeNaruhsenie1 = employeeNarushenieService.loadData(filename);
        String lastname = employeeNaruhsenie1.getLastname();
        String firstname = employeeNaruhsenie1.getFirstname();
        String uchastka = employeeNaruhsenie1.getUchastka();
        String tsex_uchastka = employeeNaruhsenie1.getTsex_uchastka();
        String pravila = employeeNaruhsenie1.getPravila();
        String narushenie = employeeNaruhsenie1.getNarushenie();
        String imgfullname = employeeNaruhsenie1.getImgfullname();
        String imgtype = employeeNaruhsenie1.getImgtype();
        String predlojenie = employeeNaruhsenie1.getPredlojenie();
        String date_naruhsenie = employeeNaruhsenie1.getDate_narushenie();

        EmplNarushenieDTO emplNarushenieDTO = new EmplNarushenieDTO();
        emplNarushenieDTO.setLastname(lastname);
        emplNarushenieDTO.setFirstname(firstname);
        emplNarushenieDTO.setUchastka(uchastka);
        emplNarushenieDTO.setTsex_uchastka(tsex_uchastka);
        emplNarushenieDTO.setPravila(pravila);
        emplNarushenieDTO.setNarushenie(narushenie);
        emplNarushenieDTO.setImgurl(MvcUriComponentsBuilder.fromMethodName(
                EmployeeNarushenieController.class, "getImage", imgfullname).build().toString());
        emplNarushenieDTO.setImgfullname(imgfullname);
        emplNarushenieDTO.setImgtype(imgtype);
        emplNarushenieDTO.setPredlojenie(predlojenie);
        emplNarushenieDTO.setDate_narushenie(String.valueOf(date_naruhsenie));

        return emplNarushenieDTO;
    }


    @GetMapping(value = "{imgfullname:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<?> getImage(@PathVariable String imgfullname) {
        Resource file = employeeNarushenieService.loadFile(imgfullname);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

    @GetMapping(value = "/file/{img_fullname:.+}")
    @ResponseBody
    public ResponseEntity<?> getAllData(@PathVariable String imgfullname) {
        EmployeeNaruhsenie file = employeeNarushenieService.loadData(imgfullname);
        EmployeeNaruhsenie employeeNaruhsenie = new EmployeeNaruhsenie();
        employeeNaruhsenie.setLastname(file.getLastname());

        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeNaruhsenie);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllEmplN() {
        EmployeeNaruhsenie employeeNaruhsenie = (EmployeeNaruhsenie) employeeNarushenieService.getAllEmplN();
        String lastname = employeeNaruhsenie.getLastname();
        EmplNarushenieDTO emplNarushenieDTO = new EmplNarushenieDTO();
        emplNarushenieDTO.setLastname(lastname);

        return new ResponseEntity<>(employeeNaruhsenie, HttpStatus.OK);
    }

    // charts

    @GetMapping("/a")
    public List<EmployeeNaruhsenie> getallempln() {
        return employeeNarushenieService.allempln();
    }

    @GetMapping("/uchastka/{y}/{m}")
    public ResponseEntity<?> countbydatenar(@PathVariable("y") int y, @PathVariable("m") int m) {
        List<Object[]> result = (employeeNarushenieService.findAllEmplNarUchastkaChartDTO(y, m));
        EmplNarUchastkaChartDTO em = null;
        List<EmplNarUchastkaChartDTO> list = new ArrayList<EmplNarUchastkaChartDTO>();
        Map<String, BigInteger> map = null;
        if (result != null && !result.isEmpty()) {
            map = new HashMap<String, BigInteger>();
            for (Object[] objects : result) {
                map.put((String) objects[0], (BigInteger) objects[1]);
                em = new EmplNarUchastkaChartDTO((String) objects[0], ((BigInteger) objects[1]).intValue());
                System.out.println(em);
                list.add(em);
            }
        }
        System.out.println("list\n" + list);
        return new ResponseEntity<>(list.stream(), HttpStatus.OK);
    }

    @GetMapping("/tsex/{y}/{m}")
    public ResponseEntity<?> countbydatenartsex(@PathVariable("y") int y, @PathVariable("m") int m) {
        List<Object[]> result = (employeeNarushenieService.findAllEmplNarTsexUchastkaChartDTO(y, m));
        EmplNarTsexUchastkaChartDTO em = null;
        List<EmplNarTsexUchastkaChartDTO> list = new ArrayList<EmplNarTsexUchastkaChartDTO>();
        Map<String, BigInteger> map = null;
        if (result != null && !result.isEmpty()) {
            map = new HashMap<String, BigInteger>();
            for (Object[] objects : result) {
                map.put((String) objects[0], (BigInteger) objects[1]);
                em = new EmplNarTsexUchastkaChartDTO((String) objects[0], ((BigInteger) objects[1]).intValue());
                System.out.println(em);
                list.add(em);
            }
        }
        System.out.println("list\n" + list);
        return new ResponseEntity<>(list.stream(), HttpStatus.OK);
    }

    @GetMapping("/pravila/{y}/{m}")
    public ResponseEntity<?> countbydatenarpravila(@PathVariable("y") int y, @PathVariable("m") int m) {
        List<Object[]> result = (employeeNarushenieService.findAllEmplNarPravilaChartDTO(y, m));
        EmplNarPravilaChartDTO em = null;
        List<EmplNarPravilaChartDTO> list = new ArrayList<EmplNarPravilaChartDTO>();
        Map<String, BigInteger> map = null;
        if (result != null && !result.isEmpty()) {
            map = new HashMap<String, BigInteger>();
            for (Object[] objects : result) {
                map.put((String) objects[0], (BigInteger) objects[1]);
                em = new EmplNarPravilaChartDTO((String) objects[0], ((BigInteger) objects[1]).intValue());
                System.out.println(em);
                list.add(em);
            }
        }
        System.out.println("list\n" + list);
        return new ResponseEntity<>(list.stream(), HttpStatus.OK);
    }

    @GetMapping("/narushenie/{y}/{m}")
    public ResponseEntity<?> countbydatenarnarushenie(@PathVariable("y") int y, @PathVariable("m") int m) {
        List<Object[]> result = (employeeNarushenieService.findAllEmplNarNarushenieChartDTO(y, m));
        EmplNarTsexNarushenieChartDTO em = null;
        List<EmplNarTsexNarushenieChartDTO> list = new ArrayList<EmplNarTsexNarushenieChartDTO>();
        Map<String, BigInteger> map = null;
        if (result != null && !result.isEmpty()) {
            map = new HashMap<String, BigInteger>();
            for (Object[] objects : result) {
                map.put((String) objects[0], (BigInteger) objects[1]);
                em = new EmplNarTsexNarushenieChartDTO((String) objects[0], ((BigInteger) objects[1]).intValue());
                System.out.println(em);
                list.add(em);
            }
        }
        System.out.println("list\n" + list);
        return new ResponseEntity<>(list.stream(), HttpStatus.OK);
    }
}
