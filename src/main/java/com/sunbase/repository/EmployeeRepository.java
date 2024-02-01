package com.sunbase.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunbase.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
	@Query("SELECT e FROM Employee e WHERE LOWER(CONCAT(e.city, ' ', e.email, ' ', e.address, ' ', e.firstName, ' ', e.lastName)) LIKE %?1% " +
		       "OR LOWER(e.city) LIKE %?1% OR LOWER(e.email) LIKE %?1% OR LOWER(e.address) LIKE %?1% OR LOWER(e.firstName) LIKE %?1% OR LOWER(e.lastName) LIKE %?1%")
		public Page<Employee> findAll(String keyword, Pageable pageable);
	
	@Query("SELECT e FROM Employee e WHERE e.email = :email")
    Employee getUserByEmail(@Param("email") String email);
}
