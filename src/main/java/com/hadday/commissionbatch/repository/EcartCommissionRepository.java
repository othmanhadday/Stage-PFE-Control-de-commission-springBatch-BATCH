package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.entities.EcartCommission;
import com.hadday.commissionbatch.entities.RelevesoldesAvoirs;
import com.hadday.commissionbatch.entities.Ssatf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EcartCommissionRepository extends JpaRepository<EcartCommission, Long> {

    public boolean existsEcartCommissionBySsatfAndDeletedIsFalse(Ssatf ssatf);

    public List<EcartCommission> findEcartCommissionsByRelevesoldesAvoirs(RelevesoldesAvoirs relevesoldesAvoirs);

    public List<EcartCommission> findEcartCommissionsByInstClassAndInstTypeAndInstCatAndDeletedIsFalse(
            String className,
            String typeCode,
            String category
    );

}
