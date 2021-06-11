package com.hadday.commissionbatch.service;

import com.hadday.commissionbatch.entities.*;
import com.hadday.commissionbatch.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ControllerAllFeesServiceImpl implements ControllerAllFeesService {

    @Autowired
    private AllFeesRepository allFeesRepository;
    @Autowired
    private CategorieFeesRepository categorieFeesRepository;
    @Autowired
    private ReleveSoldeAvoirsRepository releveSoldeAvoirsRepository;
    @Autowired
    private EcartAllFeesRepository ecartAllFeesRepository;
    @Autowired
    private FeeRateRepository feeRateRepository;

    public static int i = 0;

//    @Override
//    public void DAProcess(AllFeesGenerated allFeesGenerated) {
//        List<CategorieFees> categoriesFees = categorieFeesRepository.
//                findCategorieFeesByTypeCommissionAndDeletedIsFalse("Droits_Admission");
//        if (categoriesFees.size() > 0) {
//            controllerEtat(allFeesGenerated, categoriesFees);
//        }
//    }
//
//    public void avoirsProcess(AllFeesGenerated allFeesGenerated) {
//        List<CategorieFees> categoriesFees = categorieFeesRepository.
//                findCategorieFeesByTypeCommissionAndDeletedIsFalse("Avoirs");
//
//        if (categoriesFees.size() > 0) {
//            controllerEtat(allFeesGenerated, categoriesFees);
//        }
//    }
//
//    public void comptesProcess(AllFeesGenerated allFeesGenerated) {
//        List<CategorieFees> categoriesFees = categorieFeesRepository.
//                findCategorieFeesByTypeCommissionAndDeletedIsFalse("Comptes");
//        if (categoriesFees.size() > 0) {
//            controllerEtat(allFeesGenerated, categoriesFees);
//        }
//    }

    ////////
//    change categorie fee requet by all feesGenerated

//    and add quantite to allfeees generated
    //////////////////

    @Override
    public void controllerEtat(AllFeesGenerated allFeesGenerated) {
        FeeRate feeRate = feeRateRepository.findById(allFeesGenerated.getFeeRate().getId()).get();

        System.out.println("//////////////////////////////////////////////////////////////////////////////////");
        List<AllFees> allFees = allFeesRepository.findAll(
                feeRate.getFeeType().getCategorieFees().getCategorieFeeName(),
                allFeesGenerated.getDate(),
                allFeesGenerated.getISIN(),
                allFeesGenerated.getQuantite(),
                allFeesGenerated.getBPID_RECIPIENT(),
                allFeesGenerated.getBPID_LIABLE()
        );




        if (allFees.size() == 0) {
            // Error All Fees not exist
            EcartAllFees ecartAllFees = new EcartAllFees();
            ecartAllFees.setAllFeesGenerated(allFeesGenerated);

            ecartAllFees.setDate(allFeesGenerated.getDate());
            ecartAllFees.setTypeEcart("not exist");
            ecartAllFees.setAjouter(true);

            ecartAllFees.setIdentifiant(
                    allFeesGenerated.getISIN() + "-" +
                            allFeesGenerated.getDate() + "-" +
                            allFeesGenerated.getBPID_LIABLE() + "-" +
                            allFeesGenerated.getBPID_RECIPIENT() + "-" +
                            allFeesGenerated.getQuantite()
            );
            if (exist(null, allFeesGenerated) == false) {
                ecartAllFeesRepository.save(ecartAllFees);
            }
            System.err.println("AllFees Not Exist");
        }

        if (allFees.size() == 1) {
            List<AllFees> finalAllFees = allFees;
            allFees.forEach(allFees1 -> {
                if (allFees1.getAMOUNT() == allFeesGenerated.getAmount()) {
                    allFees1.setProcessed(true);
                    System.out.println("success = " + finalAllFees);
                    allFeesRepository.save(allFees1);
                } else {
                    EcartAllFees ecartAllFees = new EcartAllFees();
                    ecartAllFees.setAllFees(allFees1);
                    ecartAllFees.setAllFeesGenerated(allFeesGenerated);
                    ecartAllFees.setDate(allFees1.getDATE());
                    ecartAllFees.setTypeEcart("Errone");
                    ecartAllFees.setModifier(true);
                    ecartAllFees.setIdentifiant(
                            allFees1.getISIN() + "-" +
                                    allFees1.getDATE() + "-" +
                                    allFees1.getBPID_LIABLE() + "-" +
                                    allFees1.getBPID_RECIPIENT() + "-" +
                                    allFees1.getFEEBASIS()
                    );
                    System.out.println("Errone = " + finalAllFees);

                    allFees1.setProcessed(true);
                    allFeesRepository.save(allFees1);
                    if (exist(allFees1, allFeesGenerated) == false) {
                        ecartAllFeesRepository.save(ecartAllFees);
                    }
                }
            });
        }

        if (allFees.size() > 1) {
            for (AllFees allFees1 : allFees) {
                EcartAllFees ecartAllFees = new EcartAllFees();
                ecartAllFees.setAllFees(allFees1);
                ecartAllFees.setAllFeesGenerated(allFeesGenerated);
                ecartAllFees.setDate(allFees1.getDATE());
                ecartAllFees.setTypeEcart("surfacturation");
                ecartAllFees.setSupprimer(true);
                ecartAllFees.setIdentifiant(
                        allFees1.getISIN() + "-" +
                                allFees1.getDATE() + "-" +
                                allFees1.getBPID_LIABLE() + "-" +
                                allFees1.getBPID_RECIPIENT() + "-" +
                                allFees1.getFEEBASIS()
                );
                allFees1.setProcessed(true);
                System.out.println("surfacturation = " + allFees);
                allFeesRepository.save(allFees1);
                if (exist(allFees1, allFeesGenerated) == false) {
                    ecartAllFeesRepository.save(ecartAllFees);
                }
            }

        }
        System.out.println("//////////////////////////////////////////////////////////////////////////////////");

    }

    public boolean exist(AllFees allFees, AllFeesGenerated allFeesGenerated) {
        AtomicBoolean exist = new AtomicBoolean(false);

        if (ecartAllFeesRepository.existsEcartAllFeesByAllFees(allFees)) {
            exist.set(true);
        }
        if (ecartAllFeesRepository.existsEcartAllFeesByAllFeesGenerated(allFeesGenerated)) {
            exist.set(true);
        }

        return exist.get();
    }

}
