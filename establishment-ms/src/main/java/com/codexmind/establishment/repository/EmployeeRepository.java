package com.codexmind.establishment.repository;

import com.codexmind.establishment.domain.Employee;
import com.codexmind.establishment.domain.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByStatus(Pageable pageable, Status status);
    Optional<Employee> findByStatusAndId(Status status, Long id);

}
