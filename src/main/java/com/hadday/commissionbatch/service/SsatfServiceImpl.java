package com.hadday.commissionbatch.service;

import com.hadday.commissionbatch.entities.AllFeesGenerated;
import com.hadday.commissionbatch.entities.EcartCommission;
import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.Ssatf;
import com.hadday.commissionbatch.repository.AllFeesGeneratedRepository;
import com.hadday.commissionbatch.repository.EcartCommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class SsatfServiceImpl implements SsatfService {

    @Autowired
    private AllFeesGeneratedRepository allFeesGeneratedRepository;

    @Autowired
    private EcartCommissionRepository ecartCommissionRepository;

    @Override
    public AllFeesGenerated createUpdateSsatfToAllfees(Ssatf ssatf, FeeRate feeRate) {
        AllFeesGenerated allFeesGenerated = allFeesGeneratedRepository.findAllFeesGeneratedBySsatf(ssatf);

        AllFeesGenerated allFees = new AllFeesGenerated();
        allFees.setDate_calcul_commission(new Date());
        allFees.setTypeCommission("SSATF");
        allFees.setIdentifiant(ssatf.getSecurityid() + "-" + ssatf.getTradedate() + "-" + ssatf.getQuantity());
        allFees.setSsatf(ssatf);

        if (allFeesGenerated == null) {
            if (feeRate == null) {
                allFees = null;
                System.err.println("Error SSATF identity = " + ssatf.getId());
                if (ecartCommissionRepository.existsEcartCommissionBySsatf(ssatf) == false) {
                    EcartCommission ecartCommission = new EcartCommission(null, "SSATF", ssatf, null);
                    ecartCommissionRepository.save(ecartCommission);
                }
            } else {
                if (feeRate.getFeeRate() != 0) {
                    allFees.setAmount(ssatf.getQuantity() * ssatf.getTradeprice() * feeRate.getFeeRate());
                } else {
                    allFees.setAmount(ssatf.getQuantity() * ssatf.getTradeprice());
                }
                allFees.setFeeRate(feeRate);
            }
            return allFees;
        } else if (allFeesGenerated != null && allFeesGenerated.getAmount() == null) {
            if (feeRate.getFeeRate() != 0) {
                allFeesGenerated.setAmount(ssatf.getQuantity() * ssatf.getTradeprice() * feeRate.getFeeRate());
            } else {
                allFeesGenerated.setAmount(ssatf.getQuantity() * ssatf.getTradeprice());
            }
            allFeesGenerated.setFeeRate(feeRate);
            return allFeesGenerated;
        } else {
            return null;
        }
    }

}
