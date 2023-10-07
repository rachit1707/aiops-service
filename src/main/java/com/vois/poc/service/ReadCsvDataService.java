package com.vois.poc.service;

import java.net.URISyntaxException;
import java.util.List;

public interface ReadCsvDataService {

    List<String[]> readCsvDataToArray(String path) throws Exception;

}
