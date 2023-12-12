# Proyecto de Programación de Servicios y Procesos (PSP)

Este repositorio contiene el código fuente para el proyecto de la unidad didáctica 1 de PSP, centrado en la gestión de procesos en Java.

## Estructura del Proyecto

El proyecto está estructurado de la siguiente manera:

- `src/main/java/org/example/App.java`: Clase principal que ejecuta la aplicación.
- `src/main/java/org/example/Piping.java`: Clase que implementa la funcionalidad de piping entre procesos.

## Descripción

El proyecto ilustra el manejo avanzado de procesos en Java. La clase `Piping` demuestra cómo se pueden comunicar dos procesos, similar a la funcionalidad de piping en sistemas Unix/Linux, pero implementado en Java.

### `App.java`

Esta es la clase principal del proyecto y sirve como punto de entrada para la ejecución del programa. Aquí se pueden realizar pruebas y demostraciones de la funcionalidad implementada en `Piping.java`.

### `Piping.java`

Esta clase contiene la lógica para crear y manejar la comunicación entre dos procesos. Utiliza `ProcessBuilder` para crear procesos y gestionar su entrada y salida.

## Cómo Usar

Para ejecutar el proyecto, asegúrate de tener instalado Java y Maven. Puedes compilar y ejecutar el proyecto utilizando Maven:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="org.example.App"
```

## Contribuciones

Las contribuciones al proyecto son bienvenidas. Por favor, asegúrate de seguir las buenas prácticas de desarrollo y documentación de código.

## Licencia

Este proyecto se distribuye bajo la licencia [MIT](./license.txt), lo que permite la reutilización y distribución con ciertas condiciones.