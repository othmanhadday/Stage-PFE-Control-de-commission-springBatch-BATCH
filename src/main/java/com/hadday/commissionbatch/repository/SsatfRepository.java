package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.Ssatf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SsatfRepository extends JpaRepository<Ssatf, Long> {

    public List<Ssatf> findSsatfsByDeletedIsFalse();



}
