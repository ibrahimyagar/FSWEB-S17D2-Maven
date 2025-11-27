package com.workintech.s17d2.tax;

import org.springframework.stereotype.Component;

@Component
public class DeveloperTax implements Taxable{
    public DeveloperTax() {
        super();
    }


    private static  final double SIMPLE_TEXT_RATE=15d;
    private static  final double MIDDLE_TEXT_RATE=25d;
    private static  final double UPPER_TEXT_RATE=35d;


    @Override
    public Double getSimpleTaxRate() {
        return SIMPLE_TEXT_RATE;
    }

    @Override
    public Double getMiddleTaxRate() {
        return MIDDLE_TEXT_RATE;
    }

    @Override
    public Double getUpperTaxRate() {
        return UPPER_TEXT_RATE;
    }
}
