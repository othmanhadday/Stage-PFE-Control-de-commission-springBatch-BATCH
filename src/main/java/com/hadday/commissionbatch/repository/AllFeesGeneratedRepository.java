package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.constante.Queries;
import com.hadday.commissionbatch.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AllFeesGeneratedRepository extends JpaRepository<AllFeesGenerated, Long> {

//    List<AllFeesGenerated> findAllFeesGeneratedByDate_calcul_commission(Date date);

    AllFeesGenerated findAllFeesGeneratedByRelevesoldesAvoirs(RelevesoldesAvoirs relevesoldesAvoirs);

    AllFeesGenerated findAllFeesGeneratedByRelevesoldesComptes(RelevesoldesComptes relevesoldesComptes);

    AllFeesGenerated findAllFeesGeneratedBySsatf(Ssatf ssatf);

}
