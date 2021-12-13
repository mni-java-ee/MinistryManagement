package mni.ministry.mgmt.dao;

import mni.ministry.mgmt.db.DBConnection;
import mni.ministry.mgmt.dto.CreateNewEmpDto;
import mni.ministry.mgmt.models.Employee;

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
    private int result;

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

    public Employee fetchEmployee() throws SQLException {
        sql = "select * from tbl_employee where rownum = (select max(rownum) from tbl_employee)";
        ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return new Employee(BigInteger.valueOf(rs.getInt("id")), rs.getString("empName"),
                    rs.getString("empId"), rs.getString("empEmail"), rs.getInt("age"));
        }
        return null;
    }
}
