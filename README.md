# Backend de Registro de Personas

## Descripción
El Backend de Registro de Personas es una aplicación Spring Boot diseñada para registrar individuos con información esencial como Rut, Nombre, Apellido, Fecha de nacimiento, Dirección y Calle (Comuna y Región). La aplicación utiliza JPA para la interacción con la base de datos y H2 como base de datos en memoria para desarrollo y pruebas.

## Tecnologías
- Java 21
- Spring Boot 3.5.4 o superior
- Lombok
- JPA
- H2 Database
- Maven

## Funcionalidades
- Operaciones CRUD para el registro de personas
- Validación de los datos de entrada
- Manejo de excepciones para una mejor gestión de errores
- Soporte de base de datos en memoria con H2

## Instrucciones de instalación

### Requisitos
- Java 21
- Maven

### Pasos para ejecutar la aplicación
1. Clonar el repositorio:
   ```
   git clone <repository-url>
   cd person-registry-backend
   ```

2. Construir el proyecto con Maven:
   ```
   mvn clean install
   ```

3. Ejecutar la aplicación:
   ```
   mvn spring-boot:run
   ```

4. Acceder a la aplicación en:
   ```
   http://localhost:8080
   ```

### Inicialización de la base de datos
La aplicación está configurada para usar H2 como base de datos en memoria. Puedes precargar datos modificando el archivo `src/main/resources/data.sql`.

## Documentación de la API (OpenAPI / Swagger)

- Rutas útiles (una vez la aplicación esté en ejecución en http://localhost:8080):
  - OpenAPI JSON: http://localhost:8080/v3/api-docs
  - Swagger UI: http://localhost:8080/swagger-ui.html  (también accesible en /swagger-ui/index.html)

## Endpoints de la API
- `POST /api/persons` - Registrar una nueva persona
- `GET /api/persons` - Obtener todas las personas
- `GET /api/persons/{id}` - Obtener una persona por ID
- `PUT /api/persons/{id}` - Actualizar la información de una persona
- `DELETE /api/persons/{id}` - Eliminar una persona por ID

## Pruebas
Se incluyen pruebas unitarias en el proyecto para asegurar la funcionalidad del PersonController. Puedes ejecutar las pruebas con:
```
mvn test
```

## Licencia
Este proyecto está bajo la licencia MIT. Verifica el archivo LICENSE para más detalles.