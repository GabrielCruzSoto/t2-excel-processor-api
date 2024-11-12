# t2-excel-processor-api

## Descripción
Este proyecto es una API para la gestión de usuarios y la carga de archivos Excel. Utiliza Spring Boot y está diseñado para interactuar con una base de datos PostgreSQL. La API permite la autenticación de usuarios, la creación de nuevos usuarios y la carga de datos desde archivos Excel.

## Tecnologías Utilizadas
- **Spring Boot**: Framework para construir aplicaciones Java.
- **Spring Data JPA**: Para la interacción con la base de datos.
- **Spring Security**: Para la gestión de la seguridad y autenticación.
- **PostgreSQL**: Base de datos relacional.
- **Apache POI**: Para la manipulación de archivos Excel.
- **MapStruct**: Para la conversión entre objetos.
- **JUnit**: Para pruebas unitarias.

## Configuración
1. **Base de Datos**: Asegúrate de tener una base de datos PostgreSQL en funcionamiento. Configura las credenciales en el archivo `application.properties` en `src/main/resources`.

2. **Dependencias**: Este proyecto utiliza Maven para la gestión de dependencias. Asegúrate de tener Maven instalado y ejecuta el siguiente comando para compilar el proyecto:
   ```bash
   mvn clean install
   ```

3. **Ejecutar la Aplicación**: Puedes ejecutar la aplicación utilizando el siguiente comando:
   ```bash
   mvn spring-boot:run
   ```

## Endpoints
- **Autenticación**: `/auth/login` - Permite a los usuarios autenticarse.
- **Gestión de Usuarios**: `/users` - Permite crear nuevos usuarios.
- **Carga de Excel**: `/excel/upload` - Permite cargar archivos Excel y procesar los datos.

## Pruebas
```bash
mvn test
```

## Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.
