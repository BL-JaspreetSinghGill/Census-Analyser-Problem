package com.bridgelabz.census.utility;

import com.bridgelabz.census.models.IndiaStateCensus;
import com.bridgelabz.census.models.IndiaStateCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utility {

    public static Map<String, IndiaStateCensus> getMap(List<IndiaStateCensus> indiaStateCensusList, List<IndiaStateCode> indiaStateCodeList) {
        Map<String, IndiaStateCensus> map = new HashMap<>();
        for (IndiaStateCode indiaStateCode: indiaStateCodeList) {
            List<IndiaStateCensus> indiaStateCensusFilteredList = indiaStateCensusList.stream()
                                                                    .filter(data -> data.getState() !=null)
                                                                    .filter(data -> data.getState().equals(indiaStateCode.getStateName()))
                                                                    .collect(Collectors.toList());
            if (indiaStateCensusFilteredList.size() > 0) {
                if (map.get(indiaStateCode.getStateName()) == null) {
                    map.put(indiaStateCode.getStateName(), indiaStateCensusFilteredList.get(0));
                }
            }
        }
        return map;
    }

    public static List<IndiaStateCensus> getListFromMap(Map<String, IndiaStateCensus> map) {
        List<IndiaStateCensus> indiaStateCensusList = new ArrayList<>();
        map.forEach((k, v) -> {
            if (!indiaStateCensusList.contains(v)) {
                indiaStateCensusList.add(v);
            }
        });
        return indiaStateCensusList;
    }
}
