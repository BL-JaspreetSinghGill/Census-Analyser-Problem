package com.bridgelabz.census;

import com.bridgelabz.census.dao.IndiaStateCensusDAO;
import com.bridgelabz.census.dao.IndiaStateCodeDAO;
import com.bridgelabz.census.dao.USCensusDAO;
import com.bridgelabz.census.exceptions.CensusAnalyserException;
import com.bridgelabz.census.models.IndiaStateCensus;
import com.bridgelabz.census.models.IndiaStateCode;
import com.bridgelabz.census.models.USCensus;
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
            CensusAnalyser.loadData("abc.csv", IndiaStateCensusDAO.class,
                                    MessageHelper.INDIAN_STATES_CENSUS_FILE_NOT_FOUND_MESSAGE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(MessageHelper.INDIAN_STATES_CENSUS_FILE_NOT_FOUND_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void givenIndiaStatesCodeFileName_WhenProper_ShouldReturnTotalCount() {
        List<IndiaStateCodeDAO> indiaStateCodeDAOList = (List<IndiaStateCodeDAO>) CensusAnalyser.loadData(MessageHelper.INDIA_STATES_CODE_FILE_PATH,
                                                        IndiaStateCodeDAO.class, MessageHelper.INDIAN_STATES_CODE_FILE_NOT_FOUND_MESSAGE);
        Assert.assertEquals(37, indiaStateCodeDAOList.size());
    }

    @Test
    public void givenIndiaStatesCodeFileName_WhenImproper_ShouldThrowException() {
        try {
            CensusAnalyser.loadData("abc.csv", IndiaStateCodeDAO.class,
                                    MessageHelper.INDIAN_STATES_CODE_FILE_NOT_FOUND_MESSAGE);
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

    @Test
    public void givenUSStateCensusFileName_WhenProper_ShouldReturnTotalCount() {
        List<USCensusDAO> usCensusDAOList = (List<USCensusDAO>) CensusAnalyser.loadData(MessageHelper.US_CENSUS_FILE_PATH,
                                            USCensusDAO.class, MessageHelper.US_CENSUS_FILE_NOT_FOUND_MESSAGE);
        Assert.assertEquals(51, usCensusDAOList.size());
    }

    @Test
    public void givenUSStateCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
        List<USCensusDAO> usCensusDAOList = (List<USCensusDAO>) CensusAnalyser.loadData(MessageHelper.US_CENSUS_FILE_PATH,
                                            USCensusDAO.class, MessageHelper.US_CENSUS_FILE_NOT_FOUND_MESSAGE);
        List<USCensus> usCensusList = CensusAnalyser.getUSList(usCensusDAOList);
        List<USCensus> sortedUSCensusList = CensusAnalyser.getUSCensusSortedListBasedOnState(usCensusList);
        String sortedUSCensusData = CensusAnalyser.getJsonFormatOfList(sortedUSCensusList);
        USCensus[] USCensusArray = CensusAnalyser.getUSCensusArrayOfJson(sortedUSCensusData);
        Assert.assertEquals("Alabama", USCensusArray[0].getState());
    }

    @Test
    public void givenUSStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        List<USCensusDAO> usCensusDAOList = (List<USCensusDAO>) CensusAnalyser.loadData(MessageHelper.US_CENSUS_FILE_PATH,
                                            USCensusDAO.class, MessageHelper.US_CENSUS_FILE_NOT_FOUND_MESSAGE);
        List<USCensus> usCensusList = CensusAnalyser.getUSList(usCensusDAOList);
        List<USCensus> sortedUSCensusList = CensusAnalyser.getUSCensusSortedListBasedOnPopulation(usCensusList);
        String sortedUSCensusData = CensusAnalyser.getJsonFormatOfList(sortedUSCensusList);
        USCensus[] USCensusArray = CensusAnalyser.getUSCensusArrayOfJson(sortedUSCensusData);
        Assert.assertEquals(37253956, USCensusArray[0].getPopulation(),0);
    }

    @Test
    public void givenUSStateCensusData_WhenSortedOnDensity_ShouldReturnSortedResult() {
        List<USCensusDAO> usCensusDAOList = (List<USCensusDAO>) CensusAnalyser.loadData(MessageHelper.US_CENSUS_FILE_PATH,
                                            USCensusDAO.class, MessageHelper.US_CENSUS_FILE_NOT_FOUND_MESSAGE);
        List<USCensus> usCensusList = CensusAnalyser.getUSList(usCensusDAOList);
        List<USCensus> sortedUSCensusList = CensusAnalyser.getUSCensusSortedListBasedOnDensity(usCensusList);
        String sortedUSCensusData = CensusAnalyser.getJsonFormatOfList(sortedUSCensusList);
        USCensus[] USCensusArray = CensusAnalyser.getUSCensusArrayOfJson(sortedUSCensusData);
        Assert.assertEquals(3805.61, USCensusArray[0].getDensity(),0);
    }

    @Test
    public void givenUSStateCensusData_WhenSortedOnArea_ShouldReturnSortedResult() {
        List<USCensusDAO> usCensusDAOList = (List<USCensusDAO>) CensusAnalyser.loadData(MessageHelper.US_CENSUS_FILE_PATH,
                                            USCensusDAO.class, MessageHelper.US_CENSUS_FILE_NOT_FOUND_MESSAGE);
        List<USCensus> usCensusList = CensusAnalyser.getUSList(usCensusDAOList);
        List<USCensus> sortedUSCensusList = CensusAnalyser.getUSCensusSortedListBasedOnArea(usCensusList);
        String sortedUSCensusData = CensusAnalyser.getJsonFormatOfList(sortedUSCensusList);
        USCensus[] USCensusArray = CensusAnalyser.getUSCensusArrayOfJson(sortedUSCensusData);
        Assert.assertEquals(1723338.01, USCensusArray[0].getArea(),0);
    }

    @Test
    public void givenUSStateCensusData_WhenSortedOnDensity_ShouldReturnPopulousState() {
        List<USCensusDAO> usCensusDAOList = (List<USCensusDAO>) CensusAnalyser.loadData(MessageHelper.US_CENSUS_FILE_PATH,
                                            USCensusDAO.class, MessageHelper.US_CENSUS_FILE_NOT_FOUND_MESSAGE);
        List<USCensus> usCensusList = CensusAnalyser.getUSList(usCensusDAOList);
        List<USCensus> sortedUSCensusList = CensusAnalyser.getUSCensusSortedListBasedOnDensity(usCensusList);
        String sortedUSCensusData = CensusAnalyser.getJsonFormatOfList(sortedUSCensusList);
        USCensus[] USCensusArray = CensusAnalyser.getUSCensusArrayOfJson(sortedUSCensusData);
        Assert.assertEquals("District of Columbia", USCensusArray[0].getState());
    }

    @Test
    public void givenIndiaStateCensusData_WhenSortedOnDensity_ShouldReturnPopulousState() {
        Map<String, IndiaStateCensus> map = CensusAnalyser.getMap(MessageHelper.INDIA_STATES_CENSUS_FILE_PATH,
                MessageHelper.INDIA_STATES_CODE_FILE_PATH);
        List<IndiaStateCensus> indiaStateCensusList = CensusAnalyser.getListFromMap(map);
        List<IndiaStateCensus> sortedIndiaDensityCensusList = CensusAnalyser.getIndiaStateCensusSortedListBasedOnDensity(indiaStateCensusList);
        String sortedIndiaDensityCensusData = CensusAnalyser.getJsonFormatOfList(sortedIndiaDensityCensusList);
        IndiaStateCensus[] indiaStateCensusArray = CensusAnalyser.getArrayOfJson(sortedIndiaDensityCensusData);
        Assert.assertEquals("Bihar", indiaStateCensusArray[0].getState());
    }
}