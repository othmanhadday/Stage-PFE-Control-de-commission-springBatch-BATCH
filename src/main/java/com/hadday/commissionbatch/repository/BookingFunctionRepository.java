package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.entities.BookingFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingFunctionRepository extends JpaRepository<BookingFunction, Long> {

    public BookingFunction findBookingFunctionByNameAndDeletedIsFalse(String name);

    public List<BookingFunction> findBookingFunctionsByDeletedIsFalse();
}
