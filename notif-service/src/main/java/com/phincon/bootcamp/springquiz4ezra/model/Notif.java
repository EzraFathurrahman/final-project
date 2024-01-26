package com.phincon.bootcamp.springquiz4ezra.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "notif")
public class Notif implements Persistable {

    @Id
    String id;
    String status;


    @Override
    public boolean isNew() {

        return true;
    }}
