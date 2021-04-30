package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.entities.BookingFunction;
import com.hadday.commissionbatch.entities.BookingInstrumentBasis;
import com.hadday.commissionbatch.entities.FeeType;
import com.hadday.commissionbatch.entities.InstrumentClassBasisInstrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingInstrumentBasisRepository extends JpaRepository<BookingInstrumentBasis, Long> {

    public List<BookingInstrumentBasis> findBookingInstrumentBasesByDeletedIsFalse();

    public List<BookingInstrumentBasis> findBookingInstrumentBasesByInstrumentClassBasisInstrumentAndDeletedIsFalse(InstrumentClassBasisInstrument instrument);

    public List<BookingInstrumentBasis> findBookingInstrumentBasesByBookFunctionAndDeletedIsFalse(BookingFunction bookingFunction);

    public List<BookingInstrumentBasis> findBookingInstrumentBasesByFeeTypeAndDeletedIsFalse(FeeType feeType);

}
