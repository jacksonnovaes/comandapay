package com.codexmind.establishment.repository;

import com.codexmind.establishment.domain.AppConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppConfigurationRepository extends JpaRepository<AppConfiguration, Integer> {

    Optional<AppConfiguration> findByEstablishmentId(Integer id);
}
