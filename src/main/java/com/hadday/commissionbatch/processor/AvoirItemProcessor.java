package com.hadday.commissionbatch.processor;

import com.hadday.commissionbatch.entities.AllFeesGenerated;
import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.ReleveSolde;
import com.hadday.commissionbatch.service.FeeRateService;
import com.hadday.commissionbatch.service.ReleveSoldeService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AvoirItemProcessor implements ItemProcessor<ReleveSolde, AllFeesGenerated> {

    @Autowired
    private FeeRateService feeRateService;
    @Autowired
    private ReleveSoldeService releveSoldeService;

    @Override
    public AllFeesGenerated process(ReleveSolde releveSolde) throws Exception {

        FeeRate feeRate = feeRateService.findFeeRate(
                releveSolde.getCLASS(),
                releveSolde.getTYPE(),
                releveSolde.getINSTRCTGRY(),
                "Avoirs"
        );

        return releveSoldeService.craeteUpdateReleveSoldeToAllFees(releveSolde,feeRate);
    }
}
