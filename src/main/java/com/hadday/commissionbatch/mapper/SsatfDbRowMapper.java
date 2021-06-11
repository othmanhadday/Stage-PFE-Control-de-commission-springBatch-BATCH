package com.hadday.commissionbatch.mapper;

import com.hadday.commissionbatch.entities.Ssatf;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class SsatfDbRowMapper implements RowMapper<Ssatf> {
    @Override
    public Ssatf mapRow(ResultSet rs, int i) throws SQLException {
        Ssatf ssatf = new Ssatf();
        ssatf.setId(rs.getLong("id"));
        ssatf.setOrderreference(rs.getString("orderreference"));
        ssatf.setTradeprice(rs.getDouble("tradeprice"));
        ssatf.setSecurityid(rs.getString("securityid"));
        ssatf.setQuantity(rs.getDouble("quantity"));
        ssatf.setTradedate(rs.getDate("tradedate"));
        ssatf.setCLASSID(rs.getString("CLASSID"));
        ssatf.setINSTRTYPE(rs.getString("INSTRTYPE"));
        ssatf.setINSTRCTGRY(rs.getString("INSTRCTGRY"));
        ssatf.setAccountno(rs.getLong("accountno"));
        ssatf.setQuantity(rs.getDouble("quantity"));

        return ssatf;
    }
}
