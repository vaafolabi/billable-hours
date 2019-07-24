package com.binance.assignments.service;

import com.binance.assignments.models.Company;
import com.binance.assignments.models.Timetable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProcessInvoice {

    List<Timetable> readTimeTables(MultipartFile csvFile) throws  Exception;
    List<Company> getCompanyInvoices(List<Timetable> timetables)throws  Exception;
}
