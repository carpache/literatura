package com.gutendex.gutendex_catalog.service;


import com.gutendex.gutendex_catalog.dto.AutorDTO;
import com.gutendex.gutendex_catalog.dto.LibroDTO;
import com.gutendex.gutendex_catalog.dto.ResultDTO;
import com.gutendex.gutendex_catalog.model.Autor;
import com.gutendex.gutendex_catalog.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiService {
    private static final String API_URL = "https://gutendex.com/books/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DatabaseService databaseService;

    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        String url = API_URL + "?search=" + titulo.replace(" ", "%20");
        ResultDTO result = restTemplate.getForObject(url, ResultDTO.class);

        if (result != null && result.getLibros() != null && !result.getLibros().isEmpty()) {
            return result.getLibros().stream()
                    .map(this::convertirDTOaLibro)
                    .peek(libro -> databaseService.guardarLibroYAutor(libro))
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    private Libro convertirDTOaLibro(LibroDTO libroDTO) {
        Libro libro = new Libro();
        libro.setTitulo(libroDTO.getTitulo());
        libro.setDescargas(libroDTO.getDescargas() != null ? libroDTO.getDescargas() : 0);

        // Tomamos el primer idioma de la lista
        libro.setIdioma(libroDTO.getIdiomas() != null && !libroDTO.getIdiomas().isEmpty()
                ? libroDTO.getIdiomas().get(0) : "en");

        // Tomamos el primer autor de la lista
        if (libroDTO.getAutores() != null && !libroDTO.getAutores().isEmpty()) {
            AutorDTO autorDTO = libroDTO.getAutores().get(0);
            Autor autor = new Autor();
            autor.setNombre(autorDTO.getNombre() != null ? autorDTO.getNombre() : "Desconocido");
            autor.setNacimiento(autorDTO.getNacimiento());
            autor.setFallecimiento(autorDTO.getFallecimiento());
            libro.setAutor(autor);
        } else {
            Autor autor = new Autor();
            autor.setNombre("Desconocido");
            libro.setAutor(autor);
        }

        return libro;
    }
}
