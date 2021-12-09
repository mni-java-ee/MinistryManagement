package mni.ministry.mgmt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.enterprise.inject.Model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

@Data
@Model
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    private BigInteger id;
    private String empName;
    private String empId;
    private String empEmail;
    private Integer age;

    public Employee(String empName, String empId, String empEmail, Integer age) {
        this.empName = empName;
        this.empId = empId;
        this.empEmail = empEmail;
        this.age = age;
    }
}
