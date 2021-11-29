package com.scoring.services;

import com.scoring.exceptions.ReportPrinterException;

import java.util.List;
import java.util.Map;

public interface IReportPrinterService {


    enum TypeFile{
        PDF,
        XLSX
    }

//    public String print(String jasperFile, String filename, TypeFile type, Map params) throws ReportPrinterException;
    String print(String jasperFile, String filename, TypeFile type, Map params, List data) throws ReportPrinterException;
}
