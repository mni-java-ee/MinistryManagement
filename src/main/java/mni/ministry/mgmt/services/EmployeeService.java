package mni.ministry.mgmt.services;

import mni.ministry.mgmt.dao.EmployeeDAO;
import mni.ministry.mgmt.dto.CreateNewEmpDto;
import mni.ministry.mgmt.models.Employee;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmployeeService implements IEmployeeService {
    List<Employee> employees = new ArrayList<>();

    @Inject
    EmployeeDAO employeeDAO;

    @PostConstruct
    public void init() throws SQLException {
        //employeeDAO.insertNewEmployee(new CreateNewEmpDto("Bima Olga", "57594587549", "bimaol@gmail.com", 23));
        //employees.add(new Employee(new BigInteger("1"), "Bima Olga", "57594587549", "bimaol@gmail.com", 23));
    }

    @Override
    public CreateNewEmpDto createNewEmployee(CreateNewEmpDto employee) throws SQLException {
        return employeeDAO.insertNewEmployee(employee);
    }

    @Override
    public List<Employee> fetchAllEmployee() {
        employees.add(new Employee(new BigInteger("2"), "Syaffiq Ammar", "57594587511", "syafiqammar@gmail.com", 23));
        employees.add(new Employee(new BigInteger("3"), "Tikto", "57594587549", "tikto@gmail.com", 23));
        return employees;
    }
}
