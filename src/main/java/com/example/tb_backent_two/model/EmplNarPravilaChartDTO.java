package com.example.tb_backent_two.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class EmplNarPravilaChartDTO
{
    private String pravila;
    private Integer resultcount3;

    public EmplNarPravilaChartDTO(String pravila, Integer resultcount3) {
        this.pravila = pravila;
        this.resultcount3 = resultcount3;
    }

    public EmplNarPravilaChartDTO() {

    }
}
