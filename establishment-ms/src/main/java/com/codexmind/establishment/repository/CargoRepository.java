package com.codexmind.establishment.repository;

import com.codexmind.establishment.domain.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {
    Cargo findByName(String name);


}
