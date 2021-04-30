package com.hadday.commissionbatch.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InstrumentType implements Serializable, Comparable<InstrumentType> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String instrumentTypeName;
    private String instrumentTypeCode;
    private boolean deleted;
    @ManyToOne
    private InstrumentClass instrumentClass;
    @OneToMany(mappedBy = "instrumentType", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Collection<InstrumentClassBasisInstrument> instrumentClassBasisInstruments;

    @OneToMany(mappedBy = "instrumentType", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Collection<InstrumentCategorie> instrumentCategories;

    @Override
    public int compareTo(InstrumentType o) {
        if (
                this.getInstrumentTypeName().equals(o.getInstrumentTypeName()) &&
                        this.getInstrumentClass().equals(o.getInstrumentClass()) &&
                        this.getInstrumentTypeCode().equals(o.getInstrumentTypeCode())
        ) {
            return 1;
        } else {
            return -1;
        }
    }
}
