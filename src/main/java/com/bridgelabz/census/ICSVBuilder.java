package com.bridgelabz.census;

import java.util.List;

public interface ICSVBuilder {

    <T> List<T> loadCSVData(String FILE_NAME, Class<T> c, String message);
}
