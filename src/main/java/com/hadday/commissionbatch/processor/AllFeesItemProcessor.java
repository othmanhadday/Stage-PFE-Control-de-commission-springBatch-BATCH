package com.hadday.commissionbatch.processor;

import com.hadday.commissionbatch.entities.*;
import com.hadday.commissionbatch.repository.*;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class AllFeesItemProcessor implements ItemProcessor<AllFeesGenerated, AllFees> {

    @Autowired
    private AllFeesRepository allFeesRepository;
    @Autowired
    private CategorieFeesRepository categorieFeesRepository;
    @Autowired
    private ReleveSoldeAvoirsRepository releveSoldeAvoirsRepository;
    @Autowired
    private EcartAllFeesRepository ecartAllFeesRepository;

    public static int i = 0;
    public static int j = 0;
    Collection<AllFees> allFeesAbsente = new ArrayList<AllFees>();
    Collection<AllFees> allFeesErrone = new ArrayList<AllFees>();
    Collection<AllFees> allFeesSurfacturation = new ArrayList<AllFees>();
    Collection<AllFees> allFeesFinal = new ArrayList<AllFees>();

    @Override
    public AllFees process(AllFeesGenerated allFeesGenerated) throws Exception {

        List<CategorieFees> categoriesFees = categorieFeesRepository.
                findCategorieFeesByTypeCommissionAndDeletedIsFalse("Avoirs");

        RelevesoldesAvoirs relevesoldesAvoirs = new RelevesoldesAvoirs();


        for (CategorieFees categorieFees : categoriesFees) {
            if (allFeesGenerated.getRelevesoldesAvoirs() != null) {
                relevesoldesAvoirs = releveSoldeAvoirsRepository.findById(
                        allFeesGenerated.getRelevesoldesAvoirs().getId()).get();

                if (relevesoldesAvoirs != null && relevesoldesAvoirs.getPrice() != null) {

                    List<AllFees> allFees = allFeesRepository.findAll(
                            categorieFees.getCategorieFeeName(),
                            allFeesGenerated.getDate(),
                            allFeesGenerated.getISIN(),
                            relevesoldesAvoirs.getQUANTITE(),
                            relevesoldesAvoirs.getPrice(),
                            allFeesGenerated.getBPID_RECIPIENT(),
                            allFeesGenerated.getBPID_LIABLE()
                    );


                    if (allFees.size() == 1) {
                        allFees.forEach(allFees1 -> {
                            if (allFees1.getAMOUNT() == allFeesGenerated.getAmount()) {
                                allFees1.setProcessed(true);
                                allFeesRepository.save(allFees1);
                                allFeesFinal.add(allFees1);
                            } else {
                                EcartAllFees ecartAllFees = new EcartAllFees();
                                ecartAllFees.setAllFees(allFees1);
                                ecartAllFees.setDate(allFees1.getDATE());
                                ecartAllFees.setTypeEcart("absence");
                                ecartAllFees.setIdentifiant(
                                        allFees1.getISIN() + "-" +
                                                allFees1.getDATE() + "-" +
                                                allFees1.getBPID_LIABLE() + "-" +
                                                allFees1.getBPID_RECIPIENT() + "-" +
                                                allFees1.getFEEBASIS() + "-" +
                                                allFees1.getPRICE()
                                );
                                allFees1.setProcessed(true);
                                allFeesRepository.save(allFees1);
                                ecartAllFeesRepository.save(ecartAllFees);
                                allFeesErrone.add(allFees1);
                            }
                        });
                    }


                    if (allFees.size() > 1) {
                        for (AllFees allFees1 : allFees) {
                            EcartAllFees ecartAllFees = new EcartAllFees();
                            ecartAllFees.setAllFees(allFees1);
                            ecartAllFees.setDate(allFees1.getDATE());
                            ecartAllFees.setTypeEcart("surfacturation");
                            ecartAllFees.setIdentifiant(
                                    allFees1.getISIN() + "-" +
                                            allFees1.getDATE() + "-" +
                                            allFees1.getBPID_LIABLE() + "-" +
                                            allFees1.getBPID_RECIPIENT() + "-" +
                                            allFees1.getFEEBASIS() + "-" +
                                            allFees1.getPRICE()
                            );
                            allFees1.setProcessed(true);
                            allFeesRepository.save(allFees1);
                            ecartAllFeesRepository.save(ecartAllFees);
                            allFeesSurfacturation.add(allFees1);
                        }
                    }
                }
            }
        }

        System.out.println("final = " + allFeesFinal.size());
        System.out.println("surfacturation = " + allFeesSurfacturation.size());
        System.out.println("Error commission = " + allFeesErrone.size());

        return null;
    }

}
