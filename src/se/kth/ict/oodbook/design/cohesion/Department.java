package se.kth.ict.oodbook.design.cohesion;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a department.
 */
public class Department {

    private String name;
    private List<Employee> employees = new ArrayList<>();

    /**
     * Returns a list with all employees working in this department.
     */
    public List<Employee> getEmployees() {
        return employees;
    }

}
