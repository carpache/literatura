package com.gutendex.gutendex_catalog.repository;

import com.gutendex.gutendex_catalog.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.nacimiento <= :year AND (a.fallecimiento IS NULL OR a.fallecimiento >= :year)")
    List<Autor> findAutoresVivosEnAnio(Integer year);

    List<Autor> findByNombreContainingIgnoreCase(String nombre);
}
