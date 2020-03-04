package com.bridgelabz.census;

import java.io.Reader;
import java.util.List;

public interface ICSVBuilder {

    <T> int loadData(String FILE_NAME, Class<T> c, String message);

    <T> List<T> getList(Reader reader, Class<T> c);

    <T> List<T> loadCSVData(String FILE_NAME, Class<T> c, String message);
}
