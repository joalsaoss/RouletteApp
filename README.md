# RouletteApp
RouletteApp

## Antes de ejecutar el proyecto
* Asegurarse de tener instalado Redis server y en ejecución.

## Ejecutar el proyecto

+ Descargar el repositorio
Importar el proyecto a un IDE como SpringBootSuite
Actualizar el proyecto, para esto se realiza: Clic derecho sobre el proyecto - Maven - update project
Se corre la aplicacion: Clic derecho - Run - Spring boot app
Url de los endpoint

Metodo POST crear ruleta: http://localhost:8080/roulette/create-roulette
Metodo PUT abrir ruleta: http://localhost:8080/roulette/open-roulette/"ID-RULETA"
Metodo PUT hacer apuesta: http://localhost:8080/roulette/wager/"ID-RULETA" Para este endpoint se usa lo siguiente HEADER key: "username", value: user BODY { "betValue" : "8", "userAmount": 1000 }
NOTA: betValue puede tener el valor de "red" o "black" y los numeros entre 0 y 36. userAmount es la cantidad que puede apostar, esta puede ser maximo de 10.000

Metodo PUT cerrar ruleta: http://localhost:8080/roulette/close-roulette/"ID-RULETA"
Metodo GET obtener todas las ruletas: http://localhost:8080/roulette/get-roulettes
