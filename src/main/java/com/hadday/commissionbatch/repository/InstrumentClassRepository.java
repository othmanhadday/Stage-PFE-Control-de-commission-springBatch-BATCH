package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.entities.InstrumentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentClassRepository extends JpaRepository<InstrumentClass, Long> {
    public InstrumentClass findInstrumentClassByInstrementClassAndDeletedIsFalse(String namd);

    public List<InstrumentClass> findInstrumentClassesByDeletedIsFalse();
}
