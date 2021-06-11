package com.hadday.commissionbatch.web;

import com.hadday.commissionbatch.entities.*;
import com.hadday.commissionbatch.repository.AllFeesGeneratedRepository;
import com.hadday.commissionbatch.repository.AllFeesRepository;
import com.hadday.commissionbatch.repository.FeeRateRepository;
import com.hadday.commissionbatch.repository.ReleveSoldeAvoirsRepository;
import com.hadday.commissionbatch.service.SsatfService;
import javafx.print.PrinterJob;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@EnableScheduling
public class JobRestController {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;
    @Autowired
    private FeeRateRepository feeRateRepository;
    @Autowired
    private SsatfService ssatfService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private AllFeesRepository allFeesRepository;
    @Autowired
    private AllFeesGeneratedRepository allFeesGeneratedRepository;

    @Autowired
    private ReleveSoldeAvoirsRepository releveSoldeAvoirsRepository;

    @GetMapping("startJob")
    //every day at 1am
    //@Scheduled(cron = "0 0 1 1/1 * ?")
    //last day of every month
    //@Scheduled(cron = "0 0 18 L * ?")
    public BatchStatus load() throws Exception {
//    public FeeRate load() throws Exception {

        System.out.println("batch started ............... ");
        Map<String, JobParameter> params = new HashMap<>();
        params.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameter = new JobParameters(params);
        JobExecution jobExecution = jobLauncher.run(job, jobParameter);
        while (jobExecution.isRunning()) {
            System.out.println("......");
        }
        System.out.println(jobExecution.getStatus());

        Session session = (Session) entityManager.getDelegate();
//        TypedQuery<FeeRate> q = entityManager.createQuery(
//                "Select fee from InstrumentClass c " +
//                        " INNER JOIN c.instrumentTypes t on c = t.instrumentClass " +
//                        " INNER JOIN t.instrumentCategories cat on t = cat.instrumentType " +
//                        " INNER JOIN cat.feeRates fee on cat = fee.instrumentCategorie " +
//                        " where c.instrementClass = 'EQTY' " +
//                        " and t.instrumentTypeCode = 'CPNS' " +
//                        " and cat.category = 'COUP_INTR' " +
//                        " and fee.feeType.categorieFees.typeCommission = 'Comptes' " +
//                        " and fee.feeType.typeName = CASE " +
//                        "   when" +
//                        "        fee.feeType.categorieFees.typeCommission = 'Comptes' " +
//                        "THEN 'P029' ELSE '' END " +
//                        " and fee.tauxMontant = 'M'" +
//                        " and c.deleted= false" +
//                        " and t.deleted= false" +
//                        " and cat.deleted= false" +
//                        " and fee.deleted= false "
//                , FeeRate.class);
//
//        FeeRate classes = feeRateRepository.findFeeRate("EQTY","CPNS","COUP_INTR",
//                "Comptes","P029");
//
//        System.out.println(
//                feeRateRepository.findFeeRate("EQTY",
//                        "CPNS","COUP_INTR",
//                "Comptes","P029")
//        );

        return jobExecution.getStatus();
//        return classes;
//        return  ssatfService.findFeeRate("Mutual Fund","MFOT","OBL MLT","PA");
    }


    @GetMapping("test")
    public FeeRate test() throws Exception {
//        Session session = (Session) entityManager.getDelegate();
//        TypedQuery<FeeRate> q = entityManager.createQuery(
//                " select a from AllFeesGenerated as a " +
//                        " INNER JOIN  a.relevesoldesAvoirs r on a = r.allFeesGenerateds " +
//                        " where " +
//                        " a.date = :DATE " +
//                        " and a.ISIN = :ISIN " +
//                        " and a.BPID_RECIPIENT = :BPID_RECIPIENT" +
//                        " and a.BPID_LIABLE = :BPID_LIABLE " +
//                        " and not a.relevesoldesAvoirs IS not null " +
//                        " and r.QUANTITE = :quantite  "
//                , FeeRate.class);
//
//        List<FeeRate> list = q.getSingleResult();
//        System.out.println(q.getSingleResult());

        FeeRate classes = feeRateRepository.findFeeRate("MTFU", "MFMN", "MONETAIRES",
                "Comptes", "P029");


        return classes;
    }

}
