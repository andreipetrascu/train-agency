package model;

public class Employee extends Person {

    private String role;
    private String username;
    private String password;


    public Employee() {
    }

    public Employee(String name, String email, String role, String username, String password) {
        super(name, email);
        this.role = role;
        this.username = username;
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Employee)) {
            return false;
        }

        Employee e = (Employee) o;

        // Compare the data members and return accordingly
        return this.getName().equals(e.getName()) && this.getEmail().equals(e.getEmail()) &&
                this.getRole().equals(e.getRole()) && this.getUsername().equals(e.getUsername())
                && this.getPassword().equals(e.getPassword());
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
