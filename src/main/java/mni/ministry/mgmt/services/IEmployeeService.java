package mni.ministry.mgmt.services;

import mni.ministry.mgmt.models.Employee;

import java.util.List;

public interface IEmployeeService {
    Employee createNewEmployee(Employee employee);
    List<Employee> fetchAllEmployee();
}
