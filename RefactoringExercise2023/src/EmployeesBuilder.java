
public class EmployeesBuilder {
    private int employeeId;
    private String ppsNumber;
    private String employeeSurname;
    private String employeeFirstName;
    private char employeeGender;
    private String employeeDepartment;
    private double employeeSalary;
    private boolean isFullTime;

    public EmployeesBuilder setId(int id) {
        this.employeeId = id;
        return this;
    }

    public EmployeesBuilder setPps(String pps) {
        this.ppsNumber = pps;
        return this;
    }

    public EmployeesBuilder setSurname(String surname) {
        this.employeeSurname = surname;
        return this;
    }

    public EmployeesBuilder setFirstName(String firstName) {
        this.employeeFirstName = firstName;
        return this;
    }

    public EmployeesBuilder setGender(char gender) {
        this.employeeGender = gender;
        return this;
    }

    public EmployeesBuilder setDepartment(String department) {
        this.employeeDepartment = department;
        return this;
    }

    public EmployeesBuilder setSalary(double salary) {
        this.employeeSalary = salary;
        return this;
    }

    public EmployeesBuilder setFullTime(boolean fullTime) {
        this.isFullTime = fullTime;
        return this;
    }

    public Employee build() throws IllegalStateException {
        if (employeeId <= 0) {
            throw new IllegalStateException("Employee ID must be a positive integer");
        }

        if (ppsNumber == null || ppsNumber.isEmpty()) {
            throw new IllegalStateException("PPS number must not be empty");
        }

        if (employeeSurname == null || employeeSurname.isEmpty()) {
            throw new IllegalStateException("Surname must not be empty");
        }

        if (employeeFirstName == null || employeeFirstName.isEmpty()) {
            throw new IllegalStateException("First name must not be empty");
        }

        if (employeeGender != 'M' && employeeGender != 'F') {
            throw new IllegalStateException("Gender must be 'M' or 'F'");
        }

        if (employeeDepartment == null || employeeDepartment.isEmpty()) {
            throw new IllegalStateException("Department must not be empty");
        }

        if (employeeSalary <= 0) {
            throw new IllegalStateException("Salary must be a positive number");
        }

        return new Employee(employeeId, ppsNumber, employeeSurname, employeeFirstName, employeeGender, employeeDepartment, employeeSalary, isFullTime);
    }
}

