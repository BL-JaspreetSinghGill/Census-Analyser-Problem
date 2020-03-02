package com.bridgelabz.census;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {

    <T> int loadData(String FILE_NAME, Class<T> c, String message);

    <T> Iterator<T> getIterator(Reader reader, Class<T> c);

    <T> int getCount(Iterator<T> iterator);
}
