package mni.ministry.mgmt.services;

import mni.ministry.mgmt.controllers.EmployeeController;
import mni.ministry.mgmt.dao.EmployeeDAO;
import mni.ministry.mgmt.dto.CreateNewEmpDto;
import mni.ministry.mgmt.dto.EmployeeListDto;
import mni.ministry.mgmt.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmployeeService implements IEmployeeService {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
    List<Employee> employees;

    @Inject
    EmployeeDAO employeeDAO;

    private ResultSet rs;

    @PostConstruct
    public void init() {
        //employeeDAO.insertNewEmployee(new CreateNewEmpDto("Bima Olga", "57594587549", "bimaol@gmail.com", 23));
        //employees.add(new Employee(new BigInteger("1"), "Bima Olga", "57594587549", "bimaol@gmail.com", 23));
        employees = new ArrayList<>();
    }

    @Override
    public CreateNewEmpDto createNewEmployee(CreateNewEmpDto employee) throws SQLException {
        return employeeDAO.insertNewEmployee(employee);
    }

    @Override
    public EmployeeListDto fetchAllEmployee(int pageNo, int pageSize) throws SQLException {
        rs = employeeDAO.fetchEmployee(pageNo, pageSize);
        EmployeeListDto employeeList = new EmployeeListDto();
        employees.clear();

        //LOG.info("rs.next ==> "+rs.next());
        while(rs.next()){
            LOG.info(rs.getInt("id")+":"+rs.getString("empName")+":"+rs.getString("empId"));
            employees.add(new Employee(BigInteger.valueOf(rs.getInt("id")), rs.getString("empName"),
                    rs.getString("empId"), rs.getString("empEmail"), rs.getInt("age")));
        }
        employeeList.setEmployees(employees);

        return employeeList;
    }

    @Override
    public Employee fetchEmployeeById(String empId) throws SQLException {
        rs = employeeDAO.fetchEmployeeById(empId);
        if(rs.next()){
            return new Employee(BigInteger.valueOf(rs.getInt("id")), rs.getString("empName"),
                    rs.getString("empId"), rs.getString("empEmail"), rs.getInt("age"));
        }
        return null;
    }

    @Override
    public int deleteEmployeeById(String empId) throws SQLException {
        return employeeDAO.deleteEmployeeById(empId);
    }


}
