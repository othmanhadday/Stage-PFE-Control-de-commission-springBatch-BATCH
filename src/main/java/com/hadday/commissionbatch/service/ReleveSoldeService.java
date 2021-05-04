package com.hadday.commissionbatch.service;

import com.hadday.commissionbatch.entities.AllFeesGenerated;
import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.ReleveSolde;

public interface ReleveSoldeService {

    public AllFeesGenerated craeteUpdateReleveSoldeToAllFees(ReleveSolde releveSolde, FeeRate feeRate);

}
