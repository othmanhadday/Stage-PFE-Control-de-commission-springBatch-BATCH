package com.hadday.commissionbatch.processor;

import com.hadday.commissionbatch.entities.AllFeesGenerated;
import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.RelevesoldesComptes;
import com.hadday.commissionbatch.service.FeeRateService;
import com.hadday.commissionbatch.service.ReleveSoldeService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompteItemProcessor implements ItemProcessor<RelevesoldesComptes, AllFeesGenerated> {

    @Autowired
    private FeeRateService feeRateService;
    @Autowired
    private ReleveSoldeService releveSoldeService;

    @Override
    public AllFeesGenerated process(RelevesoldesComptes relevesoldesComptes) throws Exception {

        String feeType = null;

        if (relevesoldesComptes.getCODE_MANDATAIRE().equals("00000000010") || relevesoldesComptes.getCODE_MANDATAIRE().equals("00000000001")) {
            feeType = "P030";
        } else {
            feeType = "P029";
        }

        FeeRate feeRate = feeRateService.findFeeRate(
                relevesoldesComptes.getCLASS(),
                relevesoldesComptes.getTYPE(),
                relevesoldesComptes.getINSTRCTGRY(),
                "Comptes",
                feeType
        );

        return releveSoldeService.craeteUpdateReleveSoldeCompteToAllFees(relevesoldesComptes, feeRate);
    }
}
