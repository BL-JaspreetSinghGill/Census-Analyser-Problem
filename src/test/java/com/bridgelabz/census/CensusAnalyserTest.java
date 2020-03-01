package com.bridgelabz.census;

import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserTest {

    private static final String INDIA_STATES_CENSUS_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";

    @Test
    public void givenIndiaStatesCensusFileName_WhenProper_ShouldReturnTotalCount() {
        Assert.assertEquals(29, CensusAnalyser.loadIndiaStatesCensusData(INDIA_STATES_CENSUS_FILE_PATH));
    }
}
