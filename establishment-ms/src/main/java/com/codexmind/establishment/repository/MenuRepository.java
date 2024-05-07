package com.codexmind.establishment.repository;

import com.codexmind.establishment.domain.Menu;
import com.codexmind.establishment.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Optional<Menu> findByIdAndStatus(Integer id, Status status);
}
