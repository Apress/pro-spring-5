package com.apress.prospring5.ch3.xml;

import com.apress.prospring5.ch3.Oracle;

public class BookwormOracle implements Oracle {
    @Override
    public String defineMeaningOfLife() {
        return "Encyclopedias are a waste of money - use the Internet"; 
    }
}
