package com.bridgelabz.census;

public class CSVBuilderFactory {

    public static ICSVBuilder createCSVBuilder() {
        return new CSVBuilder();
    }
}
