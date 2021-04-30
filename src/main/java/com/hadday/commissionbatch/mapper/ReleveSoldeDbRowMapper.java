package com.hadday.commissionbatch.mapper;

import com.hadday.commissionbatch.entities.ReleveSolde;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ReleveSoldeDbRowMapper implements RowMapper<ReleveSolde> {

    @Override
    public ReleveSolde mapRow(ResultSet rs, int i) throws SQLException {
        ReleveSolde releveSolde = new ReleveSolde();
        releveSolde.setId(rs.getLong("id"));
        releveSolde.setCODE_VALEUR(rs.getString("CODE_VALEUR"));
        releveSolde.setQUANTITE(rs.getDouble("QUANTITE"));
        releveSolde.setPrice(rs.getDouble("Price"));
        releveSolde.setCLASS(rs.getString("CLASS"));
        releveSolde.setTYPE(rs.getString("TYPE"));
        releveSolde.setINSTRCTGRY(rs.getString("INSTRCTGRY"));
        releveSolde.setDATE_MAJ(rs.getDate("DATE_MAJ"));

        return releveSolde;
    }
}
