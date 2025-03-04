package com.example.Employee_Management_app.service;


import com.example.Employee_Management_app.entity.EmployeeDetailStructure;
import com.example.Employee_Management_app.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * service layer to bridge the details from controller layer to repoistory layer
 * user pass the parameter will be populated to repository predfined and userdefinted function
 */
@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    /**
     * constructor dependency created
     * @param employeeRepository
     */
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * save function created using employee object
     * @param employeeDetailStructure -> Employee details will passed as parameter to repository layer
     * @return employeedetailstructure object will be returned
     */
    public EmployeeDetailStructure save(EmployeeDetailStructure employeeDetailStructure) {
        EmployeeDetailStructure employeeDetailStructure1= employeeRepository.save(employeeDetailStructure);
        return employeeDetailStructure1;
    }

    /**
     * findall will be call using repository layer
     * @return we will get list of employee object
     */
    public List<EmployeeDetailStructure> findAll() {
        List<EmployeeDetailStructure> employeeDetailStructureList= employeeRepository.findAll();
        return employeeDetailStructureList;
    }

    /**
     * functon is used to call findbyid from reposiroty layer
     * @param id -> id is passed as argument using optional
     * @return employee object
     */
    public Optional<EmployeeDetailStructure> findById(Long id) {
        Optional<EmployeeDetailStructure> employeeDetailStructure=employeeRepository.findById(id);
        return employeeDetailStructure;
    }

    /**
     * tp update the existing use the logic mentioned
     * @param employeeDetailStructure -> this gives the value entered in update screen which is passed from controller layer
     * @return employee object returned after saving the details
     */
    public EmployeeDetailStructure update(EmployeeDetailStructure employeeDetailStructure) {
    Optional<EmployeeDetailStructure> existingoptUser= employeeRepository.findById(employeeDetailStructure.getId());
    EmployeeDetailStructure updateuser=existingoptUser.get();
    updateuser.setFirstName(employeeDetailStructure.getFirstName());
        updateuser.setLastName(employeeDetailStructure.getLastName());
        updateuser.setAge(employeeDetailStructure.getAge());
        updateuser.setAddress(employeeDetailStructure.getAddress());
        updateuser.setPhonenumber(employeeDetailStructure.getPhonenumber());
        updateuser.setEmail(employeeDetailStructure.getEmail());
        return employeeRepository.save(updateuser);
    }

    /**
     * to delete the specific we pass value to repository layer
     * @param id -> id we got from controller layer
     */
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    /**
     * this is userdefined function in repository layer to find email
     * @param email-> we get the email details from user input and passed by controller layer
     * @return mail if present in database
     */
    public Optional<EmployeeDetailStructure> findByEmail(String email) {
        Optional<EmployeeDetailStructure> mail=employeeRepository.findByEmail(email);
        return mail;

    }
}
