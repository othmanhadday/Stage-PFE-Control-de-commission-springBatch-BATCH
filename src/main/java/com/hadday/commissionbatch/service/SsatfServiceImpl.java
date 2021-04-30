package com.hadday.commissionbatch.service;

import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.InstrumentCategorie;
import com.hadday.commissionbatch.repository.FeeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SsatfServiceImpl implements SsatfService {

    @Autowired
    private FeeRateRepository feeRateRepository;

    @Override
    public FeeRate findFeeRate(String className, String typeCode, String category,String typeCommission) {
        System.out.println(feeRateRepository.findFeeRate(className,typeCode,category,typeCommission));
        return  feeRateRepository.findFeeRate(className,typeCode,category,typeCommission);
    }
}
