package com.example.tb_backent_two.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmplNarushenieDTO {
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
