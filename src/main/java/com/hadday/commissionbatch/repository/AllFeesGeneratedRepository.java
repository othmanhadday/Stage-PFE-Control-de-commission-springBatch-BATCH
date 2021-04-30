package com.hadday.commissionbatch.repository;

import com.hadday.commissionbatch.entities.AllFeesGenerated;
import com.hadday.commissionbatch.entities.ReleveSolde;
import com.hadday.commissionbatch.entities.Ssatf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllFeesGeneratedRepository extends JpaRepository<AllFeesGenerated, Long> {

    public AllFeesGenerated findAllFeesGeneratedByReleveSolde(ReleveSolde releveSolde);

    public AllFeesGenerated findAllFeesGeneratedBySsatf(Ssatf ssatf);

}
