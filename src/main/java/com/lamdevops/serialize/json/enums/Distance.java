package com.lamdevops.serialize.json.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Distance {
    KILOMETER("km", 1000),
    MILE("miles", 1609.34),
    METER("meters", 1),
    INCH("inches", 0.0254),
    CENTIMETER("cm", 0.01),
    MILLIMETER("mm", 0.0001);

    private String unit;
    private final double meters;

    Distance(String unit, double meters) {
        this.unit = unit;
        this.meters = meters;
    }

    public String getUnit() {
        return unit;
    }

    public double getMeters() {
        return meters;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "unit='" + unit + '\'' +
                ", meters=" + meters +
                '}';
    }
}
