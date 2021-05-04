package com.hadday.commissionbatch.service;

import com.hadday.commissionbatch.entities.AllFeesGenerated;
import com.hadday.commissionbatch.entities.EcartCommission;
import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.ReleveSolde;
import com.hadday.commissionbatch.repository.AllFeesGeneratedRepository;
import com.hadday.commissionbatch.repository.EcartCommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReleveSoldeServiceImpl implements ReleveSoldeService {

    @Autowired
    private AllFeesGeneratedRepository allFeesGeneratedRepository;

    @Autowired
    private EcartCommissionRepository ecartCommissionRepository;

    @Override
    public AllFeesGenerated craeteUpdateReleveSoldeToAllFees(ReleveSolde releveSolde, FeeRate feeRate) {
        AllFeesGenerated allFeesGenerated = allFeesGeneratedRepository.findAllFeesGeneratedByReleveSolde(releveSolde);

        AllFeesGenerated allFees = new AllFeesGenerated();
        allFees.setDate_calcul_commission(new Date());
        allFees.setTypeCommission("Releve de solde (Avoirs)");
        allFees.setIdentifiant(releveSolde.getCODE_VALEUR() + "-" + releveSolde.getDATE_MAJ() + "-" + releveSolde.getQUANTITE());
        allFees.setReleveSolde(releveSolde);

        if (allFeesGenerated == null) {
            if (feeRate == null) {
                allFees = null;
                System.err.println("Error releve solde identity = " + releveSolde.getId());
                if (ecartCommissionRepository.existsEcartCommissionByReleveSolde(releveSolde) == false) {
                    EcartCommission ecartCommission = new EcartCommission(null, "Releve de solde (Avoirs)", null, releveSolde);
                    ecartCommissionRepository.save(ecartCommission);
                }
            } else {
                if (feeRate.getFeeRate() != 0) {
                    allFees.setAmount(releveSolde.getQUANTITE() * releveSolde.getPrice() * (feeRate.getFeeRate() / 360));
                } else {
                    allFees.setAmount(releveSolde.getQUANTITE() * releveSolde.getPrice());
                }
                allFees.setFeeRate(feeRate);
            }
            return allFees;
        }
        else if (allFeesGenerated != null && allFeesGenerated.getAmount()==null){
            if (feeRate != null) {
                if (feeRate.getFeeRate() != 0) {
                    allFeesGenerated.setAmount(releveSolde.getQUANTITE() * releveSolde.getPrice() * (feeRate.getFeeRate() / 360));
                } else {
                    allFeesGenerated.setAmount(releveSolde.getQUANTITE() * releveSolde.getPrice());
                }
                allFeesGenerated.setDate_calcul_commission(new Date());
                allFeesGenerated.setFeeRate(feeRate);
            }
            return allFeesGenerated;
        } else {
            return null;
        }
    }

}
