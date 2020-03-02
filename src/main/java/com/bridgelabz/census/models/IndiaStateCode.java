package com.bridgelabz.census.models;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCode {

    @CsvBindByName(column = "State Name", required = true)
    private String stateName;

    @CsvBindByName(column = "TIN", required = true)
    private String tin;

    @CsvBindByName(column = "StateCode", required = true)
    private String stateCode;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @Override
    public String toString() {
        return "IndiaStateCode{" +
                "stateName='" + stateName + '\'' +
                ", tin='" + tin + '\'' +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}