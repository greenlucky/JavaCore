package com.lamdevops.lambda.Employee;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by lam.nm on 7/17/2017.
 */
class EmployeeTest {

    private static List<Employee> employees;

    @BeforeAll
    public static void init() throws Exception {
        Employee emp1 = new Employee("John", 3450);
        Employee emp2 = new Employee("Marry", 7340);
        Employee emp3 = new Employee("Steel", 3450);
        Employee emp4 = new Employee("Harry", 1850);
        Employee emp5 = new Employee("Stack", 1850);

        employees = Arrays.asList(emp1, emp2, emp3, emp4, emp5);

        assert employees.size() == 5;

    }

    @Test
    void compareTo() {
        Employee emp1 = new Employee("John", 2020);
        Employee emp2 = new Employee("Marry", 7340);
        assertEquals(emp1.compareTo(emp2), emp1.getId() - emp2.getId());

    }

    @Test
    void compareSalaryTo1() {
        Employee emp1 = new Employee("John", 2020);
        Employee emp2 = new Employee("Marry", 7340);
        assertEquals(emp1.compareSalaryTo(emp2), -1);
    }

    @Test
    void compareSalaryTo2() {
        Employee emp2 = new Employee("Marry", 7340);
        Employee emp3 = new Employee("Steel", 3450);
        assertEquals(emp2.compareSalaryTo(emp3), 1);
    }

    @Test
    void sortEmployeeBYSalary() throws Exception {
        employees.sort((x, y) -> x.compareSalaryTo(y));

        employees.forEach(System.out::println);
    }

    @Test
    void sortEmployeeBYSalaryIndirection() throws Exception {
        employees.sort((x, y) -> x.compareSalaryTo(y, -1));

        employees.forEach(System.out::println);
    }

    @Test
    void sortEmployeeBYSalaryManual() throws Exception {
        employees.sort((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
        employees.forEach(System.out::println);
    }

    @Test
    void multiSort() throws Exception {
        employees.sort(Comparator.comparing(Employee::getSalary).thenComparing(Employee::compareSalaryTo).reversed());
        employees.forEach(System.out::println);
    }

    @Test
    public void nuturalSort() throws Exception {
        employees.sort(Comparator.comparing(Employee::getSalary).thenComparing(Comparator.naturalOrder()));
        employees.forEach(System.out::println);
    }

}