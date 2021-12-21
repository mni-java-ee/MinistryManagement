package mni.ministry.mgmt.controllers;

import mni.ministry.mgmt.dto.CreateNewEmpDto;
import mni.ministry.mgmt.models.Employee;
import mni.ministry.mgmt.services.EmployeeService;

import javax.inject.Inject;
import javax.jws.WebMethod;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.List;

@WebService(serviceName = "employeeService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EmployeeController {

    @Inject
    private EmployeeService employeeService;

    @WebMethod
    public CreateNewEmpDto createNewEmployee(CreateNewEmpDto newEmployee) throws SQLException {
        return employeeService.createNewEmployee(newEmployee);
    }

    @WebMethod
    public List<Employee> getAllEmployee(@WebParam(name="pageNo") int pageNo, @WebParam(name="pageSize") int pageSize) throws SQLException {
        return employeeService.fetchAllEmployee(pageNo, pageSize).getEmployees();
    }

    @WebMethod
    public Employee getEmployeeById(@WebParam(name="empId") String empId) throws SQLException {
        return employeeService.fetchEmployeeById(empId);
    }

    @WebMethod
    public int deleteEmployeeById(@WebParam(name="empId") String empId) throws SQLException {
        return employeeService.deleteEmployeeById(empId);
    }
}
