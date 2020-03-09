package com.bridgelabz.census;

import com.bridgelabz.census.dao.IndiaStateCensusDAO;
import com.bridgelabz.census.dao.IndiaStateCodeDAO;
import com.bridgelabz.census.dao.USCensusDAO;
import com.bridgelabz.census.exceptions.CensusAnalyserException;
import com.bridgelabz.census.models.IndiaStateCensus;
import com.bridgelabz.census.models.IndiaStateCode;
import com.bridgelabz.census.models.USCensus;
import com.bridgelabz.census.utility.MessageHelper;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.bridgelabz.census.CensusAnalyser.*;
import static org.junit.Assert.assertEquals;

public class CensusAnalyserTest {

    @Test
    public void givenIndiaStatesCensusFileName_WhenProper_ShouldReturnTotalCount() {
        Map<String, IndiaStateCensus> map = getMap(MessageHelper.INDIA_STATES_CENSUS_FILE_PATH,
                                                   MessageHelper.INDIA_STATES_CODE_FILE_PATH);
        assertEquals(29, map.size());
    }

    @Test
    public void givenIndiaStatesCensusFileName_WhenImproper_ShouldThrowException() {
        try {
            loadData("abc.csv", IndiaStateCensusDAO.class,
                    MessageHelper.INDIAN_STATES_CENSUS_FILE_NOT_FOUND_MESSAGE);
        } catch (CensusAnalyserException e) {
            assertEquals(MessageHelper.INDIAN_STATES_CENSUS_FILE_NOT_FOUND_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void givenIndiaStatesCodeFileName_WhenProper_ShouldReturnTotalCount() {
        List<IndiaStateCodeDAO> indiaStateCodeDAOList = (List<IndiaStateCodeDAO>) loadData(MessageHelper.INDIA_STATES_CODE_FILE_PATH,
                                                        IndiaStateCodeDAO.class, MessageHelper.INDIAN_STATES_CODE_FILE_NOT_FOUND_MESSAGE);
        assertEquals(37, indiaStateCodeDAOList.size());
    }

    @Test
    public void givenIndiaStatesCodeFileName_WhenImproper_ShouldThrowException() {
        try {
            loadData("abc.csv", IndiaStateCodeDAO.class,
                    MessageHelper.INDIAN_STATES_CODE_FILE_NOT_FOUND_MESSAGE);
        } catch (CensusAnalyserException e) {
            assertEquals(MessageHelper.INDIAN_STATES_CODE_FILE_NOT_FOUND_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void givenIndiaStatesCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
        List<IndiaStateCensus> indiaStateCensusList = getIndiaStateCensusData();
        List<IndiaStateCensus> sortedIndiaStateCensusList = getIndiaStateCensusSortedList(indiaStateCensusList);
        IndiaStateCensus[] indiaStateCensusArray = getIndiaStateCensusArray(sortedIndiaStateCensusList);
        assertEquals("Andhra Pradesh", indiaStateCensusArray[0].getState());
    }

    @Test
    public void givenIndiaStatesCodeData_WhenSortedOnStateCode_ShouldReturnSortedResult() {
        List<IndiaStateCode> indiaStateCodeList = (List<IndiaStateCode>) loadData(MessageHelper.INDIA_STATES_CODE_FILE_PATH,
                                                  IndiaStateCode.class, MessageHelper.INDIAN_STATES_CODE_FILE_NOT_FOUND_MESSAGE);
        List<IndiaStateCode> sortedIndiaStateCodeList = getIndiaStateCodeSortedList(indiaStateCodeList);
        String sortedIndiaStateCodeData = getJsonFormatOfList(sortedIndiaStateCodeList);
        IndiaStateCode[] indiaStateCodeArray = getIndiaStateCodeArrayOfJson(sortedIndiaStateCodeData);
        assertEquals("AD", indiaStateCodeArray[0].getStateCode());
    }

    @Test
    public void givenIndiaStatesCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        List<IndiaStateCensus> indiaStateCensusList = getIndiaStateCensusData();
        List<IndiaStateCensus> sortedIndiaPopulationCensusList = getIndiaStateCensusSortedListBasedOnPopulation(indiaStateCensusList);
        IndiaStateCensus[] indiaStateCensusArray = getIndiaStateCensusArray(sortedIndiaPopulationCensusList);
        assertEquals(199812341, indiaStateCensusArray[0].getPopulation(), 0);
    }

    @Test
    public void givenIndiaStatesCensusData_WhenSortedOnDensity_ShouldReturnSortedResult() {
        List<IndiaStateCensus> indiaStateCensusList = getIndiaStateCensusData();
        List<IndiaStateCensus> sortedIndiaDensityCensusList = getIndiaStateCensusSortedListBasedOnDensity(indiaStateCensusList);
        IndiaStateCensus[] indiaStateCensusArray = getIndiaStateCensusArray(sortedIndiaDensityCensusList);
        assertEquals(1102, indiaStateCensusArray[0].getDensity(), 0);
    }

    @Test
    public void givenIndiaStatesCensusData_WhenSortedOnArea_ShouldReturnSortedResult() {
        List<IndiaStateCensus> indiaStateCensusList = getIndiaStateCensusData();
        List<IndiaStateCensus> sortedIndiaAreaCensusList = getIndiaStateCensusSortedListBasedOnArea(indiaStateCensusList);
        IndiaStateCensus[] indiaStateCensusArray = getIndiaStateCensusArray(sortedIndiaAreaCensusList);;
        assertEquals(342239, indiaStateCensusArray[0].getArea(), 0);
    }

    @Test
    public void givenUSStateCensusFileName_WhenProper_ShouldReturnTotalCount() {
        List<USCensusDAO> usCensusDAOList = (List<USCensusDAO>) loadData(MessageHelper.US_CENSUS_FILE_PATH,
                                            USCensusDAO.class, MessageHelper.US_CENSUS_FILE_NOT_FOUND_MESSAGE);
        assertEquals(51, usCensusDAOList.size());
    }

    @Test
    public void givenUSStateCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
        List<USCensus> usCensusList = getUSStateCensusData();
        List<USCensus> sortedUSCensusList = getUSCensusSortedListBasedOnState(usCensusList);
        USCensus[] USCensusArray = getUSStateCensusArray(sortedUSCensusList);
        assertEquals("Alabama", USCensusArray[0].getState());
    }

    @Test
    public void givenUSStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        List<USCensus> usCensusList = getUSStateCensusData();
        List<USCensus> sortedUSCensusList = getUSCensusSortedListBasedOnPopulation(usCensusList);
        USCensus[] USCensusArray = getUSStateCensusArray(sortedUSCensusList);
        assertEquals(37253956, USCensusArray[0].getPopulation(),0);
    }

    @Test
    public void givenUSStateCensusData_WhenSortedOnDensity_ShouldReturnSortedResult() {
        List<USCensus> usCensusList = getUSStateCensusData();
        List<USCensus> sortedUSCensusList = getUSCensusSortedListBasedOnDensity(usCensusList);
        USCensus[] USCensusArray = getUSStateCensusArray(sortedUSCensusList);
        assertEquals(3805.61, USCensusArray[0].getDensity(),0);
    }

    @Test
    public void givenUSStateCensusData_WhenSortedOnArea_ShouldReturnSortedResult() {
        List<USCensus> usCensusList = getUSStateCensusData();
        List<USCensus> sortedUSCensusList = getUSCensusSortedListBasedOnArea(usCensusList);
        USCensus[] USCensusArray = getUSStateCensusArray(sortedUSCensusList);
        assertEquals(1723338.01, USCensusArray[0].getArea(),0);
    }

    @Test
    public void givenUSStateCensusData_WhenSortedOnDensity_ShouldReturnPopulousState() {
        List<USCensus> usCensusList = getUSStateCensusData();
        List<USCensus> sortedUSCensusList = getUSCensusSortedListBasedOnDensity(usCensusList);
        USCensus[] USCensusArray = getUSStateCensusArray(sortedUSCensusList);
        assertEquals("District of Columbia", USCensusArray[0].getState());
    }

    @Test
    public void givenIndiaStateCensusData_WhenSortedOnDensity_ShouldReturnPopulousState() {
        List<IndiaStateCensus> indiaStateCensusList = getIndiaStateCensusData();
        List<IndiaStateCensus> sortedIndiaDensityCensusList = getIndiaStateCensusSortedListBasedOnDensity(indiaStateCensusList);
        IndiaStateCensus[] indiaStateCensusArray = getIndiaStateCensusArray(sortedIndiaDensityCensusList);
        assertEquals("Bihar", indiaStateCensusArray[0].getState());
    }
}