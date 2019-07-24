package com.binance.assignments.service;

import com.binance.assignments.models.Company;
import com.binance.assignments.models.Timetable;
import com.binance.assignments.utils.MapCsvDataToModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProcessInvoiceImpl implements ProcessInvoice {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CompanyService companyService;

    @Override
    public List<Timetable> readTimeTables(MultipartFile csvFile) throws Exception {
        if (csvFile == null) throw new Exception("File is null");
        if (csvFile.isEmpty()) throw new Exception("File is empty");
        try {
            List<Timetable> read = MapCsvDataToModelUtil.read(Timetable.class, csvFile.getInputStream());
            return read;
        } catch (Exception ex) {
            throw new Exception("Something went wrong with the uploaded file. Please review and retry");
        }
    }

    @Override
    public List<Company> getCompanyInvoices(List<Timetable> timetables) throws Exception {
        if (timetables.size() == 0) throw new Exception("no record found in the uploaded file");
        return companyService.processInvoice( employeeService.createEmployeeRecord(timetables));

    }
}
