package com.bridgelabz.census;

import com.bridgelabz.census.exceptions.CensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CSVBuilder implements ICSVBuilder {

    @Override
    public <T> int loadData(String FILE_NAME, Class<T> c, String message) {
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
