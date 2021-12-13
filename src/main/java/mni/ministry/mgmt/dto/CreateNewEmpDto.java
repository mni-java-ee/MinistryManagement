package mni.ministry.mgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewEmpDto {
    private String empName;
    private String empId;
    private String empEmail;
    private Integer age;
}
