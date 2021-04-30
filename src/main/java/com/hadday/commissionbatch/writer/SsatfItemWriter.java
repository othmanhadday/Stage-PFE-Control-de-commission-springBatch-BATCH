package com.hadday.commissionbatch.writer;

import com.hadday.commissionbatch.entities.AllFeesGenerated;
import com.hadday.commissionbatch.repository.AllFeesGeneratedRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SsatfItemWriter implements ItemWriter<AllFeesGenerated> {

    @Autowired
    private AllFeesGeneratedRepository allFeesGeneratedRepository;

    @Override
    public void write(List<? extends AllFeesGenerated> list) throws Exception {
        allFeesGeneratedRepository.saveAll(list);
    }
}
