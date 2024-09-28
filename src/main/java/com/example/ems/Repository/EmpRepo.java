package com.example.ems.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ems.Entity.Employee;


@Repository
public interface EmpRepo extends JpaRepository<Employee, Long>{

}
