package com.hadday.commissionbatch.constante;

abstract public class Queries {

    public static final String ssatfQuery = " Select fee from InstrumentClass c " +
            " INNER JOIN c.instrumentTypes t on c = t.instrumentClass " +
            " INNER JOIN t.instrumentCategories cat on t = cat.instrumentType " +
            " INNER JOIN cat.feeRates fee on cat = fee.instrumentCategorie " +
            " where c.instrementClass = :className " +
            " and t.instrumentTypeCode = :typeCode " +
            " and cat.category = :category " +
            " and fee.feeType.categorieFees.typeCommission = :typeCommission " +
            " and fee.tauxMontant = 'T'" +
            " and c.deleted= false" +
            " and t.deleted= false" +
            " and cat.deleted= false" +
            " and fee.deleted= false";

//    public static final String avoirQuery = " Select fee from InstrumentClass c " +
//            " INNER JOIN c.instrumentTypes t on c = t.instrumentClass " +
//            " INNER JOIN t.instrumentCategories cat on t = cat.instrumentType " +
//            " INNER JOIN cat.feeRates fee on cat = fee.instrumentCategorie " +
//            " where c.instrementClass = :className " +
//            " and t.instrumentTypeCode = :typeCode " +
//            " and cat.category = :category " +
//            " and fee.feeType.categorieFees.typeCommission.commission = 'Avoirs' " +
//            " and fee.tauxMontant = 'T'" +
//            " and c.deleted= false" +
//            " and t.deleted= false" +
//            " and cat.deleted= false" +
//            " and fee.deleted= false";

}


