package com.hadday.commissionbatch.constante;

abstract public class Queries {

    public static final String DA_Avoirs_Query = " Select fee from InstrumentClass c " +
            " INNER JOIN c.instrumentTypes t on c = t.instrumentClass " +
            " INNER JOIN t.instrumentCategories cat on t = cat.instrumentType " +
            " INNER JOIN cat.feeRates fee on cat = fee.instrumentCategorie " +
            " where fee.feeType.categorieFees.typeCommission = :typeCommission " +
            " and c.instrementClass = :className " +
            " and t.instrumentTypeCode = :typeCode " +
            " and cat.category = :category " +
            " and fee.tauxMontant = 'T'" +
            " and c.deleted= false" +
            " and t.deleted= false" +
            " and cat.deleted= false" +
            " and fee.deleted= false";

    public static final String Compte_Query = " Select fee from InstrumentClass c " +
            " INNER JOIN c.instrumentTypes t on c = t.instrumentClass " +
            " INNER JOIN t.instrumentCategories cat on t = cat.instrumentType " +
            " INNER JOIN cat.feeRates fee on cat = fee.instrumentCategorie " +
            " where fee.feeType.typeName = :feeType " +
            " and fee.feeType.categorieFees.typeCommission = :typeCommission " +
            " and c.instrementClass = :className " +
            " and t.instrumentTypeCode = :typeCode " +
            " and cat.category = :category " +
            " and fee.tauxMontant = 'M' " +
            " and c.deleted= false" +
            " and t.deleted= false" +
            " and cat.deleted= false " +
            " and fee.deleted= false ";

    public static final  String ECART_AVOIR_EXIST_QUERY = " Select a from  RelevesoldesAvoirs a " +
            " where a.CLASS = :instrClass" +
            " and a.TYPE = :instrType " +
            " and a.INSTRCTGRY = :instrCat ";


    public static final String GET_ALLFEES_BY_IDENTITY = " select a from AllFees a " +
            " where " +
            " a.FEECATEGORY = :feeCategorie  " +
            " and DATE(a.DATE) = :DATE " +
            " and a.ISIN = :ISIN " +
            " and a.FEEBASIS = :FEEBASIS " +
            " and a.PRICE = :price " +
            " and a.BPID_RECIPIENT = :BPID_RECIPIENT" +
            " and a.BPID_LIABLE = :BPID_LIABLE ";

}


