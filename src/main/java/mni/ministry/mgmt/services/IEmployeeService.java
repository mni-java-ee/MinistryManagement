package mni.ministry.mgmt.services;

import mni.ministry.mgmt.models.Employee;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeService {
    Employee createNewEmployee(Employee employee) throws SQLException;
    List<Employee> fetchAllEmployee();
}
