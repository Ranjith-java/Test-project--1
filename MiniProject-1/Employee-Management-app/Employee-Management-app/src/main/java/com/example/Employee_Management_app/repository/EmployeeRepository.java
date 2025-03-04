package com.example.Employee_Management_app.repository;

import com.example.Employee_Management_app.entity.EmployeeDetailStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository layer helps with pre defined JPA function to achieve CRUD
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetailStructure, Long> {
    /**
     * used defined function to find email
     * @param email-> we get this parameter from user end and passed through controller and service layer
     * @return email value if it present in database
     */
    Optional<EmployeeDetailStructure> findByEmail(String email);
}
