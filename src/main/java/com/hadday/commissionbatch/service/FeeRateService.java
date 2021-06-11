package com.hadday.commissionbatch.service;

import com.hadday.commissionbatch.entities.FeeRate;

import java.util.List;

public interface FeeRateService {

    public List<FeeRate> findFeeRate(String className, String typeCode, String category, String typeCommission);

    public FeeRate findFeeRate(String className, String typeCode, String category, String typeCommission, String feType);

}
