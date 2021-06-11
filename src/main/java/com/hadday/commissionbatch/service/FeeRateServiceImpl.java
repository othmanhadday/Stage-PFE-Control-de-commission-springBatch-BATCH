package com.hadday.commissionbatch.service;

import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.repository.FeeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeRateServiceImpl implements FeeRateService {

    @Autowired
    private FeeRateRepository feeRateRepository;

    public List<FeeRate> findFeeRate(String className, String typeCode, String category, String typeCommission) {
        return feeRateRepository.findFeeRate(className, typeCode, category, typeCommission);
    }

    public FeeRate findFeeRate(String className, String typeCode, String category, String typeCommission, String feType) {
        return feeRateRepository.findFeeRate(className, typeCode, category, typeCommission,feType);
    }
}
