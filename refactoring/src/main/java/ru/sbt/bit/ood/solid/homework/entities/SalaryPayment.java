package ru.sbt.bit.ood.solid.homework.entities;

import java.math.BigDecimal;

/**
 * Created by artem
 */
public class SalaryPayment {

    private final Long employeeId;
    private final String employeeName;
    private final double salary;

    public SalaryPayment(Long employeeId, String employeeName, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public double getSalary() {
        return salary;
    }

}
