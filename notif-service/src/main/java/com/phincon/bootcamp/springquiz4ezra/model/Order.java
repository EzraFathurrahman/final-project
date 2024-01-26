package com.phincon.bootcamp.springquiz4ezra.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Table(name = "orders")
public class Order implements Persistable, Serializable {

    @Id
    String id;
    String product;
    String price;
    String status;
    String action;
    String step;
    @Column(value = "trxid")
    @JsonProperty(value = "trxid")
    String trxid;

    @Override
    public boolean isNew() {

        return true;
    }}
