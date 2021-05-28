package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.constante.Queries;
import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.RelevesoldesAvoirs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleveSoldeAvoirsRepository extends JpaRepository<RelevesoldesAvoirs,Long> {



}
