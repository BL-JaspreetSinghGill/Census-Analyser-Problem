package com.bridgelabz.census.models;

public class IndiaStateCensus {

    private String state;

    private Long population;

    private Long area;

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
