package com.hadday.commissionbatch.service;

import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.InstrumentCategorie;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SsatfService {

    public FeeRate findFeeRate(String className, String typeCode, String category, String typeCommission);
}
