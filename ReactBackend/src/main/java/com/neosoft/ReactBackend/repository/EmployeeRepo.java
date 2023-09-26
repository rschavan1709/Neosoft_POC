package com.neosoft.ReactBackend.repository;

import com.neosoft.ReactBackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
}
