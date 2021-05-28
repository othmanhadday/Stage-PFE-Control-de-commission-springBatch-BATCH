package com.hadday.commissionbatch.service;

import com.hadday.commissionbatch.entities.AllFeesGenerated;
import com.hadday.commissionbatch.entities.FeeRate;
import com.hadday.commissionbatch.entities.RelevesoldesAvoirs;
import com.hadday.commissionbatch.entities.RelevesoldesComptes;

public interface ReleveSoldeService {

    public AllFeesGenerated craeteUpdateReleveSoldeAvoirsToAllFees(RelevesoldesAvoirs relevesoldesAvoirs, FeeRate feeRate);
    public AllFeesGenerated craeteUpdateReleveSoldeCompteToAllFees(RelevesoldesComptes releveSolde, FeeRate feeRate);

}
