package com.phincon.bootcamp.springquiz4ezra.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Setter
@Getter
public class Steps {

    @Id
    String id;
    String cfgId;
    String step;
    String priority;
    String action;

}
