package com.ahmedsalihh.forexcasestudy.converter;

import com.ahmedsalihh.forexcasestudy.model.Conversion;
import com.ahmedsalihh.forexcasestudy.model.ConversionResponse;

public class ConversionConverter {

    public static Conversion toConversion(ConversionResponse conversionResponse){
        Conversion conversion = new Conversion();
        conversion.setAmount(conversionResponse.getAmount());
        conversion.setFromCurrency(conversionResponse.getFrom());
        conversion.setToCurrency(conversionResponse.getTo());
        conversion.setTransactionDate(conversionResponse.getTransactionDate());
        conversion.setTotalCalculatedAmount(conversionResponse.getTotalAmount());

        return conversion;
    }
}
