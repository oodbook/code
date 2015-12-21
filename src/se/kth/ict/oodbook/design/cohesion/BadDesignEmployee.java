package se.kth.ict.oodbook.design.cohesion;

import java.util.List;

/**
 * Represents an employee.
 */
public class BadDesignEmployee {

    private String name;
    private Address address;
    private Amount salary;

    /**
     * Changes the salary of <code>employee</code> to <code>newSalary</code>.
     *
     * @param employee The <code>Employee</code> whose salary will be changed.
     * @param newSalary The new salary of <code>employee</code>.
     */
    public void changeSalary(BadDesignEmployee employee, Amount newSalary) {

    }

    /**
     * Returns a list with all employees working in the same department as this employee.
     */
    public List<BadDesignEmployee> getAllEmployees() {
        return null;
    }

}
