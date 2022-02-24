package com.example.demo.controller;


import com.example.demo.dao.EmployeeRepository;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
//http://localhost:8080/api
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

   // http://localhost:8080/api/save
    @PostMapping("/save")
    public void save(@RequestBody Employee employee){
        employeeRepository.save(employee);
    }

    @GetMapping("/show/{id}")
    public Employee show(@PathVariable String id){
        Long employee_id = Long.parseLong(id);
     return  employeeRepository.findById(employee_id).get();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id){
        Long employee_id = Long.parseLong(id);
        employeeRepository.deleteById(employee_id);
    }

    @PutMapping("/edit/{id}")
    public void update(@PathVariable String id ,@RequestBody Employee employee){
            Long employee_id = Long.parseLong(id);
          Employee employee1=  employeeRepository.findById(employee_id).orElse(null);
            if (employee1 != null) {
                if (employee.getFullName() != null)
                    employee1.setFullName(employee.getFullName());
                if (employee.getAddress() != null)
                    employee1.setAddress(employee.getAddress());
                if (employee.getAge() != null)
                    employee1.setAge(employee.getAge());
                if (employee.getPhone() != null)
                    employee1.setPhone(employee.getPhone());

                employeeRepository.save(employee1);
            }
    }

}
