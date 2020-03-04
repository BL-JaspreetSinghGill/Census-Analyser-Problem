package com.bridgelabz.census;

import com.bridgelabz.census.exceptions.CensusAnalyserException;
import com.bridgelabz.census.models.IndiaStateCensus;
import com.bridgelabz.census.utility.MessageHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CensusAnalyserTest {

    private static final String INDIA_STATES_CENSUS_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";

    private static final String INDIA_STATES_CODE_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";

    @Test
    public void givenIndiaStatesCensusFileName_WhenProper_ShouldReturnTotalCount() {
        Assert.assertEquals(29, CensusAnalyser.loadIndiaStatesCensusData(INDIA_STATES_CENSUS_FILE_PATH));
    }

    @Test
    public void givenIndiaStatesCensusFileName_WhenImproper_ShouldThrowException() {
        try {
            CensusAnalyser.loadIndiaStatesCensusData("abc.csv");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(MessageHelper.INDIAN_STATES_CENSUS_FILE_NOT_FOUND_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void givenIndiaStatesCodeFileName_WhenProper_ShouldReturnTotalCount() {
        Assert.assertEquals(37, CensusAnalyser.loadIndiaStatesCodeData(INDIA_STATES_CODE_FILE_PATH));
    }

    @Test
    public void givenIndiaStatesCodeFileName_WhenImproper_ShouldThrowException() {
        try {
            CensusAnalyser.loadIndiaStatesCodeData("abc.csv");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(MessageHelper.INDIAN_STATES_CODE_FILE_NOT_FOUND_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void givenIndiaStatesCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
        List<IndiaStateCensus> indiaStateCensusList = (List<IndiaStateCensus>) CensusAnalyser.loadData(INDIA_STATES_CENSUS_FILE_PATH,
                                                    IndiaStateCensus.class, MessageHelper.INDIAN_STATES_CENSUS_FILE_NOT_FOUND_MESSAGE);
        List<IndiaStateCensus> sortedIndiaStateCensuslist = CensusAnalyser.getIndiaStateCensusSortedList(indiaStateCensusList);
        String sortedIndiaStateCensusData = CensusAnalyser.getJsonFormatOfList(sortedIndiaStateCensuslist);
        IndiaStateCensus[] indiaStateCensusArray = CensusAnalyser.getArrayOfJson(sortedIndiaStateCensusData);
        Assert.assertEquals("Andhra Pradesh", indiaStateCensusArray[0].getState());
    }
}