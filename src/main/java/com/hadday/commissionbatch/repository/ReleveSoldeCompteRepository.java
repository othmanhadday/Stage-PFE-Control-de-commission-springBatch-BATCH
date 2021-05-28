package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.constante.Queries;
import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.RelevesoldesComptes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleveSoldeCompteRepository extends JpaRepository<RelevesoldesComptes,Long> {

//    @Query(value = Queries.ECART_AVOIR_EXIST_QUERY)
//    public FeeRate findRe(
//            @Param("className") String className,
//            @Param("typeCode") String typeCode,
//            @Param("category") String category,
//            @Param("typeCommission") String typeCommission
//    );

}
