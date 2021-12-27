ALTER USER muhamad_iqbal IDENTIFIED BY 123Abcdefgh!# ACCOUNT UNLOCK;

create function fn_create_new_emp(empName varchar2, empId varchar2, empEmail varchar2, age number)
    return varchar2
    is
    tmpName     varchar2(128);
    tmpEmpId    varchar2(128);
    tmpEmpEmail varchar2(128);
    tmpAge      number(2);
    type tmpBulkId is table of number;
    bulkEmpId   tmpBulkId;
begin
    tmpName := empName;
    tmpEmpId := empId;
    tmpEmpEmail := empEmail;
    tmpAge := age;

    insert into employee(EMPNAME, EMPID, EMPEMAIL, AGE)
    values (tmpName, tmpEmpEmail, tmpEmpEmail, tmpAge)
    returning empId bulk collect into bulkEmpId;

    return bulkEmpId.COUNT;
end;

create function fn_fetch_all_employee(page_no in number, page_size in number)
    return employees_nt pipelined
is
    cursor emp_list is
        select
        'column_value1' as id,
        'column_value2' as empName,
        'column_value3' as empId,
        'column_value4' as empEmail,
        'column_value5' as age
        from employee offset page_no*page_size rows fetch next page_size rows only;
    -- list_employee employees_nt := employees_nt();
begin
    for emp_result in emp_list
    loop
        pipe row (
            employee_ot(
                emp_result.id,
                emp_result.empName,
                emp_result.empId,
                emp_result.empEmail,
                emp_result.age)
            );
    end loop;

    return;
end fn_fetch_all_employee;
/


create function fn_fetch_employee_by_id(employeeId in varchar2)
    return employees_nt
is
    current_employee employees_nt := employees_nt();
begin
    select employee_ot(id, empName, empId, empEmail, age)
        bulk collect into current_employee from employee where empId = employeeId;
    return current_employee;
end fn_fetch_employee_by_id;
/

create function fn_update_employee_by_id(current_employee_ot in update_employee_ot)
    return number
is
    type tmp_rows_updated is table of number;
    rows_updated tmp_rows_updated;
begin
    update EMPLOYEE set EMPNAME=current_employee_ot.EMPNAME, EMPEMAIL=current_employee_ot.EMPEMAIL, AGE=current_employee_ot.AGE
    where EMPID=current_employee_ot.EMPID returning ID bulk collect into rows_updated;

    return rows_updated.COUNT;
end fn_update_employee_by_id;
/

create function fn_delete_employee_by_empId(employeeId in number)
    return number
is
    type tmp_rows_deleted is table of number;
    rows_deleted tmp_rows_deleted;
begin
    delete from EMPLOYEE e where e.EMPID=employeeId returning e.ID bulk collect into rows_deleted;
    return rows_deleted.COUNT;
end fn_delete_employee_by_empId;
/

create function oddEvenNumber(x in number)
return VARCHAR2
is
begin
    if x mod 2 = 0 then
        return ('The number is Even='||x);
    else
        return ('The number is Odd='||x);
    end if;
end;
/

create function welcome_msg_func(p_name in varchar2)
return VARCHAR2
is
begin
return ('Welcome'|| p_name);
end;
/

