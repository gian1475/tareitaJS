# Práctica 3 – Matrícula de Cursos de Extensión

Sistema de matrícula de alumnos en cursos de extensión: Angular (frontend), Spring Boot (backend) y MySQL (base de datos), publicado en la nube con servicios gratuitos.

| | |
|---|---|
| **Página publicada** | https://matricula-front.onrender.com |
| **API del backend** | https://matricula-back.onrender.com/api/v1/especialidades |
| **Guía paso a paso (Word)** | [docs/Guia_despliegue_nube.docx](docs/Guia_despliegue_nube.docx) |

> **Nota:** el backend usa el plan gratuito de Render, que se apaga cuando nadie lo usa por 15 minutos. La primera vez que se abre la página puede tardar **hasta 1 minuto** en mostrar los datos; mientras tanto aparece el aviso "Conectando con el servidor..." y los datos se cargan solos, sin recargar.

## Guía para publicar en la nube

La guía en Word explica paso a paso cómo se publicó la base de datos, el backend y el frontend, con capturas y una sección de problemas frecuentes (por ejemplo, qué hacer si no salen las especialidades).

**Para descargarla:** abrir [docs/Guia_despliegue_nube.docx](docs/Guia_despliegue_nube.docx) y hacer clic en el botón de descarga (**Download raw file**).

Resumen:

1. **Base de datos:** MySQL Free en [Aiven](https://aiven.io/free-tier). Se ejecutan `sql_matri/MATRICULA_mysql.sql` y `sql_matri/procedimientos.sql`.
2. **Backend:** Render → Web Service con Docker, carpeta raíz `springMatri/demo`, con las variables `DB_URL`, `DB_USER`, `DB_PASSWORD` y `CORS_ORIGINS`.
3. **Frontend:** Render → Static Site, carpeta raíz `matri-front`, build `npm ci && npm run build`, publicación `dist/matri-front/browser` y la regla Rewrite `/*` → `/index.html`.

## Puntos de la práctica

| Punto | Dónde está |
|---|---|
| 1. Cursos de extensión por especialidad | Menú **Cursos** |
| 2. Matrícula: código desde 000001, solo alumnos del curso, pago automático según el tipo, stored procedure que resta la vacante | Enlace **Alumnos** de cada curso |
| 3. Cantidad de alumnos por curso con "Iniciar" / "No inicia" | Menú **Alumnos por curso** |
| 4. Mantenimiento de la tabla curso | Menú **Mantenimiento** |
| 5. Menú principal | Barra superior |

## Tecnologías

- **Frontend:** Angular 21
- **Backend:** Spring Boot 3.5, Java 21, Spring Data JPA, JdbcTemplate para los stored procedures
- **Base de datos:** MySQL 8.4 con los procedimientos `spadicion` (matricular) y `spanula` (anular)

## Estructura

```
matri-front/                  Frontend Angular
springMatri/demo/             Backend Spring Boot (incluye el Dockerfile)
sql_matri/                    Scripts de la base de datos y procedimientos
docs/                         Guía de despliegue en Word
thunder-matricula.postman_collection.json   Pruebas de la API
```

## Ejecutar en local

1. **Base de datos:** ejecutar los dos scripts de `sql_matri/` en un MySQL.
2. **Backend:** copiar `springMatri/demo/.env.example` como `.env`, completar los datos de conexión y luego:
   ```
   cd springMatri/demo
   ./mvnw spring-boot:run
   ```
   La API queda en http://localhost:8086/api/v1
3. **Frontend:**
   ```
   cd matri-front
   npm install
   npm start
   ```
   La página queda en http://localhost:4200
