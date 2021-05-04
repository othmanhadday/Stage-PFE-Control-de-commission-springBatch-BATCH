package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.entities.EcartCommission;
import com.hadday.commissionbatch.entities.ReleveSolde;
import com.hadday.commissionbatch.entities.Ssatf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcartCommissionRepository extends JpaRepository<EcartCommission, Long> {

    public boolean existsEcartCommissionBySsatf(Ssatf ssatf);

    public boolean existsEcartCommissionByReleveSolde(ReleveSolde releveSolde);
}
