package mni.ministry.mgmt.dao;

import mni.ministry.mgmt.db.DBConnection;
import mni.ministry.mgmt.dto.CreateNewEmpDto;
import mni.ministry.mgmt.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class EmployeeDAO {
    private String sql = "";
    private PreparedStatement ps = null;
    private Connection connection;

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeDAO.class);

    @PostConstruct
    public void init(){
        connection = DBConnection.getDbConnection();
    }

    public CreateNewEmpDto insertNewEmployee(CreateNewEmpDto employee) throws SQLException {
        sql = "insert into employee(empName, empId, empEmail, age) values(?,?,?,?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1, employee.getEmpName());
        ps.setString(2, employee.getEmpId());
        ps.setString(3, employee.getEmpEmail());
        ps.setInt(4, employee.getAge());
        if(ps.executeUpdate() > 0){
            return employee;
        }

        return null;
    }

    public ResultSet fetchEmployee(int pageNo, int pageSize) throws SQLException {
        sql = "select * from employee offset "+ (pageNo*pageSize)+" rows fetch next "+ pageSize+" rows only";
        LOG.info("connection on fetchEmployee ===> "+ connection.getCatalog());
        ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        LOG.info("ResultSet on fetchEmployee ===> "+ rs);
        return rs;
    }

    public ResultSet fetchEmployeeById(String empId) throws SQLException {
        sql = "select * from employee where empId="+empId;
        ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();;
        return rs;
    }

    public int deleteEmployeeById(String empId) throws SQLException{
        sql = "delete from employee where empId="+empId;
        ps = connection.prepareStatement(sql);
        return ps.executeUpdate();
    }
}
