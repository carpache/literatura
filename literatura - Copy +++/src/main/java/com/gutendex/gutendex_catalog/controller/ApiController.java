package com.gutendex.gutendex_catalog.controller;


import com.gutendex.gutendex_catalog.model.Autor;
import com.gutendex.gutendex_catalog.model.Libro;
import com.gutendex.gutendex_catalog.service.ApiService;
import com.gutendex.gutendex_catalog.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ApiController implements CommandLineRunner {
    @Autowired
    private ApiService apiService;

    @Autowired
    private DatabaseService databaseService;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) throws Exception {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarTodosLosLibros();
                    break;
                case 3:
                    listarLibrosPorIdioma();
                    break;
                case 4:
                    listarAutoresVivosEnAnio();
                    break;
                case 5:
                    mostrarEstadisticasIdiomas();
                    break;

                case 0:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Buscar libro por título");
        System.out.println("2. Listar todos los libros");
        System.out.println("3. Listar libros por idioma");
        System.out.println("4. Listar autores vivos en un año");
        System.out.println("5. Mostrar estadísticas por idioma");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void buscarLibroPorTitulo() {
        System.out.print("\nIngrese el título del libro a buscar: ");
        String titulo = scanner.nextLine();

        List<Libro> libros = apiService.buscarLibrosPorTitulo(titulo);

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros con ese título.");
        } else {
            System.out.println("\nLibros encontrados:");
            libros.forEach(this::mostrarLibro);
        }
    }

    private void listarTodosLosLibros() {
        List<Libro> libros = databaseService.obtenerTodosLosLibros();

        if (libros.isEmpty()) {
            System.out.println("\nNo hay libros en la base de datos.");
        } else {
            System.out.println("\nTodos los libros:");
            libros.forEach(this::mostrarLibro);
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.print("\nIngrese el idioma a filtrar (ej. en, es, fr): ");
        String idioma = scanner.nextLine();

        List<Libro> libros = databaseService.obtenerLibrosPorIdioma(idioma);

        if (libros.isEmpty()) {
            System.out.println("No hay libros en ese idioma.");
        } else {
            System.out.println("\nLibros en idioma " + idioma + ":");
            libros.forEach(this::mostrarLibro);
        }
    }

    private void listarAutoresVivosEnAnio() {
        System.out.print("\nIngrese el año para buscar autores vivos: ");
        int anio = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        List<Autor> autores = databaseService.obtenerAutoresVivosEnAnio(anio);

        if (autores.isEmpty()) {
            System.out.println("No hay autores vivos en el año " + anio);
        } else {
            System.out.println("\nAutores vivos en " + anio + ":");
            autores.forEach(this::mostrarAutor);
        }
    }

    private void mostrarEstadisticasIdiomas() {
        System.out.println("\nEstadísticas por idioma:");

        long ingles = databaseService.contarLibrosPorIdioma("en");
        long espanol = databaseService.contarLibrosPorIdioma("es");
        long frances = databaseService.contarLibrosPorIdioma("fr");

        System.out.println("Inglés (en): " + ingles + " libros");
        System.out.println("Español (es): " + espanol + " libros");
        System.out.println("Francés (fr): " + frances + " libros");
    }




    private void mostrarLibro(Libro libro) {
        System.out.println("\nTítulo: " + libro.getTitulo());
        System.out.println("Idioma: " + libro.getIdioma());
        System.out.println("Descargas: " + libro.getDescargas());
        System.out.println("Autor: " + libro.getAutor().getNombre());
    }

    private void mostrarAutor(Autor autor) {
        System.out.println("\nNombre: " + autor.getNombre());
        System.out.println("Nacimiento: " + autor.getNacimiento());
        System.out.println("Fallecimiento: " + autor.getFallecimiento());
    }
}
