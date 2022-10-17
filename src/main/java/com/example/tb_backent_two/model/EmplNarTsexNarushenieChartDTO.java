package com.example.tb_backent_two.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class EmplNarTsexNarushenieChartDTO
{
    private String narushenie;
    private Integer resultcount4;

    public EmplNarTsexNarushenieChartDTO(String narushenie, Integer resultcount4) {
        this.narushenie = narushenie;
        this.resultcount4 = resultcount4;
    }

    public EmplNarTsexNarushenieChartDTO() {

    }
}
