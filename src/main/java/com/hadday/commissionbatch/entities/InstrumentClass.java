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
@ToString(exclude = "instrumentTypes")
public class InstrumentClass implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String instrementClass;
    private boolean deleted;
    @OneToMany(mappedBy = "instrumentClass", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<InstrumentType> instrumentTypes;
}
