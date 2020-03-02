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
        return loadData(INDIA_STATES_CENSUS_FILE_PATH, IndiaStateCensus.class,
                 MessageHelper.INDIAN_STATES_CENSUS_FILE_NOT_FOUND_MESSAGE);
    }

    public static int loadIndiaStatesCodeData(String INDIA_STATES_CODE_FILE_PATH) {
        return loadData(INDIA_STATES_CODE_FILE_PATH, IndiaStateCode.class,
                MessageHelper.INDIAN_STATES_CODE_FILE_NOT_FOUND_MESSAGE);
    }

    private static <T> int loadData(String FILE_NAME, Class<T> c, String message) {
        int count = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_NAME))) {
            Iterator<T> csvIterator = getIterator(reader, c);
            count = getCount(csvIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(message);
        }
        return count;
    }

    private static <T> Iterator<T> getIterator(Reader reader, Class<T> c) {
        CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                                .withType(c)
                                .withIgnoreLeadingWhiteSpace(true)
                                .build();
        return csvToBean.iterator();
    }

    private static <T> int getCount(Iterator<T> iterator) {
        Iterable<T> indiaCensusIterable = () -> iterator;
        return (int) StreamSupport.stream(indiaCensusIterable.spliterator(), false)
                                  .count();
    }
}