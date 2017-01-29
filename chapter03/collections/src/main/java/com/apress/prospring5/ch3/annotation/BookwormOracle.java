package com.apress.prospring5.ch3.annotation;

import org.springframework.stereotype.Service;

import com.apress.prospring5.ch3.Oracle;

@Service("oracle")
public class BookwormOracle implements Oracle {
    @Override
    public String defineMeaningOfLife() {
        return "Encyclopedias are a waste of money - use the Internet";
    }
}
