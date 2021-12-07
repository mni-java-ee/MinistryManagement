package mni.ministry.mgmt.controllers;

import mni.ministry.mgmt.dto.EmployeeListDto;
import mni.ministry.mgmt.services.EmployeeService;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EmployeeController {

    @Inject
    private EmployeeService employeeService;

    @WebMethod
    public EmployeeListDto getAllEmployee(){
        EmployeeListDto employeeListDto = new EmployeeListDto();
        employeeListDto.setEmployees(employeeService.fetchAllEmployee());
        return employeeListDto;
    }
}
