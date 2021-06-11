package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.entities.AllFees;
import com.hadday.commissionbatch.entities.AllFeesGenerated;
import com.hadday.commissionbatch.entities.EcartAllFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public
interface EcartAllFeesRepository extends JpaRepository<EcartAllFees, Long> {

    public boolean existsEcartAllFeesByAllFees(AllFees allFees);

    public boolean existsEcartAllFeesByAllFeesGenerated(AllFeesGenerated allFeesGenerated);

}
