package com.lamdevops.annotation.validator.composition;

import com.lamdevops.annotation.validator.CheckCase.CaseMode;
import com.lamdevops.annotation.validator.CheckCase.CheckCase;
import com.lamdevops.annotation.validator.CheckCase.ValidPassengerCount;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Car {

    @NotNull
    private String manufacturer;

    @ValidLicensePlate
    private String licensePlate;

    @Min(2)
    private int seatCount;

    private List<String> passengers;

    public Car() {
    }

    public Car(String manufacturer, String licensePlate, int seatCount) {
        this.manufacturer = manufacturer;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
    }

    public Car(String manufacturer, String licensePlate, int seatCount, List<String> passengers) {
        this.manufacturer = manufacturer;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.passengers = passengers;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<String> passengers) {
        this.passengers = passengers;
    }
}
