package com.binance.assignments.service;


import com.binance.assignments.models.Employee;
import com.binance.assignments.models.Timetable;

import java.util.List;


public interface EmployeeService {
    List<Employee> createEmployeeRecord(List<Timetable> timetable) throws Exception;


}
