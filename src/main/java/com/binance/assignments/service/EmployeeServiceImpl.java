package com.binance.assignments.service;

import com.binance.assignments.models.Employee;
import com.binance.assignments.models.Timetable;
import com.binance.assignments.utils.DateAndTimeDifferenceUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    private List<Employee> employees;

    @Override
    public  List<Employee> createEmployeeRecord(List<Timetable> table) throws Exception {
        int count = 2;
        employees= new ArrayList<>();
        for (Timetable timetable : table) {
            if (timetable.getProject() == null || timetable.getProject() == "")
                throw new Exception("project name is empty at row " + count);
            if (timetable.getEmployeeId() <= 0) throw new Exception("Invalid employee Id entered at row " + count);
            if (timetable.getEmployeeRate() <= 0)
                throw new Exception("Employee Billable rate is invalid  at row " + count);
            try {
                Employee employee = createEmployee(timetable);
                addToEmployee(employee);
            } catch (Exception ex) {
                throw new Exception(ex.getMessage() + " at row " + count);
            }
            count++;
        }
        return employees;
    }



    private void addToEmployee(Employee employee) {

        List<Employee> collect = employees.stream()
                .filter(emp -> employee.getEmployeeId() == (emp.getEmployeeId()) &&
                        employee.getNumberOfHours() == emp.getNumberOfHours() &&
                        employee.getProject().equals(emp.getNumberOfHours()) &&
                        employee.getUnitPrice() == emp.getUnitPrice())
                .collect(Collectors.toList());

        logger.info("size of collect is " + collect.size());
        if (collect.size() == 0) {
             employees.add(employee);

        }
    }

    private Employee createEmployee(Timetable table) throws Exception {
        Employee emp = new Employee();
        emp.setEmployeeId(table.getEmployeeId());
        emp.setProject(table.getProject());
        emp.setUnitPrice(table.getEmployeeRate());
        emp.setNumberOfHours(DateAndTimeDifferenceUtil.getDateDifferenceInHours(table.getDate(), table.getStartTime(), table.getEndTime()));
        return emp;
    }


}
