package com.hadday.commissionbatch.processor;

import com.hadday.commissionbatch.entities.AllFeesGenerated;
import com.hadday.commissionbatch.entities.EcartCommission;
import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.Ssatf;
//import com.hadday.commissionbatch.repository.AllFeesGeneratedRepository;
import com.hadday.commissionbatch.repository.EcartCommissionRepository;
import com.hadday.commissionbatch.repository.FeeRateRepository;
import com.hadday.commissionbatch.service.FeeRateService;
import com.hadday.commissionbatch.service.SsatfService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SsatfItemProcessor implements ItemProcessor<Ssatf, AllFeesGenerated> {

    @Autowired
    private FeeRateService feeRateService;

    @Autowired
    private SsatfService ssatfService;

    @Override
    public AllFeesGenerated process(Ssatf ssatf) throws Exception {

        FeeRate feeRate = new FeeRate();

        System.out.println(ssatf);

        List<FeeRate> feeRates = feeRateService.findFeeRate(
                ssatf.getCLASSID(),
                ssatf.getINSTRTYPE(),
                ssatf.getINSTRCTGRY(),
                "Droits_Admission"
        );
        if (feeRates.size() > 1) {
            feeRate = feeRates.get(0);
        } else if (feeRates.size() == 1) {
            feeRate = feeRates.get(0);
        } else {
            feeRate = null;
        }

        return ssatfService.createUpdateSsatfToAllfees(ssatf, feeRate);
    }
}
