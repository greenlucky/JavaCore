package com.lamdevops.lambda.Employee;

import com.lamdevops.lambda.models.Idenfified;
import com.lamdevops.lambda.models.Person;

import java.lang.*;

/**
 * Created by lam.nm on 7/17/2017.
 */
public class Employee implements Person, Idenfified, java.lang.Comparable<Employee> {

    private double salary = 0.0;
    private String name;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return Idenfified.super.getId();
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee other) {
        return getId() - other.getId();
    }

    public int compareTo(Employee other, int direction) {
        return direction * (getId() - other.getId());
    }

    public int compareSalaryTo(Employee other) {
        return Double.compare(salary, other.salary);
    }

    public int compareSalaryTo(Employee other, int direction) {
        return direction * Double.compare(salary, other.salary);
    }


    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", name='" + name + '\'' +
                '}';
    }
}
