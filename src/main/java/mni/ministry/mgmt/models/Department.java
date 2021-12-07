package mni.ministry.mgmt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private BigInteger id;
    private String departmentName;
    private String departmentCode;
    List<Employee> employees;
}
