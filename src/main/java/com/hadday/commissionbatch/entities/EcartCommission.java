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
public class EcartCommission{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeCommission;
    @ManyToOne
     public Ssatf ssatf;
    //    public Mouvement mouvement;
    @ManyToOne
    public ReleveSolde releveSolde;
}
