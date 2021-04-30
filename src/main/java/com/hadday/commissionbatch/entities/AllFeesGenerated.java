package com.hadday.commissionbatch.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AllFeesGenerated implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date_calcul_commission;
    private Double amount;
    @ManyToOne(targetEntity = Ssatf.class)
    private Ssatf ssatf;
    @ManyToOne(targetEntity = ReleveSolde.class)
    private ReleveSolde releveSolde;
    @ManyToOne(targetEntity = FeeRate.class)
    private FeeRate feeRate;
    @ManyToOne(targetEntity = BookingInstrumentBasis.class)
    private BookingInstrumentBasis bookingInstrumentBasis;
    private String identifiant;
    private String typeCommission;
    private String message;
    private boolean isProcessed;
}
