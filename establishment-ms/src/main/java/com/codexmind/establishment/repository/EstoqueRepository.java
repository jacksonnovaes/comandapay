package com.codexmind.establishment.repository;


import com.codexmind.establishment.domain.Address;
import com.codexmind.establishment.domain.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

}