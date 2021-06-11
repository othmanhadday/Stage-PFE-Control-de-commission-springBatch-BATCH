package com.hadday.commissionbatch.mapper;

import com.hadday.commissionbatch.entities.RelevesoldesAvoirs;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ReleveSoldeDbRowMapper implements RowMapper<RelevesoldesAvoirs> {

    @Override
    public RelevesoldesAvoirs mapRow(ResultSet rs, int i) throws SQLException {
        RelevesoldesAvoirs relevesoldesAvoirs = new RelevesoldesAvoirs();
        relevesoldesAvoirs.setId(rs.getLong("id"));
        relevesoldesAvoirs.setQUANTITE(rs.getDouble("quantite"));
        relevesoldesAvoirs.setPrice(rs.getDouble("price"));
        relevesoldesAvoirs.setCLASS(rs.getString("class"));
        relevesoldesAvoirs.setTYPE(rs.getString("type"));
        relevesoldesAvoirs.setINSTRCTGRY(rs.getString("instrctgry"));
        relevesoldesAvoirs.setDATE_MAJ(rs.getDate("date_maj"));
        relevesoldesAvoirs.setCODE_MANDATAIRE(rs.getString("code_mandataire"));
        relevesoldesAvoirs.setCODE_MANDANT(rs.getString("code_mandant"));
        relevesoldesAvoirs.setCODE_VALEUR(rs.getString("code_valeur"));
        relevesoldesAvoirs.setQUANTITE(rs.getDouble("quantite"));

        return relevesoldesAvoirs;
    }
}
