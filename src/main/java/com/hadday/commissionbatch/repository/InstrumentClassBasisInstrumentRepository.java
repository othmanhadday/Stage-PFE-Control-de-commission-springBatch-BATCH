package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.entities.InstrumentClassBasisInstrument;
import com.hadday.commissionbatch.entities.InstrumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentClassBasisInstrumentRepository extends JpaRepository<InstrumentClassBasisInstrument, Long> {

    public List<InstrumentClassBasisInstrument> findInstrumentClassBasisInstrumentByDeletedIsFalse();

    public List<InstrumentClassBasisInstrument> findInstrumentClassBasisInstrumentsByInstrumentTypeAndDeletedIsFalse(InstrumentType instrumentType);

    public List<InstrumentClassBasisInstrument> findInstrumentClassBasisInstrumentByName(String name);

}
