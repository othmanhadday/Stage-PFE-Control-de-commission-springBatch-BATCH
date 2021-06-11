package com.hadday.commissionbatch.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class AllFeesGenerated implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date_calcul_commission;
    private Date date;
    @Column(columnDefinition = "double(24,12)")
    private Double amount;
    @ManyToOne(targetEntity = Ssatf.class, cascade = CascadeType.MERGE)
    private Ssatf ssatf;
    @ManyToOne
    private RelevesoldesAvoirs relevesoldesAvoirs;
    @ManyToOne(targetEntity = RelevesoldesComptes.class, fetch = FetchType.LAZY)
    private RelevesoldesComptes relevesoldesComptes;
    @ManyToOne
    private FeeRate feeRate;
    @ManyToOne(targetEntity = BookingInstrumentBasis.class, fetch = FetchType.LAZY)
    private BookingInstrumentBasis bookingInstrumentBasis;
    private String BPID_RECIPIENT;
    private String BPID_LIABLE;
    private String identifiant;
    private String typeCommission;
    private String ISIN;
    private double quantite;
    private double prix;

}
