package com.hadday.commissionbatch.mapper;

import com.hadday.commissionbatch.entities.RelevesoldesComptes;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CompteDbRowMapper implements RowMapper<RelevesoldesComptes> {

    @Override
    public RelevesoldesComptes mapRow(ResultSet rs, int i) throws SQLException {
        RelevesoldesComptes releveSolde = new RelevesoldesComptes();
        releveSolde.setId(rs.getLong("id"));
        releveSolde.setQUANTITE(rs.getDouble("quantite"));
        releveSolde.setPrice(rs.getDouble("price"));
        releveSolde.setCLASS(rs.getString("class"));
        releveSolde.setTYPE(rs.getString("type"));
        releveSolde.setINSTRCTGRY(rs.getString("instrctgry"));
        releveSolde.setDATE_MAJ(rs.getDate("date_maj"));
        releveSolde.setCODE_MANDATAIRE(rs.getString("code_mandataire"));
        releveSolde.setCODE_MANDANT(rs.getString("code_mandant"));
        releveSolde.setCODE_VALEUR(rs.getString("code_valeur"));
        releveSolde.setQUANTITE(rs.getDouble("quantite"));

        return releveSolde;
    }
}
