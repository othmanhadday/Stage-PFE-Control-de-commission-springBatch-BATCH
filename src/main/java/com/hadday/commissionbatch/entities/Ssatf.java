package com.hadday.commissionbatch.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ssatf implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderreference;
    private Long accountno;
    private String securityid;
    private String issuercd;
    private String agentid;
    private double quantity;
    private Date tradedate;
    private Date expectedsettlementdate;
    private Date actualsettlementdate;
    private String remarks;
    private String transfer_type;
    private String traderbpid;
    private double settlementamount;
    private double tradeprice;
    private String trade_purpose_reason;
    private String trade_status;
    private String CLASSID;
    private String INSTRCTGRY;
    private String INSTRSUBCTGRY;
    private String INSTRTYPE;
    private Date date_alimentation;
    private boolean deleted;
    @OneToMany(mappedBy = "ssatf")
    @JsonIgnore
    @ToString.Exclude
    private Collection<AllFeesGenerated> allFeesGenerateds;
}
