package com.bridgelabz.census;

import com.bridgelabz.census.exceptions.CSVBuilderException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVBuilder implements ICSVBuilder {

    @Override
    public <T> List<T> loadCSVData(String FILE_NAME, Class<T> c, String message) {
        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_NAME))) {
            return getList(reader, c);
        } catch (IOException e) {
            throw new CSVBuilderException(message);
        }
    }

    private <T> List<T> getList(Reader reader, Class<T> c) {
        CsvToBean<T> csvToBean = getCSVBean(reader, c);
        return csvToBean.parse();
    }

    private <T> CsvToBean<T> getCSVBean(Reader reader, Class<T> c) {
        return new CsvToBeanBuilder<T>(reader)
                .withType(c)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
    }
}
