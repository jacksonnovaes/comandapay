package com.codexmind.establishment.repository;

import com.codexmind.establishment.domain.Menu;
import com.codexmind.establishment.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Optional<Menu> findByIdAndStatus(Integer id, Status status);


    @Query(
            value = """
                            SELECT ME.ID,ME.NAME,ME.ESTABLISHMENT_ID, ME.STATUS
                            FROM TB_MENU ME INNER JOIN TB_ESTABLISHMENT ESTAB
                            ON ESTAB.ID = ME.ESTABLISHMENT_ID
                            WHERE ESTAB.ID = ?1
                    """, nativeQuery = true
    )
    List<Menu> findMenusByEstablishmenteId(Integer id);
}
