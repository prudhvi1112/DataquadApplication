package com.dataquad.dao;

import com.dataquad.model.Employee;
import com.dataquad.repository.EmployeeRepo;
import com.dataquad.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
public class EmployeeDao {

    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee saveEmployee(Employee employee){
        return employeeRepo.save(employee);
    }
    public Optional<Employee> fetchemployee(int id){
        return employeeRepo.findById(id);
    }
    public Optional<Employee> updateemployee(int id){
        return employeeRepo.findById(id);

    }
 public void deleteEmployee(int id){
        employeeRepo.deleteById(id);
 }
}
