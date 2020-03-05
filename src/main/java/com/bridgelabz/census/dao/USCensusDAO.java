package com.bridgelabz.census.dao;

import com.opencsv.bean.CsvBindByName;

public class USCensusDAO {

    @CsvBindByName(column = "StateId", required = true)
    private String stateId;

    @CsvBindByName(column = "State", required = true)
    private String state;

    @CsvBindByName(column = "Population", required = true)
    private Long population;

    @CsvBindByName(column = "TotalArea", required = true)
    private Double area;

    @CsvBindByName(column = "PopulationDensity", required = true)
    private Double density;

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

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

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }
}
