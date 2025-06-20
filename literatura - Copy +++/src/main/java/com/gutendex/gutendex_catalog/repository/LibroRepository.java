package com.gutendex.gutendex_catalog.repository;


import com.gutendex.gutendex_catalog.model.Autor;
import com.gutendex.gutendex_catalog.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByIdioma(String idioma);
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    List<Libro> findAllByOrderByDescargasDesc();
    long countByIdioma(String idioma);
}
