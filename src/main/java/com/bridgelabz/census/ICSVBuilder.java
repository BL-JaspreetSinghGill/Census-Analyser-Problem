package com.bridgelabz.census;

public interface ICSVBuilder {

    <T> int loadData(String FILE_NAME, Class<T> c, String message);
}
