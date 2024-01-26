package com.phincon.bootcamp.springquiz4ezra.repository;

import com.phincon.bootcamp.springquiz4ezra.model.Order;
import com.phincon.bootcamp.springquiz4ezra.model.Trx;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
public interface TrxRepository extends R2dbcRepository<Trx,String> {
    @Query("INSERT INTO transactions(trxId, action, step, status) VALUES (:trxId, :action, :step, :status)")
    Mono<Trx> savenew(@Param("trxId") String trxId, @Param("action") String action, @Param("step") String step, @Param("status") String status);
}
