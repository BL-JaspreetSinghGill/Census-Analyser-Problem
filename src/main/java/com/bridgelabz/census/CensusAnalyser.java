package com.bridgelabz.census;

import com.bridgelabz.census.exceptions.CensusAnalyserException;
import com.bridgelabz.census.models.IndiaCensus;
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
            CsvToBean<IndiaCensus> csvToBean = new CsvToBeanBuilder<IndiaCensus>(reader)
                                                .withType(IndiaCensus.class)
                                                .withIgnoreLeadingWhiteSpace(true)
                                                .build();

            Iterator<IndiaCensus> csvIndiaCensusIterator = csvToBean.iterator();
            Iterable<IndiaCensus> indiaCensusIterable = () -> csvIndiaCensusIterator;
            count = (int) StreamSupport.stream(indiaCensusIterable.spliterator(), false).count();
        } catch (IOException e) {
            throw new CensusAnalyserException(MessageHelper.INDIAN_STATES_CENSUS_FILE_NOT_FOUND_MESSAGE);
        }
        return count;
    }
}