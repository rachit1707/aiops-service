package com.vois.poc.service.impl;

import com.opencsv.CSVReader;
import com.vois.poc.service.ReadCsvDataService;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ReadCsvDataServiceImpl implements ReadCsvDataService {
    @Override
    public List<String[]> readCsvDataToArray(String path) throws Exception {
        Path csvPath = Paths.get(
                ResourceUtils.getFile(path).toURI());
        return readAllLines(csvPath);
        //logic to add only those lines that are in the time range
    }

    private List<String[]> readAllLines(Path filePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }
}
