package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.entities.InstrumentCategorie;
import com.hadday.commissionbatch.entities.InstrumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentCategorieRepository extends JpaRepository<InstrumentCategorie, Long> {







    public List<InstrumentCategorie> findCategorieRatesByDeletedIsFalse();

    public InstrumentCategorie findCategorieRateByCategory(String name);

    public List<InstrumentCategorie> findInstrumentCategoriesByInstrumentTypeAndDeletedIsFalse(InstrumentType instrumentType);

}
