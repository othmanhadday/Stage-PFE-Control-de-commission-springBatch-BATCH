package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.entities.InstrumentClass;
import com.hadday.commissionbatch.entities.InstrumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentTypeRepository extends JpaRepository<InstrumentType, Long> {

    public InstrumentType findInstrumentTypeByInstrumentTypeNameAndDeletedIsFalse(String namd);

    public List<InstrumentType> findInstrumentTypesByDeletedIsFalse();

    public List<InstrumentType> findInstrumentTypesByInstrumentClassAndDeletedIsFalse(InstrumentClass instrumentClass);
}
