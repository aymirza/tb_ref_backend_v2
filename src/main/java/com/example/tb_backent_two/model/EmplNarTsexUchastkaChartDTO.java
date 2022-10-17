package com.example.tb_backent_two.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class EmplNarTsexUchastkaChartDTO
{
    private String tsex_uchastka;
    private Integer resultcount2;

    public EmplNarTsexUchastkaChartDTO(String tsex_uchastka, Integer resultcount2) {
        this.tsex_uchastka = tsex_uchastka;
        this.resultcount2 = resultcount2;
    }

    public EmplNarTsexUchastkaChartDTO() {

    }
}
