package com.dataquad.service;

import com.dataquad.dao.EmployeeDao;
import com.dataquad.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public ResponseEntity<Employee> saveEmployee(Employee employee) {
//        System.out.println(employee.getDob());
        Employee db = employeeDao.saveEmployee(employee);
        return new ResponseEntity<Employee>(db, HttpStatus.ACCEPTED);
    }

    public ResponseEntity fetchemployee(int id) {
        Optional<Employee> emp = employeeDao.fetchemployee(id);
        if (emp.isPresent()) {
            return new ResponseEntity<>(emp.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("The Employee is not found in the Database", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity updateemployee(int id, Employee employee) {
        Optional<Employee> emp1 = employeeDao.updateemployee(id);
        if ((emp1.isPresent())) {
            emp1.get().setFirstName(employee.getFirstName());
            emp1.get().setLastName(employee.getLastName());
            emp1.get().setDob(employee.getDob());
            emp1.get().setPhoneNo(employee.getPhoneNo());
            emp1.get().setEmail(employee.getEmail());
            emp1.get().setRole(employee.getRole());
            emp1.get().setDateOfJoining(employee.getDateOfJoining());
            emp1.get().setSalary(employee.getSalary());
            return new ResponseEntity<Employee>(employeeDao.saveEmployee(emp1.get()), HttpStatus.CREATED);

        } else {
            return new ResponseEntity("The Employee is not found in the Database", HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity deleteEmployee(int id){
         employeeDao.deleteEmployee(id);
        return new ResponseEntity( "The person has been removed", HttpStatus.OK);
    }
}






