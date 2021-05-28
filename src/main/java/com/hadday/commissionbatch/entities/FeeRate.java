package com.hadday.commissionbatch.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeeRate implements Serializable, Comparable<FeeRate> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private FeeType feeType;
    @ManyToOne
    private InstrumentCategorie instrumentCategorie;
    private double feeRate;
    private double montant;
    private char tauxMontant;
    private boolean deleted;

    public FeeRate(Long feeRate_id) {
        this.id = feeRate_id;
    }

    @Override
    public int compareTo(FeeRate o) {
        if (this.getInstrumentCategorie().equals(o.getInstrumentCategorie()) &&
                this.getFeeType().equals(o.getFeeType()) &&
                this.getTauxMontant() == o.getTauxMontant() &&
                this.getFeeRate() == o.getFeeRate() &&
                this.getMontant() == o.getMontant()
        ) {
            return 1;
        } else {
            return -1;
        }
    }
}
