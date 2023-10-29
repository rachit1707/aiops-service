package com.vois.poc.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface UpdateWorkgroupService {
    List<String[]> updateWorkGroup(List<String[]> csvData) throws IOException;
    void updateAuditServiceWithLatestData(List<String[]> csvData) throws URISyntaxException, IOException;
}
