package com.hadday.commissionbatch.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EcartCommission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeCommission;
    private String instClass;
    private String instType;
    private String instCat;
    private boolean deleted;
    @ManyToOne
    public Ssatf ssatf;
    //    public Mouvement mouvement;
    @ManyToOne
    public RelevesoldesAvoirs relevesoldesAvoirs;
    @ManyToOne
    public RelevesoldesComptes relevesoldesComptes;
}
