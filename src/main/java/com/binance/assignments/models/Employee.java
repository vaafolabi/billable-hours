package com.binance.assignments.models;

public class Employee {
    private int employeeId;
    private long numberOfHours;
    private double unitPrice;



    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    private String project;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public long getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(long numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
