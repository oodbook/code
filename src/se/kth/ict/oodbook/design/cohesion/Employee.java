package se.kth.ict.oodbook.design.cohesion;

/**
 * Represents an employee.
 */
public class Employee {

    private String name;
    private Address address;
    private Amount salary;

    /**
     * Changes the salary to <code>newSalary</code>.
     *
     * @param newSalary The new salary.
     */
    public void changeSalary(Amount newSalary) {
        this.salary = newSalary;
    }
}
