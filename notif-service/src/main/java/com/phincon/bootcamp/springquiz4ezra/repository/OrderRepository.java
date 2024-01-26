package com.phincon.bootcamp.springquiz4ezra.repository;

import com.phincon.bootcamp.springquiz4ezra.model.Notif;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
public interface OrderRepository extends R2dbcRepository<Notif,String> {


}
