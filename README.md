# 📚 Catálogo de Libros - Gutendex API

![Java](https://img.shields.io/badge/Java-17+-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)

Aplicación Spring Boot completa para interactuar con la API de Gutendex (Project Gutenberg) que permite buscar, almacenar y analizar datos de libros y autores.

## 🌟 Características

- **Búsqueda avanzada** de libros por título
- **Persistencia** en base de datos PostgreSQL
- **Consultas especializadas**:
    - Libros por idioma (inglés, español, francés)
    - Autores vivos en año específico
- **Estadísticas** completas
- **Interfaz de línea de comandos** (CLI) intuitiva

## 🛠 Stack Tecnológico

- **Backend**:
    - Java 17
    - Spring Boot 3.1
    - Spring Data JPA
    - Jackson (v2.16)

- **Base de Datos**:
    - PostgreSQL 15+

- **Dependencias Principales**:
  ```xml
  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
      <groupId>org.postgresql</groupId>
      <artifactId>postgresql</artifactId>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.16.0</version>
    </dependency>
  </dependencies>

## 🚀 Configuración Rápida
Requisitos Mínimos
- Java JDK 17+
- PostgreSQL 15+ o Docker
- Maven 3.8+

Pasos de Instalación:
1. Clonar repositorio:
````
git clone https://github.com/tu-usuario/gutendex-client.git
cd gutendex-client
````
2. Configurar PostgreSQL con Docker (recomendado):
````
docker run --name gutendex-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=gutendex -p 5432:5432 -d postgres:15
````
3. Configurar aplicación (src/main/resources/application.properties):
````
spring.datasource.url=jdbc:postgresql://localhost:5432/gutendex
spring.datasource.username=postgres
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
server.port=8080
````
4. Ejecutar aplicación
````
mvn clean install
mvn spring-boot:run
````

##  🖥 Manual de Uso
La aplicación muestra un menú interactivo con las siguientes opciones:
````
=== MENÚ PRINCIPAL ===
1. Buscar libro por título
2. Listar todos los libros
3. Listar libros por idioma
4. Listar autores vivos en un año
5. Mostrar estadísticas por idioma
6. Top 10 libros más descargados
0. Salir
````

**Ejemplo de Flujo**
````
Buscar libro:

Ingrese título: "Don Quijote"

La aplicación consulta la API y guarda resultados

Ver estadísticas:

Muestra conteo por idiomas

Consultar autores:

Buscar autores vivos en 1850
````
## 🏗 Estructura del Código
````
src/main/java/com/example/gutendex/
├── config/              # Configuraciones
├── controller/         # Lógica de CLI
├── dto/                # Objetos para la API
│   ├── AutorDTO.java
│   ├── LibroDTO.java
│   └── ResultDTO.java
├── model/              # Entidades JPA
│   ├── Autor.java
│   └── Libro.java
├── repository/         # Repositorios
│   ├── AutorRepository.java
│   └── LibroRepository.java
├── service/            # Servicios
│   ├── ApiService.java
│   └── DatabaseService.java
└── GutendexApplication.java # Main
````

