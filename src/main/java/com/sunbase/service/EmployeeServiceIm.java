package com.sunbase.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import com.sunbase.model.Employee;
import com.sunbase.repository.EmployeeRepository;


@Service

public class EmployeeServiceIm implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @Override
    public List<Employee> listAll(String keyword) {
        return employeeRepository.findAll();
    }


    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
	}

    @Override
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String keyword) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		if(keyword != null) {
			return employeeRepository.findAll(keyword, pageable);
		}
		
		return this.employeeRepository.findAll(pageable);
	}
}
