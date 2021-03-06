package com.bridgelabz.census;

import com.bridgelabz.census.dao.IndiaStateCensusDAO;
import com.bridgelabz.census.dao.IndiaStateCodeDAO;
import com.bridgelabz.census.dao.USCensusDAO;
import com.bridgelabz.census.exceptions.CSVBuilderException;
import com.bridgelabz.census.exceptions.CensusAnalyserException;
import com.bridgelabz.census.models.IndiaStateCensus;
import com.bridgelabz.census.models.IndiaStateCode;
import com.bridgelabz.census.models.USCensus;
import com.bridgelabz.census.utility.MessageHelper;
import com.bridgelabz.census.utility.CensusHelper;
import com.google.gson.Gson;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CensusAnalyser {

    private static ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();

    public static List<?> loadData(String FILE_PATH, Class c, String message) {
        try {
            return csvBuilder.loadCSVData(FILE_PATH, c, message);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(message);
        }
    }

    public static List<IndiaStateCensus> getIndiaStateCensusSortedList(List<IndiaStateCensus> list) {
        list.sort(Comparator.comparing(IndiaStateCensus::getState)
                            .thenComparing(IndiaStateCensus::getState));
        return list;
    }

    public static List<IndiaStateCensus> getIndiaStateCensusSortedListBasedOnPopulation(List<IndiaStateCensus> list) {
        list.sort(Comparator.comparing(IndiaStateCensus::getPopulation)
                            .reversed());
        return list;
    }

    public static List<IndiaStateCensus> getIndiaStateCensusSortedListBasedOnDensity(List<IndiaStateCensus> list) {
        list.sort(Comparator.comparing(IndiaStateCensus::getDensity)
                            .reversed());
        return list;
    }

    public static List<IndiaStateCensus> getIndiaStateCensusSortedListBasedOnArea(List<IndiaStateCensus> list) {
        list.sort(Comparator.comparing(IndiaStateCensus::getArea)
                            .reversed());
        return list;
    }

    public static List<IndiaStateCode> getIndiaStateCodeSortedList(List<IndiaStateCode> list) {
        list.sort(Comparator.comparing(IndiaStateCode::getStateCode)
                            .thenComparing(IndiaStateCode::getStateCode));
        return list;
    }

    public static List<USCensus> getUSCensusSortedListBasedOnState(List<USCensus> list) {
        list.sort(Comparator.comparing(USCensus::getState)
                            .thenComparing(USCensus::getState));
        return list;
    }

    public static List<USCensus> getUSCensusSortedListBasedOnPopulation(List<USCensus> list) {
        list.sort(Comparator.comparing(USCensus::getPopulation)
                            .reversed());
        return list;
    }

    public static List<USCensus> getUSCensusSortedListBasedOnDensity(List<USCensus> list) {
        list.sort(Comparator.comparing(USCensus::getDensity)
                            .reversed());
        return list;
    }

    public static List<USCensus> getUSCensusSortedListBasedOnArea(List<USCensus> list) {
        list.sort(Comparator.comparing(USCensus::getArea)
                            .reversed());
        return list;
    }

    public static <T> String getJsonFormatOfList(List<T> list) {
        return new Gson().toJson(list);
    }

    public static IndiaStateCensus[] getArrayOfJson(String sortedIndiaStateCensusData) {
        return new Gson().fromJson(sortedIndiaStateCensusData, IndiaStateCensus[].class);
    }

    public static IndiaStateCode[] getIndiaStateCodeArrayOfJson(String sortedIndiaStateCensusData) {
        return new Gson().fromJson(sortedIndiaStateCensusData, IndiaStateCode[].class);
    }

    public static USCensus[] getUSCensusArrayOfJson(String sortedUSCensusData) {
        return new Gson().fromJson(sortedUSCensusData, USCensus[].class);
    }


    public static Map<String, IndiaStateCensus> getMap(String INDIA_STATE_CENSUS_FILE_PATH, String INDIA_STATE_CODE_FILE_PATH) {
        List<IndiaStateCensusDAO> indiaStateCensusDAOList = (List<IndiaStateCensusDAO>) csvBuilder.loadCSVData(INDIA_STATE_CENSUS_FILE_PATH,
                                                            IndiaStateCensusDAO.class, MessageHelper.INDIAN_STATES_CENSUS_FILE_NOT_FOUND_MESSAGE);
        List<IndiaStateCodeDAO> indiaStateCodeDAOList = (List<IndiaStateCodeDAO>) csvBuilder.loadCSVData(INDIA_STATE_CODE_FILE_PATH,
                                                        IndiaStateCodeDAO.class, MessageHelper.INDIAN_STATES_CODE_FILE_NOT_FOUND_MESSAGE);
        List<IndiaStateCensus> indiaStateCensusList = CensusHelper.convertCensusDAOToCensusObj(indiaStateCensusDAOList);
        List<IndiaStateCode> indiaStateCodeList = CensusHelper.convertStateDAOToStateObj(indiaStateCodeDAOList);
        return CensusHelper.getMap(indiaStateCensusList, indiaStateCodeList);
    }

    public static List<IndiaStateCensus> getListFromMap(Map<String, IndiaStateCensus> map) {
        return CensusHelper.getListFromMap(map);
    }

    public static List<USCensus> getUSList(List<USCensusDAO> usCensusDAOList) {
        return CensusHelper.convertUSCensusDAOToUSCensus(usCensusDAOList);
    }

    public static List<IndiaStateCensus> getIndiaStateCensusData() {
        Map<String, IndiaStateCensus> map = getMap(MessageHelper.INDIA_STATES_CENSUS_FILE_PATH,
                                                   MessageHelper.INDIA_STATES_CODE_FILE_PATH);
        return getListFromMap(map);
    }

    public static List<USCensus> getUSStateCensusData() {
        List<USCensusDAO> usCensusDAOList = (List<USCensusDAO>) loadData(MessageHelper.US_CENSUS_FILE_PATH,
                                            USCensusDAO.class, MessageHelper.US_CENSUS_FILE_NOT_FOUND_MESSAGE);
        return getUSList(usCensusDAOList);
    }

    public static USCensus[] getUSStateCensusArray(List<USCensus> sortedUSCensusList) {
        String sortedUSCensusData = getJsonFormatOfList(sortedUSCensusList);
        return getUSCensusArrayOfJson(sortedUSCensusData);
    }

    public static IndiaStateCensus[] getIndiaStateCensusArray(List<IndiaStateCensus> sortedIndiaCensusList) {
        String sortedIndiaCensusData = getJsonFormatOfList(sortedIndiaCensusList);
        return getArrayOfJson(sortedIndiaCensusData);
    }
}