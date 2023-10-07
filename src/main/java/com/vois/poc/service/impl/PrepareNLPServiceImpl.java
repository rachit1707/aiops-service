package com.vois.poc.service.impl;

import com.vois.poc.service.PrepareNLPService;
import com.vois.poc.util.GenerateModelUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PrepareNLPServiceImpl implements PrepareNLPService {
    @Override
    public void generateModel() throws IOException {
        GenerateModelUtil.generateModel();
    }
}
