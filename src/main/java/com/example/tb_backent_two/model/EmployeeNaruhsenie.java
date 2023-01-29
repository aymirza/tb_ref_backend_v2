package com.example.tb_backent_two.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "empl_narushenie")
public class EmployeeNaruhsenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="empl_narushenie_id")
    private Long empl_narushenie_id;
    private String lastname;
    private String firstname;
    private String uchastka;
    private String tsex_uchastka;
    private String pravila;
    private String narushenie;
    private String imgurl;
    private String imgfullname;
    private String imgtype;
    private String date_narushenie;
    private String predlojenie;


}

