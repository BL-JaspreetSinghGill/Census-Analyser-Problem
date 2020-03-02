package com.bridgelabz.census;

import com.bridgelabz.census.models.IndiaStateCensus;
import com.bridgelabz.census.models.IndiaStateCode;
import com.bridgelabz.census.utility.MessageHelper;

public class CensusAnalyser {

    private static ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();

    public static int loadIndiaStatesCensusData(String INDIA_STATES_CENSUS_FILE_PATH) {
        return csvBuilder.loadData(INDIA_STATES_CENSUS_FILE_PATH, IndiaStateCensus.class,
                                   MessageHelper.INDIAN_STATES_CENSUS_FILE_NOT_FOUND_MESSAGE);
    }

    public static int loadIndiaStatesCodeData(String INDIA_STATES_CODE_FILE_PATH) {
        return csvBuilder.loadData(INDIA_STATES_CODE_FILE_PATH, IndiaStateCode.class,
                                   MessageHelper.INDIAN_STATES_CODE_FILE_NOT_FOUND_MESSAGE);
    }
}