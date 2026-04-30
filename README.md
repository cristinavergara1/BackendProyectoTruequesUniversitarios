# 📦 Backend - Plataforma de Trueques Universitarios

Este proyecto corresponde al backend de una plataforma de trueques universitarios, desarrollado con **Spring Boot**, que permite a los usuarios registrarse, autenticarse y gestionar artículos para intercambio.

---

## 🚀 Tecnologías utilizadas

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA
* PostgreSQL
* Maven
* Jakarta Validation

---

## 🧠 Arquitectura del proyecto

El backend sigue una arquitectura en capas:

```
Controller → DTO → Service → Entity → Repository
                 ↑
              Mapper
```

### 🔹 Capas

* **Controller**: Manejo de endpoints REST
* **DTO**: Transferencia de datos (entrada/salida)
* **Service**: Lógica de negocio
* **Entity**: Modelos de base de datos
* **Repository**: Acceso a datos
* **Mapper**: Conversión entre DTOs y entidades

---

## 🔐 Funcionalidades principales

### 👤 Usuarios

* Registro de usuarios
* Validación de datos (correo, contraseña)
* Confirmación de contraseña
* Login básico

### 📦 Artículos

* Crear artículos asociados a un usuario
* Listar artículos
* Consultar artículo por ID
* Actualizar artículos
* Eliminar artículos
* Consultar artículos por usuario

---

## 🔄 Uso de DTOs

Se implementó el uso de DTOs para:

* Evitar exponer entidades directamente
* Proteger información sensible (como contraseñas)
* Mantener desacoplamiento entre capas

---

## 🔗 Endpoints principales

### 👤 Usuarios

| Método | Endpoint                 | Descripción       |
| ------ | ------------------------ | ----------------- |
| POST   | `/api/usuarios/registro` | Registrar usuario |
| POST   | `/api/usuarios/login`    | Iniciar sesión    |
| GET    | `/api/usuarios`          | Listar usuarios   |
| GET    | `/api/usuarios/{id}`     | Obtener usuario   |

---

### 📦 Artículos

| Método | Endpoint                             | Descripción           |
| ------ | ------------------------------------ | --------------------- |
| POST   | `/api/articulos`                     | Crear artículo        |
| GET    | `/api/articulos`                     | Listar artículos      |
| GET    | `/api/articulos/{id}`                | Obtener artículo      |
| PUT    | `/api/articulos/{id}`                | Actualizar artículo   |
| DELETE | `/api/articulos/{id}`                | Eliminar artículo     |
| GET    | `/api/articulos/usuario/{usuarioId}` | Artículos por usuario |

---

## 🧪 Ejemplo de petición

### Crear artículo

```json
{
  "titulo": "Libro de matemáticas",
  "descripcion": "Buen estado",
  "estado": "Usado",
  "categoria": "Educación",
  "tipo": "Intercambio",
  "condiciones": "Sin hojas dañadas",
  "imagen": "url_imagen",
  "usuarioId": 1
}
```

---

## ⚠️ Validaciones implementadas

* Campos obligatorios (`@NotBlank`)
* Email válido (`@Email`)
* Contraseña mínima (`@Size`)
* Validación de confirmación de contraseña
* Validación de usuario existente al crear artículos

---

## 🔧 Instalación y ejecución

### 1. Clonar repositorio

```bash
git clone https://github.com/rubengutierrezb/BackendProyectoTruequesUniversitarios
cd BackendProyectoTruequesUniversitarios
```

### 2. Configurar base de datos

Editar `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/bdtrueques
spring.datasource.username=postgres
spring.datasource.password=2904
spring.jpa.hibernate.ddl-auto=update
```

---

### 3. Ejecutar proyecto

```bash
./mvnw spring-boot:run
```

---

## 📌 Estado del proyecto

✅ Backend funcional
✅ CRUD completo de usuarios y artículos
✅ Uso de DTOs
✅ Validaciones implementadas

---

## 🚀 Mejoras futuras

* Implementar autenticación con JWT
* Manejo global de errores (`@ControllerAdvice`)
* Paginación y filtros
* Subida de imágenes
* Seguridad avanzada

---

## 👨‍💻 Autor

Desarrollado por **EBV 15**

---

## 📄 Licencia

Este proyecto es de uso académico de la Universidad de Antioquia.
