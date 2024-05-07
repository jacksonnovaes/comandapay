package com.codexmind.establishment.repository;

import com.codexmind.establishment.domain.Customer;
import com.codexmind.establishment.domain.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Page<Customer> findByStatus(Pageable pageable, Status status);

}
