package mni.ministry.mgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeOT {
    private Integer id;
    private String empName;
    private String empId;
    private String empEmail;
    private int age;
}
