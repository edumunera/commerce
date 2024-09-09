# Spring Boot H2 Docker Application

Este proyecto es una aplicación Spring Boot que se conecta a una base de datos H2, empaquetada en un contenedor Docker.

## Prerrequisitos

Asegúrate de tener instalados los siguientes componentes:

- Para para poder ejecutar el contenedor [Docker Desktop](https://www.docker.com/products/docker-desktop).
- Git (opcional, si deseas clonar el repositorio en lugar de descargarlo) [Git](https://git-scm.com/downloads).
- Para probar la aplicación [Postman](https://www.postman.com/downloads/).

## Instrucciones

Sigue los pasos a continuación para configurar y ejecutar la aplicación.

### 1. Clonar el Repositorio o Descargar el Proyecto

Si tienes Git instalado, puedes clonar este repositorio. De lo contrario, descarga el proyecto como un archivo ZIP y descomprímelo.

### 2. Construir y Ejecutar la Aplicación

Abre una terminal o CMD en la ruta raíz del proyecto y ejecuta los siguientes comandos secuencialmente:
 
1) Construir la Imagen de la Aplicación
    ```
       docker build -t spring-comercio-app .
    ```    
2) Ejecutar el Contenedor de la Aplicación Spring Boot
   ```
       docker run -p 8080:8080 --name spring-comercio-app -d spring-comercio-app:latest
   ```
     
### 3. Probar APP
   1. **Buscar todos los precios:**
      - Solicitud HTTP: GET
      - Ruta: http://localhost:8080/api/prices

  2. **Buscar precios por prioridad:**
       - Solicitud HTTP: POST
       - Ruta: http://localhost:8080/api/prices
       - Header:
         * key: X-API-VERSION=1
       - Cuerpo de la solicitud (JSON):

          ```json
          {
              "brandId": 1,
              "startDate": "2020-06-14T10:00:00",
              "productId": 35455    
          }                         
### Datos de Ejemplo
La aplicación se inicializa con las tablas de schema.sql y los datos de prueba de data.sql, se pueden encontrar en la carperta src/main/resources de la aplicación.

## Contacto

Si tienes alguna pregunta, sugerencia o necesitas soporte relacionado con este proyecto, aquí te dejo mis datos de contacto:

### Correo Electrónico

- **Eduardo Munera Cabello**: [edumunera@gmail.com](edumunera@gmail.com)
