package mni.ministry.mgmt.services;

import mni.ministry.mgmt.dto.CreateNewEmpDto;
import mni.ministry.mgmt.dto.EmployeeListDto;
import mni.ministry.mgmt.models.Employee;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeService {
    CreateNewEmpDto createNewEmployee(CreateNewEmpDto employee) throws SQLException;
    EmployeeListDto fetchAllEmployee(int pageNo, int pageSize) throws SQLException;
}
