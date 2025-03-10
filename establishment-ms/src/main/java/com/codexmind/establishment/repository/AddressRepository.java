package com.codexmind.establishment.repository;


import com.codexmind.establishment.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Optional<Address> findBypostalCode(String postalcode);
}
