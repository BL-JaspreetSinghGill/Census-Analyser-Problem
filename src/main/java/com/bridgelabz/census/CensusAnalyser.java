package com.bridgelabz.census;

import com.bridgelabz.census.exceptions.CensusAnalyserException;
import com.bridgelabz.census.models.IndiaStateCensus;
import com.bridgelabz.census.models.IndiaStateCode;
import com.bridgelabz.census.utility.MessageHelper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    public static int loadIndiaStatesCensusData(String INDIA_STATES_CENSUS_FILE_PATH) {
        int count = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(INDIA_STATES_CENSUS_FILE_PATH))) {
            CsvToBean<IndiaStateCensus> csvToBean = new CsvToBeanBuilder<IndiaStateCensus>(reader)
                                                    .withType(IndiaStateCensus.class)
                                                    .withIgnoreLeadingWhiteSpace(true)
                                                    .build();

            Iterator<IndiaStateCensus> csvIndiaCensusIterator = csvToBean.iterator();
            Iterable<IndiaStateCensus> indiaCensusIterable = () -> csvIndiaCensusIterator;
            count = (int) StreamSupport.stream(indiaCensusIterable.spliterator(), false).count();
        } catch (IOException e) {
            throw new CensusAnalyserException(MessageHelper.INDIAN_STATES_CENSUS_FILE_NOT_FOUND_MESSAGE);
        }
        return count;
    }

    public static int loadIndiaStatesCodeData(String INDIA_STATES_CODE_FILE_PATH) {
        int count = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(INDIA_STATES_CODE_FILE_PATH))) {
            CsvToBean<IndiaStateCode> csvToBean = new CsvToBeanBuilder<IndiaStateCode>(reader)
                                                  .withType(IndiaStateCode.class)
                                                  .withIgnoreLeadingWhiteSpace(true)
                                                  .build();

            Iterator<IndiaStateCode> csvIndiaCensusIterator = csvToBean.iterator();
            Iterable<IndiaStateCode> indiaCensusIterable = () -> csvIndiaCensusIterator;
            count = (int) StreamSupport.stream(indiaCensusIterable.spliterator(), false).count();
        } catch (IOException e) {
            throw new CensusAnalyserException(MessageHelper.INDIAN_STATES_CODE_FILE_NOT_FOUND_MESSAGE);
        }
        return count;
    }
}