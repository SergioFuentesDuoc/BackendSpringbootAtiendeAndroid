# Backend para Android (Spring Boot + MySQL)

CRUD REST de las 3 tablas de la app Android (`products`, `users`, `tipo_usuarios`).

## Ejecutar
1. Tener MySQL corriendo en `localhost:3306` (la BD `app_database` se crea sola).
2. Usuario/clave por defecto: `root` / vacía. Cambiar con variables de entorno `DB_USER` y `DB_PASSWORD`
   o editando `src/main/resources/application.properties`.
3. `mvnw.cmd spring-boot:run` (Windows) o `./mvnw spring-boot:run`.

Desde el emulador Android, la URL base es `http://10.0.2.2:8080/`.

## Endpoints
| Método | Ruta | Descripción |
|---|---|---|
| GET/POST | `/api/products` | Listar / crear productos |
| GET/PUT/DELETE | `/api/products/{id}` | Ver / actualizar / eliminar |
| GET/POST | `/api/tipo-usuarios` | Listar / crear tipos |
| GET/PUT/DELETE | `/api/tipo-usuarios/{id}` | Ver / actualizar / eliminar (409 si está en uso) |
| GET/POST | `/api/users` | Listar / crear usuarios |
| GET/PUT/DELETE | `/api/users/{id}` | Ver / actualizar / eliminar |
| POST | `/api/users/login` | `{ "email": "admin@duoc.cl", "password": "admin123" }` |

## Estructura
`model` → `repository` → `service` → `controller` (+ `exception` con el manejador global de errores).
