package com.example.tb_backent_two.model;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Data
public class EmplNarUchastkaChartDTO
{
    private String uchastka;
    private Integer resultcount;

    public EmplNarUchastkaChartDTO(String uchastka, Integer resultcount) {
        this.uchastka = uchastka;
        this.resultcount = resultcount;
    }

    public EmplNarUchastkaChartDTO() {

    }
}
