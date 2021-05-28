package com.hadday.commissionbatch.web;

import com.hadday.commissionbatch.entities.*;
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
    private SsatfService ssatfService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private AllFeesRepository allFeesRepository;

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
    public List<AllFees> test() throws Exception {
        Session session = (Session) entityManager.getDelegate();
//        TypedQuery<Integer> q = entityManager.createQuery(
//               " Select count(a) from  RelevesoldesAvoirs a " +
//                        " where a.CLASS = 'EQTY' " +
//                        " and a.TYPE = 'SHRS' " +
//                        " and a.INSTRCTGRY = 'ACT_ORD' "
//                , Integer.class);
//
////        List<RelevesoldesAvoirs> list = q.getSingleResult();
//        System.out.println(q.getSingleResult());

//        FeeRate classes = feeRateRepository.findFeeRate("EQTY","CPNS","COUP_INTR",
//                "Comptes","P029");

//        System.out.println(releveSoldeAvoirsRepository.findRelevesoldesAvoirsByCLASSAndTYPEAndINSTRCTGRY("EQTY","SHRS","ACT_ORD").size());

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-01");
        System.out.println(date);

//        Date date1 = new Date("2021-03-01");

        List<AllFees> allFees = allFeesRepository.findAll(
                "PH",
                date,
                "MA0000010019",
                10,
                369.85,
                "00000000112",
                "00000000128"
        );

        System.out.println(allFees);

        return allFees;

    }

}
