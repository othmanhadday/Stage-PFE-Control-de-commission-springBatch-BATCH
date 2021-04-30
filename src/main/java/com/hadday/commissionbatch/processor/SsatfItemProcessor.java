package com.hadday.commissionbatch.processor;

import com.hadday.commissionbatch.entities.AllFeesGenerated;
import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.Ssatf;
import com.hadday.commissionbatch.repository.AllFeesGeneratedRepository;
import com.hadday.commissionbatch.repository.FeeRateRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SsatfItemProcessor implements ItemProcessor<Ssatf, AllFeesGenerated> {

    @Autowired
    private FeeRateRepository feeRateRepository;

    @Autowired
    private AllFeesGeneratedRepository allFeesGeneratedRepository;

    @Override
    public AllFeesGenerated process(Ssatf ssatf) throws Exception {

        FeeRate feeRate = null;

        feeRate = feeRateRepository.findFeeRate(
                ssatf.getCLASSID(),
                ssatf.getINSTRTYPE(),
                ssatf.getINSTRCTGRY(),
                "Droits_Admission"
        );

        AllFeesGenerated allFeesGenerated = allFeesGeneratedRepository.findAllFeesGeneratedBySsatf(ssatf);

        AllFeesGenerated allFees = new AllFeesGenerated();
        allFees.setDate_calcul_commission(new Date());
        allFees.setTypeCommission("Droits_Admission");
        allFees.setIdentifiant(ssatf.getSecurityid() + "-" + ssatf.getTradedate() + "-" + ssatf.getQuantity());
        allFees.setSsatf(ssatf);
        System.out.println(ssatf);

        if (allFeesGenerated == null) {
            if (feeRate == null) {
                System.err.println("SSATF have a problem in Order Reference = " + ssatf.getOrderreference());
                allFees.setMessage("SSATF have a problem in Order Reference = " + ssatf.getOrderreference());
                allFees.setAmount(null);
                allFees.setProcessed(false);
            } else {
                if (feeRate.getFeeRate() != 0) {
                    allFees.setAmount(ssatf.getQuantity() * ssatf.getTradeprice() * feeRate.getFeeRate());
                } else {
                    allFees.setAmount(ssatf.getQuantity() * ssatf.getTradeprice());
                }
                allFees.setFeeRate(feeRate);
                allFees.setProcessed(true);
                allFees.setMessage("Success");
            }
            return allFees;

        } else if (allFeesGenerated != null && allFeesGenerated.isProcessed() == false) {
            if (feeRate.getFeeRate() != 0) {
                allFeesGenerated.setAmount(ssatf.getQuantity() * ssatf.getTradeprice() * feeRate.getFeeRate());
            } else {
                allFeesGenerated.setAmount(ssatf.getQuantity() * ssatf.getTradeprice());
            }
            allFeesGenerated.setFeeRate(feeRate);
            allFeesGenerated.setProcessed(true);
            allFeesGenerated.setMessage("Success");
            return allFeesGenerated;
        } else {
            return null;
        }

    }
}
