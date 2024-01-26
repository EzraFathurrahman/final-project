package com.phincon.bootcamp.springquiz4ezra.repository;

import com.phincon.bootcamp.springquiz4ezra.model.Steps;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface StepConfigRepository extends R2dbcRepository<Steps,String> {

    @Query("SELECT * FROM Steps WHERE cfg_Id = :cfgId ORDER BY priority")
    Flux<Steps> findStep(@Param("cfgId") String cfgId);

}
