package com.apress.prospring5.ch8.service;

import com.apress.prospring5.ch8.view.SingerSummary;

import java.util.List;

public interface SingerSummaryService {
    List<SingerSummary> findAll();
}
