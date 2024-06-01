import java.util.Objects;

public class Employee {
    private String email;
    private String firstName;
    private String lastName;
    private Money salary;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Money getSalary() {
        return salary;
    }

    public void setSalary(Money salary) {
        this.salary = salary;
    }

    public Employee (String email, String firstName, String lastName, Money salary){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(email, employee.email) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(salary, employee.salary);
    }


    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, salary);
    }
}
