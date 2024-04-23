package com.codexmind.establishment.repository;

import com.codexmind.establishment.domain.Establishment;
import com.codexmind.establishment.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstablishmentRepository  extends JpaRepository<Establishment, Long> {

     Optional<Establishment> findByIdAndStatus(Long id, Status status);
}
