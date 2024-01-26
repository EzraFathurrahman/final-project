package com.phincon.bootcamp.springquiz4ezra.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Setter
@Getter
@Table(name = "transactions")
public class Trx implements Persistable {

    @Id
    String id;
    String action;
    String step;
    String status;


    @Override
    public boolean isNew() {

        return true;
    }
}