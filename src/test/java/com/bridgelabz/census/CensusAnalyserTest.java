package com.bridgelabz.census;

import com.bridgelabz.census.exceptions.CensusAnalyserException;
import com.bridgelabz.census.models.IndiaStateCensus;
import com.bridgelabz.census.models.IndiaStateCode;
import com.bridgelabz.census.utility.MessageHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class CensusAnalyserTest {

    @Test
    public void givenIndiaStatesCensusFileName_WhenProper_ShouldReturnTotalCount() {
        Map<String, IndiaStateCensus> map = CensusAnalyser.getMap(MessageHelper.INDIA_STATES_CENSUS_FILE_PATH,
                                                                  MessageHelper.INDIA_STATES_CODE_FILE_PATH);
        Assert.assertEquals(29, map.size());
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
        Assert.assertEquals(37, CensusAnalyser.loadIndiaStatesCodeData(MessageHelper.INDIA_STATES_CODE_FILE_PATH));
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
        Map<String, IndiaStateCensus> map = CensusAnalyser.getMap(MessageHelper.INDIA_STATES_CENSUS_FILE_PATH,
                                                                  MessageHelper.INDIA_STATES_CODE_FILE_PATH);
        List<IndiaStateCensus> indiaStateCensusList = CensusAnalyser.getListFromMap(map);
        List<IndiaStateCensus> sortedIndiaStateCensusList = CensusAnalyser.getIndiaStateCensusSortedList(indiaStateCensusList);
        String sortedIndiaStateCensusData = CensusAnalyser.getJsonFormatOfList(sortedIndiaStateCensusList);
        IndiaStateCensus[] indiaStateCensusArray = CensusAnalyser.getArrayOfJson(sortedIndiaStateCensusData);
        Assert.assertEquals("Andhra Pradesh", indiaStateCensusArray[0].getState());
    }

    @Test
    public void givenIndiaStatesCodeData_WhenSortedOnStateCode_ShouldReturnSortedResult() {
        List<IndiaStateCode> indiaStateCodeList = (List<IndiaStateCode>) CensusAnalyser.loadData(MessageHelper.INDIA_STATES_CODE_FILE_PATH,
                                                  IndiaStateCode.class, MessageHelper.INDIAN_STATES_CODE_FILE_NOT_FOUND_MESSAGE);
        List<IndiaStateCode> sortedIndiaStateCodeList = CensusAnalyser.getIndiaStateCodeSortedList(indiaStateCodeList);
        String sortedIndiaStateCodeData = CensusAnalyser.getJsonFormatOfList(sortedIndiaStateCodeList);
        IndiaStateCode[] indiaStateCodeArray = CensusAnalyser.getIndiaStateCodeArrayOfJson(sortedIndiaStateCodeData);
        Assert.assertEquals("AD", indiaStateCodeArray[0].getStateCode());
    }

    @Test
    public void givenIndiaStatesCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        Map<String, IndiaStateCensus> map = CensusAnalyser.getMap(MessageHelper.INDIA_STATES_CENSUS_FILE_PATH,
                                                                  MessageHelper.INDIA_STATES_CODE_FILE_PATH);
        List<IndiaStateCensus> indiaStateCensusList = CensusAnalyser.getListFromMap(map);
        List<IndiaStateCensus> sortedIndiaPopulationCensusList = CensusAnalyser.getIndiaStateCensusSortedListBasedOnPopulation(indiaStateCensusList);
        String sortedIndiaPopulationCensusData = CensusAnalyser.getJsonFormatOfList(sortedIndiaPopulationCensusList);
        IndiaStateCensus[] indiaStateCensusArray = CensusAnalyser.getArrayOfJson(sortedIndiaPopulationCensusData);
        Assert.assertEquals(199812341, indiaStateCensusArray[0].getPopulation(), 0);
    }

    @Test
    public void givenIndiaStatesCensusData_WhenSortedOnDensity_ShouldReturnSortedResult() {
        Map<String, IndiaStateCensus> map = CensusAnalyser.getMap(MessageHelper.INDIA_STATES_CENSUS_FILE_PATH,
                                                                  MessageHelper.INDIA_STATES_CODE_FILE_PATH);
        List<IndiaStateCensus> indiaStateCensusList = CensusAnalyser.getListFromMap(map);
        List<IndiaStateCensus> sortedIndiaDensityCensusList = CensusAnalyser.getIndiaStateCensusSortedListBasedOnDensity(indiaStateCensusList);
        String sortedIndiaDensityCensusData = CensusAnalyser.getJsonFormatOfList(sortedIndiaDensityCensusList);
        IndiaStateCensus[] indiaStateCensusArray = CensusAnalyser.getArrayOfJson(sortedIndiaDensityCensusData);
        Assert.assertEquals(1102, indiaStateCensusArray[0].getDensity(), 0);
    }

    @Test
    public void givenIndiaStatesCensusData_WhenSortedOnArea_ShouldReturnSortedResult() {
        Map<String, IndiaStateCensus> map = CensusAnalyser.getMap(MessageHelper.INDIA_STATES_CENSUS_FILE_PATH,
                                                                    MessageHelper.INDIA_STATES_CODE_FILE_PATH);
        List<IndiaStateCensus> indiaStateCensusList = CensusAnalyser.getListFromMap(map);
        List<IndiaStateCensus> sortedIndiaAreaCensusList = CensusAnalyser.getIndiaStateCensusSortedListBasedOnArea(indiaStateCensusList);
        String sortedIndiaAreaCensusData = CensusAnalyser.getJsonFormatOfList(sortedIndiaAreaCensusList);
        IndiaStateCensus[] indiaStateCensusArray = CensusAnalyser.getArrayOfJson(sortedIndiaAreaCensusData);
        Assert.assertEquals(342239, indiaStateCensusArray[0].getArea(), 0);
    }
}