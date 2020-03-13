package com.scottrbrtsn.wordcount.services.impl;

import com.scottrbrtsn.wordcount.ras.ILogsRepository;
import com.scottrbrtsn.wordcount.services.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService implements ILogService {

    @Autowired
    ILogsRepository logsRepository;

    @Override
    public String testMe(){
        return "passed";
    }

}