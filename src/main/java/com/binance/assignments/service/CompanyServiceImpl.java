package com.binance.assignments.service;

import com.binance.assignments.models.Company;
import com.binance.assignments.models.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingLong;

@Service

public class CompanyServiceImpl implements CompanyService {
    private static final Logger logger = LogManager.getLogger(CompanyServiceImpl.class);
    private List<Company> companyInvoices;

    @Override
    public List<Company> processInvoice(List<Employee> employees) {
        companyInvoices = new ArrayList<>();
        Map<String, List<Employee>> byProjects = employees.stream().collect(Collectors.groupingBy(Employee::getProject));
        for (Map.Entry<String, List<Employee>> byProject : byProjects.entrySet()) {
            Company company = new Company();
            logger.info("Name of project is " + byProject.getKey());
            company.setCompanyName(byProject.getKey());
            company.setEmployees(sortAndGroupEmployees(byProject.getValue()));
            company.setTotal(calculateTotalCost(company.getEmployees()));
            companyInvoices.add(company);
            logger.info("size of company invoice is " + companyInvoices.size());
        }
        return companyInvoices;
    }


    private List<Employee> sortAndGroupEmployees(List<Employee> employees) {

        Map<Integer, Long> collect = employees.stream().collect(Collectors.groupingBy(Employee::getEmployeeId, summingLong(Employee::getNumberOfHours)));
        List<Employee> newEmployee = new ArrayList<>();
        for (Employee e : employees) {
            long totalHours = collect.get(e.getEmployeeId());
            e.setNumberOfHours(totalHours);
            //check if id has been added before
            if (!employeeAddedPreviously(newEmployee, e)) {
                newEmployee.add(e);
            }

        }
        return newEmployee;
    }

    private boolean employeeAddedPreviously(List<Employee> employees, Employee employee) {
        List<Employee> collect = employees.stream()
                .filter(emp -> employee.getEmployeeId() == (emp.getEmployeeId()))
                .collect(Collectors.toList());
        return !collect.isEmpty();
    }

    private double calculateTotalCost(List<Employee> employees){

        double total = 0;
        for(Employee e: employees){
            total += e.getNumberOfHours() * e.getUnitPrice();
        }
        return total;
    }
}
