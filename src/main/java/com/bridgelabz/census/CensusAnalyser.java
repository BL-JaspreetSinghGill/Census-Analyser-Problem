package com.bridgelabz.census;

import com.bridgelabz.census.exceptions.CSVBuilderException;
import com.bridgelabz.census.exceptions.CensusAnalyserException;
import com.bridgelabz.census.models.IndiaStateCensus;
import com.bridgelabz.census.models.IndiaStateCode;
import com.bridgelabz.census.utility.MessageHelper;
import com.google.gson.Gson;

import java.util.Comparator;
import java.util.List;

public class CensusAnalyser {

    private static ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();

    public static int loadIndiaStatesCensusData(String INDIA_STATES_CENSUS_FILE_PATH) {
        try {
            return csvBuilder.loadData(INDIA_STATES_CENSUS_FILE_PATH, IndiaStateCensus.class,
                                      MessageHelper.INDIAN_STATES_CENSUS_FILE_NOT_FOUND_MESSAGE);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage());
        }
    }

    public static int loadIndiaStatesCodeData(String INDIA_STATES_CODE_FILE_PATH) {
        try {
            return csvBuilder.loadData(INDIA_STATES_CODE_FILE_PATH, IndiaStateCode.class,
                                       MessageHelper.INDIAN_STATES_CODE_FILE_NOT_FOUND_MESSAGE);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage());
        }
    }

    public static List<?> loadData(String FILE_PATH, Class c, String message) {
        return csvBuilder.loadCSVData(FILE_PATH, c, message);
    }

    public static List<IndiaStateCensus> getIndiaStateCensusSortedList(List<IndiaStateCensus> list) {
        list.sort(Comparator.comparing(IndiaStateCensus::getState)
                            .thenComparing(IndiaStateCensus::getState));
        return list;
    }

    public static List<IndiaStateCode> getIndiaStateCodeSortedList(List<IndiaStateCode> list) {
        list.sort(Comparator.comparing(IndiaStateCode::getStateCode)
                            .thenComparing(IndiaStateCode::getStateCode));
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
}