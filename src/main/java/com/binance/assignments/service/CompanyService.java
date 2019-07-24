package com.binance.assignments.service;

import com.binance.assignments.models.Company;
import com.binance.assignments.models.Employee;

import java.util.List;

public interface CompanyService {

    List<Company> processInvoice(List<Employee> employees);

}
