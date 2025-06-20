package com.gutendex.gutendex_catalog.service;


import com.gutendex.gutendex_catalog.model.Autor;
import com.gutendex.gutendex_catalog.model.Libro;
import com.gutendex.gutendex_catalog.repository.AutorRepository;
import com.gutendex.gutendex_catalog.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatabaseService {
    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public void guardarLibroYAutor(Libro libro) {
        // Primero buscamos si el autor ya existe
        Autor autorExistente = autorRepository.findByNombreContainingIgnoreCase(libro.getAutor().getNombre())
                .stream()
                .findFirst()
                .orElse(null);

        if (autorExistente != null) {
            libro.setAutor(autorExistente);
        } else {
            autorRepository.save(libro.getAutor());
        }

        libroRepository.save(libro);
    }

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public List<Libro> obtenerLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }

    public List<Autor> obtenerAutoresVivosEnAnio(Integer anio) {
        return autorRepository.findAutoresVivosEnAnio(anio);
    }

    public List<Libro> obtenerTop10LibrosMasDescargados() {
        return libroRepository.findAllByOrderByDescargasDesc()
                .stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Autor> buscarAutoresPorNombre(String nombre) {
        return autorRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public long contarLibrosPorIdioma(String idioma) {
        return libroRepository.countByIdioma(idioma);
    }
}
