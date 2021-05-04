package com.hadday.commissionbatch.service;

import com.hadday.commissionbatch.entities.AllFeesGenerated;
import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.Ssatf;

public interface SsatfService {

    public AllFeesGenerated createUpdateSsatfToAllfees(Ssatf ssatf, FeeRate feeRate);

}
