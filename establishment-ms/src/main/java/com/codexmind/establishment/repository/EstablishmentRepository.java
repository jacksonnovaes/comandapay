package com.codexmind.establishment.repository;

import com.codexmind.establishment.domain.Establishment;
import com.codexmind.establishment.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Integer> {

    Optional<Establishment> findByIdAndStatus(Integer id, Status status);


    @Query(value = """
            select
            estab.id,
            estab.name,
            estab.cnpj,
            estab.rate,
            estab.address_id,
            estab.status,
            estab.customer_id,
            estab.url_image,
            estab.is_favorite
            from tb_person tp inner join tb_establishment estab on estab.id = tp.establishment_id where tp.id = ?1
            """, nativeQuery = true)
    Optional<Establishment> getEstablishmentByUserLoggedId(Integer personId);

    @Query(value = """
            select te.id,
            estab.id,
            estab.name,
            estab.cnpj,
            estab.rate,
            estab.address_id,
            estab.status,
            estab.customer_id,
            estab.url_image,
            estab.is_favorite
            from tb_establishment estab
            inner join tb_person tp on tp.establishment_id = estab.id where tp.id = ?1 ;
            """, nativeQuery = true)
    Optional<Establishment> getEstablishmentByEmployee(Integer employeeId);

    @Query(value = """
            SELECT estab.id, estab.name, estab.cnpj, estab.address_id, estab.status,
            addr.name as logradouro, addr.complemento, addr.postal_code,estab.customer_id, estab.rate,
             estab.url_image, estab.is_favorite
            FROM tb_establishment estab
            INNER JOIN tb_address addr
            ON addr.id = estab.address_id
            WHERE ?1 IS NULL OR estab.name LIKE CONCAT('%', ?1, '%') AND estab.status = ?2
            """, nativeQuery = true)
    List<Establishment> findEstablishmentByName(String name, Status status);

    @Query(value = """
            SELECT estab.id, estab.name, estab.cnpj, estab.address_id, estab.status,
            addr.name as logradouro, addr.complemento, addr.postal_code,estab.customer_id, estab.rate,
              estab.url_image, estab.is_favorite
            FROM tb_establishment estab
            INNER JOIN tb_address addr
            ON addr.id = estab.address_id
            WHERE estab.status = ?1
            """, nativeQuery = true)
    List<Establishment> findAllEstablishment(Status status);

    @Query(value = """
            SELECT
            estab.id,
            estab.name,
            estab.cnpj,
            estab.rate,
            estab.address_id,
            estab.status,
            estab.customer_id,
            estab.url_image,
            estab.is_favorite
            FROM tb_establishment estab
            INNER JOIN tb_person customer
            ON customer.id = estab.customer_id
            WHERE customer.id = ?1 AND estab.status = ?2
            """, nativeQuery = true)
    List<Establishment> findFavorites(Integer customerId, Status status);
}
