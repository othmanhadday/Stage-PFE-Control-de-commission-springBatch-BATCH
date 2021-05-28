package com.hadday.commissionbatch.service;

import com.hadday.commissionbatch.constante.RegleCalcul;
import com.hadday.commissionbatch.entities.*;
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
    public AllFeesGenerated craeteUpdateReleveSoldeAvoirsToAllFees(RelevesoldesAvoirs relevesoldesAvoirs, FeeRate feeRate) {
        AllFeesGenerated allFeesGenerated = allFeesGeneratedRepository.findAllFeesGeneratedByRelevesoldesAvoirs(relevesoldesAvoirs);

        AllFeesGenerated allFees = new AllFeesGenerated();
        allFees.setDate_calcul_commission(new Date());
        allFees.setDate(relevesoldesAvoirs.getDATE_MAJ());
        allFees.setTypeCommission("Releve de solde (Avoirs)");
        allFees.setIdentifiant(relevesoldesAvoirs.getCODE_VALEUR() + "-" + relevesoldesAvoirs.getDATE_MAJ() + "-" + relevesoldesAvoirs.getQUANTITE());
        allFees.setRelevesoldesAvoirs(relevesoldesAvoirs);
        allFees.setBPID_RECIPIENT(relevesoldesAvoirs.getCODE_MANDANT());
        allFees.setBPID_LIABLE(relevesoldesAvoirs.getCODE_MANDATAIRE());
        allFees.setISIN(relevesoldesAvoirs.getCODE_VALEUR());

        if (allFeesGenerated == null) {
            if (feeRate == null) {
                allFees = null;
                System.err.println("Error releve solde  (Avoirs) identity = " + relevesoldesAvoirs.getId());

                EcartCommission ecartCommission = new EcartCommission(null, "Releve de solde (Avoirs)",
                        relevesoldesAvoirs.getCLASS(),
                        relevesoldesAvoirs.getTYPE(),
                        relevesoldesAvoirs.getINSTRCTGRY(),
                        false, null, relevesoldesAvoirs, null);
                ecartCommissionRepository.saveAndFlush(ecartCommission);

            } else {
                allFees.setAmount(RegleCalcul.avoirsRegle(relevesoldesAvoirs.getQUANTITE(), relevesoldesAvoirs.getPrice(), feeRate.getFeeRate()));
                allFees.setFeeRate(feeRate);
            }
            return allFees;
        } else if (allFeesGenerated != null && allFeesGenerated.getAmount() == null) {
            if (feeRate != null) {
                allFees.setAmount(RegleCalcul.avoirsRegle(relevesoldesAvoirs.getQUANTITE(), relevesoldesAvoirs.getPrice(), feeRate.getFeeRate()));

                allFeesGenerated.setDate_calcul_commission(new Date());
                allFeesGenerated.setFeeRate(feeRate);
            }
            return allFeesGenerated;
        } else {
            return null;
        }
    }


    @Override
    public AllFeesGenerated craeteUpdateReleveSoldeCompteToAllFees(RelevesoldesComptes releveSolde, FeeRate feeRate) {
        AllFeesGenerated allFeesGenerated = allFeesGeneratedRepository.findAllFeesGeneratedByRelevesoldesComptes(releveSolde);

        AllFeesGenerated allFees = new AllFeesGenerated();
        allFees.setDate_calcul_commission(new Date());
        allFees.setTypeCommission("Releve de solde (Compte)");
        allFees.setIdentifiant(releveSolde.getCODE_VALEUR() + "-" + releveSolde.getDATE_MAJ() + "-" + releveSolde.getQUANTITE());
        allFees.setRelevesoldesComptes(releveSolde);
        allFees.setBPID_RECIPIENT(releveSolde.getCODE_MANDANT());
        allFees.setBPID_LIABLE(releveSolde.getCODE_MANDATAIRE());
        allFees.setISIN(releveSolde.getCODE_VALEUR());
        allFees.setDate(releveSolde.getDATE_MAJ());

        if (allFeesGenerated == null) {
            if (feeRate == null) {
                allFees = null;
                System.err.println("Error releve solde (Compte) identity = " + releveSolde.getId());

                EcartCommission ecartCommission = new EcartCommission(null, "Releve de solde (Compte)",
                        releveSolde.getCLASS(),
                        releveSolde.getTYPE(),
                        releveSolde.getINSTRCTGRY(),
                        false, null, null, releveSolde);
                ecartCommissionRepository.save(ecartCommission);

            } else {
                if (feeRate.getFeeRate() != 0) {
                    allFees.setAmount(RegleCalcul.comptesRegle(releveSolde.getQUANTITE(), feeRate.getMontant()));
                }
                allFees.setFeeRate(feeRate);
            }
            return allFees;
        } else if (allFeesGenerated != null && allFeesGenerated.getAmount() == null) {
            if (feeRate != null) {
                if (feeRate.getFeeRate() != 0) {
                    allFees.setAmount(RegleCalcul.comptesRegle(releveSolde.getQUANTITE(), feeRate.getMontant()));
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
