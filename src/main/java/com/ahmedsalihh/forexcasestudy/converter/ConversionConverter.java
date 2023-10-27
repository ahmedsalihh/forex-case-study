package com.ahmedsalihh.forexcasestudy.converter;

import com.ahmedsalihh.forexcasestudy.model.Conversion;
import com.ahmedsalihh.forexcasestudy.model.ConversionApiResponse;
import com.ahmedsalihh.forexcasestudy.model.ConversionResponse;

public class ConversionConverter {

    public static Conversion toConversion(ConversionApiResponse conversionApiResponse) {
        Conversion conversion = new Conversion();
        conversion.setAmount(conversionApiResponse.getAmount());
        conversion.setFromCurrency(conversionApiResponse.getFrom());
        conversion.setToCurrency(conversionApiResponse.getTo());
        conversion.setTransactionDate(conversionApiResponse.getTransactionDate());
        conversion.setTotalCalculatedAmount(conversionApiResponse.getTotalAmount());

        return conversion;
    }

    public static ConversionResponse toConversionResponse(Conversion conversion) {
        ConversionResponse conversionResponse = new ConversionResponse();
        conversionResponse.setConvertedAmount(conversion.getTotalCalculatedAmount());
        conversionResponse.setTransactionId(conversion.getTransactionId());

        return conversionResponse;
    }
}
