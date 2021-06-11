package com.hadday.commissionbatch.mapper;

import com.hadday.commissionbatch.entities.*;
import com.hadday.commissionbatch.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class AllFeesDbEowMapper implements RowMapper<AllFeesGenerated> {

    @Autowired
    private FeeRateRepository feeRateRepository;
    @Autowired
    private BookingInstrumentBasisRepository bookingInstrumentBasisRepository;
    @Autowired
    private ReleveSoldeAvoirsRepository releveSoldeAvoirsRepository;
    @Autowired
    private ReleveSoldeCompteRepository releveSoldeCompteRepository;
    @Autowired
    private SsatfRepository ssatfRepository;

    @Override
    public AllFeesGenerated mapRow(ResultSet rs, int i) throws SQLException {

        AllFeesGenerated allFeesGenerated = new AllFeesGenerated();
        allFeesGenerated.setId(rs.getLong("id"));
        allFeesGenerated.setAmount(rs.getDouble("amount"));
        allFeesGenerated.setDate_calcul_commission(rs.getDate("date_calcul_commission"));
        allFeesGenerated.setIdentifiant(rs.getString("identifiant"));
        allFeesGenerated.setTypeCommission(rs.getString("type_commission"));
        allFeesGenerated.setBPID_LIABLE(rs.getString("bpid_liable"));
        allFeesGenerated.setBPID_RECIPIENT(rs.getString("bpid_recipient"));
        allFeesGenerated.setQuantite(rs.getDouble("quantite"));
        Long feeRate_id = rs.getLong("fee_rate_id");
        Long bookingInstrumentBasisId = rs.getLong("booking_instrument_basis_id");
        Long relevesoldesAvoirsId = rs.getLong("relevesoldes_avoirs_id");
        Long relevesoldesComptesId = rs.getLong("relevesoldes_comptes_id");
        Long ssatfId = rs.getLong("ssatf_id");
        if (feeRate_id != 0) {
            allFeesGenerated.setFeeRate(new FeeRate(feeRate_id));
        }
        if (bookingInstrumentBasisId != 0) {
            allFeesGenerated.setBookingInstrumentBasis(new BookingInstrumentBasis(bookingInstrumentBasisId));
        }
        if (relevesoldesAvoirsId != 0) {
            allFeesGenerated.setRelevesoldesAvoirs(new RelevesoldesAvoirs(relevesoldesAvoirsId));
        }
        if (relevesoldesComptesId != 0) {
            allFeesGenerated.setRelevesoldesComptes(new RelevesoldesComptes(relevesoldesComptesId));
        }
        if (ssatfId != 0) {
            allFeesGenerated.setSsatf(new Ssatf(ssatfId));
        }
        allFeesGenerated.setISIN(rs.getString("isin"));
        allFeesGenerated.setDate(rs.getDate("date"));


//        AllFees allFees = new AllFees();
//        allFees.setCOM_SEQ(rs.getLong("COM_SEQ"));
//        allFees.setFACT_NO(rs.getString("FACT_NO"));
//        allFees.setDATE(rs.getDate("DATE"));
//        allFees.setBPID_RECIPIENT(rs.getString("BPID_RECIPIENT"));
//        allFees.setBPID_LIABLE(rs.getString("BPID_LIABLE"));
//        allFees.setFEECATEGORY(rs.getString("FEECATEGORY"));
//        allFees.setFEETYPE(rs.getString("FEETYPE"));
//        allFees.setTRANSACTIONREFERENCE(rs.getString("TRANSACTIONREFERENCE"));
//        allFees.setTRANSACTIONTYPE(rs.getString("TRANSACTIONTYPE"));
//        allFees.setTRADEDATE(rs.getDate("TRADEDATE"));
//        allFees.setSETTLEMENTDATE(rs.getDate("SETTLEMENTDATE"));
//        allFees.setACNO(rs.getLong("ACNO"));
//        allFees.setACCOUNTTYPE(rs.getString("ACCOUNTTYPE"));
//        allFees.setACCATEGORY(rs.getString("ACCATEGORY"));
//        allFees.setISIN(rs.getString("ISIN"));
//        allFees.setISIN_CREATIONDATE(rs.getString("ISIN_CREATIONDATE"));
//        allFees.setINSTRUMENTTYPE(rs.getString("INSTRUMENTTYPE"));
//        allFees.setPRICE(rs.getDouble("PRICE"));
//        allFees.setFEEBASIS(rs.getDouble("FEEBASIS"));
//        allFees.setAMOUNT(rs.getDouble("AMOUNT"));
//        allFees.setCURRENCY(rs.getString("CURRENCY"));
//        allFees.setBPID_AMC4MF(rs.getLong("BPID_AMC4MF"));
//        allFees.setDC(rs.getString("DC"));
//        allFees.setCAPI(rs.getDouble("CAPI"));
//
        return allFeesGenerated;
//        return allFees;
    }
}
