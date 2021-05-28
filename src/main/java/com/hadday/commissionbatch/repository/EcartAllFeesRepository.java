package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.entities.EcartAllFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface EcartAllFeesRepository extends JpaRepository<EcartAllFees,Long> {
}
