package com.hadday.commissionbatch.writer;

import com.hadday.commissionbatch.entities.AllFees;
import com.hadday.commissionbatch.entities.AllFeesGenerated;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllFeesItemWriter implements ItemWriter<AllFees> {
    @Override
    public void write(List<? extends AllFees> list) throws Exception {

    }
}
