package com.bridgelabz.census;

import com.bridgelabz.census.exceptions.CensusAnalyserException;
import com.bridgelabz.census.utility.MessageHelper;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserTest {

    private static final String INDIA_STATES_CENSUS_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";

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
}
