package model;

import java.util.ArrayList;

public class Employees {
    private ArrayList<Employee> employees;

    public Employees() {
        this.employees = new ArrayList<>();
    }

    public Employees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    /* check if an employee exists in the employees list */
    public boolean exists(Employee employee) {
        for (Employee e : this.getEmployees()
        ) {
            if (e.equals(employee) == true) return true;
        }
        return false;
    }

    public void addEmployee(Employee employee) {
        if (!this.exists(employee))
            this.employees.add(employee);
    }


    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }


}
