package com.hadday.commissionbatch.web;

import com.hadday.commissionbatch.entities.*;
import com.hadday.commissionbatch.service.SsatfService;
import javafx.print.PrinterJob;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    @GetMapping("startJob")
    //every day at 1am
    //@Scheduled(cron = "0 0 1 1/1 * ?")
    //last day of every month
    //@Scheduled(cron = "0 0 18 L * ?")
    //public BatchStatus load() throws Exception {
    public BatchStatus load() throws Exception {

        System.out.println("batch started ............... ");
        Map<String, JobParameter> params = new HashMap<>();
        params.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameter = new JobParameters(params);
        JobExecution jobExecution = jobLauncher.run(job, jobParameter);
        while (jobExecution.isRunning()) {
            System.out.println("......");
        }
        System.out.println(jobExecution.getStatus());

//        Session session = (Session) entityManager.getDelegate();
//        TypedQuery<FeeRate> q = entityManager.createQuery(
//                "Select fee from InstrumentClass c " +
//                        " INNER JOIN c.instrumentTypes t on c = t.instrumentClass " +
//                        " INNER JOIN t.instrumentCategories cat on t = cat.instrumentType " +
//                        " INNER JOIN cat.feeRates fee on cat = fee.instrumentCategorie " +
//                        " where c.instrementClass = 'EQTY' " +
//                        " and t.instrumentTypeCode = 'SHRS' " +
//                        " and cat.category = 'ACT ORD' " +
//                        " and fee.feeType.categorieFees.typeCommission in ( 'Droits Admission' , 'Avoirs') " +
//                        " and fee.tauxMontant = 'T'" +
//                        " and c.deleted= false" +
//                        " and t.deleted= false" +
//                        " and cat.deleted= false" +
//                        " and fee.deleted= false"
//                , FeeRate.class);
//
//        FeeRate classes = q.getSingleResult();
//        System.out.println(classes);

        return jobExecution.getStatus();
//        return classes;
//        return  ssatfService.findFeeRate("Mutual Fund","MFOT","OBL MLT","PA");
    }
}
