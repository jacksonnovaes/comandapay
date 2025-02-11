package com.codexmind.establishment.repository;

import com.codexmind.establishment.domain.Customer;
import com.codexmind.establishment.domain.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Page<Customer> findByStatus(Pageable pageable, Status status);

    @Query(value = """
            SELECT pers.id,pers.name, pers.last_name, pers.phone,
             pers.cel_phone, pers.status, pers.user_id,pers.admission_date, pers.birth_date, pers.cargo_id,
             pers.establishment_id,
             pers.cpf, pers.url_image FROM tb_person pers INNER JOIN tb_users us on us.id = pers.user_id WHERE pers.status = ?1 and us.id = ?2""", nativeQuery = true)
    Optional<Customer> findImageByID(Status status, Integer employeeId);

}
