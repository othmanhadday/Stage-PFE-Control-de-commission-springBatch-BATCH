package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.entities.CategorieFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieFeesRepository extends JpaRepository<CategorieFees, Long> {

    public CategorieFees findCategorieFeesByCategorieFeeNameAndDeletedIsFalse(String name);

    public List<CategorieFees> findCategorieFeesByTypeCommissionAndDeletedIsFalse(String typeCommission);

    public List<CategorieFees> findCategorieFeesByDeletedIsFalse();
}
