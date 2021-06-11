package com.hadday.commissionbatch.processor;

import com.hadday.commissionbatch.entities.AllFeesGenerated;
import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.RelevesoldesAvoirs;
import com.hadday.commissionbatch.service.FeeRateService;
import com.hadday.commissionbatch.service.ReleveSoldeService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AvoirItemProcessor implements ItemProcessor<RelevesoldesAvoirs, AllFeesGenerated> {

    @Autowired
    private FeeRateService feeRateService;
    @Autowired
    private ReleveSoldeService releveSoldeService;

    @Override
    public AllFeesGenerated process(RelevesoldesAvoirs relevesoldesAvoirs) throws Exception {

        FeeRate feeRate = new FeeRate();

        List<FeeRate> feeRates = feeRateService.findFeeRate(
                relevesoldesAvoirs.getCLASS(),
                relevesoldesAvoirs.getTYPE(),
                relevesoldesAvoirs.getINSTRCTGRY(),
                "Avoirs"
        );
        if (feeRates.size() > 1) {
            feeRate = feeRates.get(0);
        } else if (feeRates.size() == 1) {
            feeRate = feeRates.get(0);
        } else {
            feeRate = null;
        }

        return releveSoldeService.craeteUpdateReleveSoldeAvoirsToAllFees(relevesoldesAvoirs, feeRate);
    }
}
