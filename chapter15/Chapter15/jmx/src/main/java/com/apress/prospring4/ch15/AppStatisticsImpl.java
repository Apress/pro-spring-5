package com.apress.prospring4.ch15;

import org.springframework.beans.factory.annotation.Autowired;

public class AppStatisticsImpl implements AppStatistics {
    @Autowired
    private ContactService contactService;

    @Override
    public int getTotalContactCount() {
        return contactService.findAll().size();
    }
}
