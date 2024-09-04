package com.codexmind.establishment.repository;


import com.codexmind.establishment.domain.SchedulingDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingRepository extends JpaRepository<SchedulingDomain, Integer> {
}
