package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.entities.ReleveSolde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleveSoldeRepository extends JpaRepository<ReleveSolde,Long> {
}
