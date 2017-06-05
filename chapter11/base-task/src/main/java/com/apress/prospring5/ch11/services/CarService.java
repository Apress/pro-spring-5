package com.apress.prospring5.ch11.services;

import com.apress.prospring5.ch11.ents.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();
    Car save(Car car);
    void updateCarAgeJob();
}
