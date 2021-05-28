package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.constante.Queries;
import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.FeeType;
import com.hadday.commissionbatch.entities.InstrumentCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface FeeRateRepository extends JpaRepository<FeeRate, Long> {

    public List<FeeRate> findFeeRatesByDeletedIsFalse();

    public List<FeeRate> findFeeRatesByInstrumentCategorieAndDeletedIsFalse(InstrumentCategorie instrumentCategorie);

    public List<FeeRate> findFeeRatesByFeeTypeAndDeletedIsFalse(FeeType feeType);

    @Query(value = Queries.DA_Avoirs_Query)
    public FeeRate findFeeRate(
            @Param("className") String className,
            @Param("typeCode") String typeCode,
            @Param("category") String category,
            @Param("typeCommission") String typeCommission
    );


    @Query(value = Queries.Compte_Query)
    public FeeRate findFeeRate(
            @Param("className") String className,
            @Param("typeCode") String typeCode,
            @Param("category") String category,
            @Param("typeCommission") String typeCommission,
            @Param("feeType") String feeType
    );

}
