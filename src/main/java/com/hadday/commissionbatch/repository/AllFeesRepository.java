package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.constante.Queries;
import com.hadday.commissionbatch.entities.AllFees;
import com.hadday.commissionbatch.entities.AllFeesGenerated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Repository
public interface AllFeesRepository extends JpaRepository<AllFees, Long> {

    @Query(value = Queries.GET_ALLFEES_BY_IDENTITY)
    public List<AllFees> findAll(
            @Param("feeCategorie") String feeCategorie,
            @Param("DATE") Date Date,
            @Param("ISIN") String ISIN,
            @Param("FEEBASIS") double FEEBASIS,
            @Param("price") double price,
            @Param("BPID_RECIPIENT") String BPID_RECIPIENT,
            @Param("BPID_LIABLE") String BPID_LIABLE
    );


}
