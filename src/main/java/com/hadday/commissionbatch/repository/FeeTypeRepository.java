package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.entities.CategorieFees;
import com.hadday.commissionbatch.entities.FeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeTypeRepository extends JpaRepository<FeeType, Long> {
    public List<FeeType> findFeeTypeByTypeNameAndDeletedIsFalse(String name);

    public List<FeeType> findFeeTypesByCategorieFeesAndDeletedIsFalse(CategorieFees categorieFees);

    public List<FeeType> findFeeTypesByDeletedIsFalse();
}
