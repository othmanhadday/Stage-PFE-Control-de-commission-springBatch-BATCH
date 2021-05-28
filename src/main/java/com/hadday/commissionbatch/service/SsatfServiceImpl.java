package com.hadday.commissionbatch.service;

import com.hadday.commissionbatch.constante.RegleCalcul;
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
        allFees.setISIN(ssatf.getSecurityid());
        allFees.setDate(ssatf.getTradedate());

        if (allFeesGenerated == null) {
            if (feeRate == null) {
                allFees = null;
                System.err.println("Error SSATF identity = " + ssatf.getId());
                if (ecartCommissionRepository.existsEcartCommissionBySsatfAndDeletedIsFalse(ssatf) == false) {
                    EcartCommission ecartCommission = new EcartCommission(null,
                            "SSATF",
                            ssatf.getCLASSID(),
                            ssatf.getINSTRTYPE(),
                            ssatf.getINSTRCTGRY(),
                            false,
                            ssatf, null,null);
                    ecartCommissionRepository.save(ecartCommission);
                }
            } else {
                allFees.setAmount(RegleCalcul.droitAdmissionRegle(ssatf.getQuantity(), ssatf.getTradeprice(), feeRate.getFeeRate()));
                allFees.setFeeRate(feeRate);
            }
            return allFees;
        } else if (allFeesGenerated != null && allFeesGenerated.getAmount() == null) {
            allFees.setAmount(RegleCalcul.droitAdmissionRegle(ssatf.getQuantity(), ssatf.getTradeprice(), feeRate.getFeeRate()));
            allFeesGenerated.setFeeRate(feeRate);
            return allFeesGenerated;
        } else {
            return null;
        }
    }

}
