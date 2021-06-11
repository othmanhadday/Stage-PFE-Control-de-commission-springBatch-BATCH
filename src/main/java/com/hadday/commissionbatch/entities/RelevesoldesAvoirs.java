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
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude="relevesoldesAvoirs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

//@Table(name = "releve_solde")
public class RelevesoldesAvoirs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date DATE_MAJ;
    private String CODE_MANDATAIRE;
    private String LIBELLE_MANDATAIRE;
    private String CODE_MANDANT;
    private String LIBELLE_MANDANT;
    private Long NUM_COMPTE;
    private String EMETTEUR;
    private String AGENT;
    private String GESTION;
    private String CODE_VALEUR;
    private String LIBELLE_VALEUR;
    private Double QUANTITE;
    private Date DATE_OUVERTURE;
    private Double Price;
    private String SENS;
    private String CLASS;
    private String TYPE;
    private String INSTRCTGRY;
    private Double capitalisation;
    private Date date_alimentation;

    @OneToMany(mappedBy = "relevesoldesAvoirs", cascade = CascadeType.ALL)
    private List<EcartCommission> ecartCommissions;

    @OneToMany(mappedBy = "relevesoldesAvoirs", cascade = CascadeType.ALL)
    private List<AllFeesGenerated> allFeesGenerateds;

    public RelevesoldesAvoirs(Long relevesoldesAvoirsId) {
        this.id=relevesoldesAvoirsId;
    }
}
