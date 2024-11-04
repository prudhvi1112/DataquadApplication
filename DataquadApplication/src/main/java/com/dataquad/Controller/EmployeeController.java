package com.dataquad.Controller;

import com.dataquad.model.Employee;
import com.dataquad.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

   @Autowired
    private EmployeeService employeeService;

   @PostMapping("/save")
   public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
      return new ResponseEntity<>(employeeService.saveEmployee(employee).getBody(),HttpStatus.CREATED);
   }
    @GetMapping("/fetch")
    public ResponseEntity fetchemployee(@RequestParam int id){
      return new ResponseEntity<>(employeeService.fetchemployee(id).getBody(),HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateemployee(@PathVariable int id, @RequestBody Employee employee){
       return new ResponseEntity<>(employeeService.updateemployee(id, employee).getBody(),HttpStatus.CREATED);
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteEmployee(@RequestParam int id){
      return employeeService.deleteEmployee(id);
    }
}

