package com.hadday.commissionbatch.service;

import com.hadday.commissionbatch.entities.FeeRate;

public interface FeeRateService {
    public FeeRate findFeeRate(String className, String typeCode, String category, String typeCommission);


}
