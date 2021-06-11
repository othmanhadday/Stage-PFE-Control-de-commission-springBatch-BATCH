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

        System.out.println("********************************************************************");
        String feeType = "";
        FeeRate feeRate = null;

        System.out.println(relevesoldesComptes);

        if (relevesoldesComptes.getCODE_MANDATAIRE().equals("00000000001")) {
            feeType = "P030";
        } else if (relevesoldesComptes.getCODE_MANDATAIRE().equals("00000000010")) {
            feeType = "";
        } else {
            feeType = "P029";
        }

        if (!feeType.isEmpty()) {
            feeRate = feeRateService.findFeeRate(
                    relevesoldesComptes.getCLASS(),
                    relevesoldesComptes.getTYPE(),
                    relevesoldesComptes.getINSTRCTGRY(),
                    "Comptes",
                    feeType
            );
        }
        return releveSoldeService.craeteUpdateReleveSoldeCompteToAllFees(relevesoldesComptes, feeRate);
    }
}
