package com.hadday.commissionbatch.processor;

import com.hadday.commissionbatch.entities.*;
import com.hadday.commissionbatch.service.ControllerAllFeesService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AllFeesItemProcessor implements ItemProcessor<AllFeesGenerated,AllFees> {


    @Autowired
    private ControllerAllFeesService controllerAllFeesService;

    @Override
    public AllFees process(AllFeesGenerated allFeesGenerated) throws Exception {


        controllerAllFeesService.controllerEtat(allFeesGenerated);


        return null;
    }


}
