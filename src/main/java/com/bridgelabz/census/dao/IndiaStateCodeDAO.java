package com.bridgelabz.census.dao;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeDAO {

    @CsvBindByName(column = "State Name", required = true)
    private String stateName;

    @CsvBindByName(column = "StateCode", required = true)
    private String stateCode;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}