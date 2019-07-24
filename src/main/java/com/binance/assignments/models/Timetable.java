package com.binance.assignments.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Timetable {
    public static final String EmployeeIdField = "Employee ID";
    public static final String BillableRateField = "Billable Rate (per hour)";
    public static final String ProjectField = "Project";
    public static final String DateField = "Date";
    public static final String StartTimeField = "Start Time";
    public static final String EndTimeField = "End Time";

    @JsonProperty(EmployeeIdField)
    private int employeeId;
    @JsonProperty(BillableRateField)
    private double employeeRate;
    @JsonProperty(ProjectField)
    private String project;
    @JsonProperty(DateField)
    private String date;
    @JsonProperty(StartTimeField)
    private String startTime;
    @JsonProperty(EndTimeField)
    private String endTime;



    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getEmployeeRate() {
        return employeeRate;
    }

    public void setEmployeeRate(double employeeRate) {
        this.employeeRate = employeeRate;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
