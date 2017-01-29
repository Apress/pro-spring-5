package com.apress.prospring4.ch11;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name="car")
public class Car {
    private Long id;
    private String licensePlate;
    private String manufacturer;
    private DateTime manufactureDate;
    private int age;
    private int version;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Column(name="LICENSE_PLATE")
    public String getLicensePlate() {
        return licensePlate;
    }

    @Column(name="MANUFACTURER")
    public String getManufacturer() {
        return manufacturer;
    }

    @Column(name="MANUFACTURE_DATE")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getManufactureDate() {
        return manufactureDate;
    }

    @Column(name="AGE")
    public int getAge() {
        return age;
    }

    @Version
    public int getVersion() {
        return version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setManufactureDate(DateTime manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "License: " + licensePlate + " - Manufacturer: " + manufacturer
                + " - Manufacture Date: " + manufactureDate + " - Age: " + age;
    }
}
