package com.hadday.commissionbatch.processor;

import com.hadday.commissionbatch.entities.AllFeesGenerated;
import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.ReleveSolde;
import com.hadday.commissionbatch.repository.AllFeesGeneratedRepository;
import com.hadday.commissionbatch.repository.FeeRateRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AvoirItemProcessor implements ItemProcessor<ReleveSolde, AllFeesGenerated> {

    @Autowired
    private FeeRateRepository feeRateRepository;
    @Autowired
    private AllFeesGeneratedRepository allFeesGeneratedRepository;

    @Override
    public AllFeesGenerated process(ReleveSolde releveSolde) throws Exception {
        FeeRate feeRate = null;

        feeRate = feeRateRepository.findFeeRate(
                releveSolde.getCLASS(),
                releveSolde.getTYPE(),
                releveSolde.getINSTRCTGRY(),
                "Avoirs"
        );
        AllFeesGenerated allFeesGenerated = allFeesGeneratedRepository.findAllFeesGeneratedByReleveSolde(releveSolde);

        AllFeesGenerated allFees = new AllFeesGenerated();
        allFees.setDate_calcul_commission(new Date());
        allFees.setTypeCommission("Avoirs");
        allFees.setIdentifiant(releveSolde.getCODE_VALEUR() + "-" + releveSolde.getDATE_MAJ() + "-" + releveSolde.getQUANTITE());
        allFees.setReleveSolde(releveSolde);
        System.out.println(releveSolde);

        if (allFeesGenerated == null) {
            if (feeRate == null) {
                System.err.println("Releve de Solde have a problem in Order Reference = " + releveSolde.getId());
                allFees.setMessage("Releve de Solde have a problem in Order Reference = " + releveSolde.getId());
                allFees.setAmount(null);
                allFees.setProcessed(false);
            } else {
                if (feeRate.getFeeRate() != 0) {
                    allFees.setAmount(releveSolde.getQUANTITE() * releveSolde.getPrice() * (feeRate.getFeeRate() / 360));
                } else {
                    allFees.setAmount(releveSolde.getQUANTITE() * releveSolde.getPrice());
                }
                allFees.setFeeRate(feeRate);
                allFees.setProcessed(true);
                allFees.setMessage("Success");
            }
            return allFees;

        } else if (allFeesGenerated != null && allFeesGenerated.isProcessed()==false){
            if (feeRate != null) {
                if (feeRate.getFeeRate() != 0) {
                    allFeesGenerated.setAmount(releveSolde.getQUANTITE() * releveSolde.getPrice() * (feeRate.getFeeRate() / 360));
                } else {
                    allFeesGenerated.setAmount(releveSolde.getQUANTITE() * releveSolde.getPrice());
                }
                allFeesGenerated.setDate_calcul_commission(new Date());
                allFeesGenerated.setFeeRate(feeRate);
                allFeesGenerated.setProcessed(true);
                allFeesGenerated.setMessage("Success");
            }
            return allFeesGenerated;
        } else {
            return null;
        }
    }
}
