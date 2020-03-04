package com.bridgelabz.census.models;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCensus {

    @CsvBindByName(column = "State", required = true)
    private String state;

    @CsvBindByName(column = "Population", required = true)
    private Long population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    private Long area;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    private Long density;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public Long getDensity() {
        return density;
    }

    public void setDensity(Long density) {
        this.density = density;
    }

    @Override
    public String toString() {
        return "IndiaStateCensus{" +
                "state='" + state + '\'' +
                ", population=" + population +
                ", area=" + area +
                ", density=" + density +
                '}';
    }
}
