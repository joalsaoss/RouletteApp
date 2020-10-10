# RouletteApp
RouletteApp

## Antes de ejecutar el proyecto
+ Asegurarse de tener instalado Redis server y en ejecución.

## Ejecutar el proyecto
+ Descargar el repositorio
+ Importar el proyecto a un IDE como SpringBootSuite (maven).
+ Dar clic derecho sobre el proyecto RouletteApi seleccionar Run y luego Spring boot app

## Endpoints
### Crear ruleta
+ URL: http://localhost:8080/roulette/create-roulette
+ Method: POST

### Abrir ruleta: 
+ URL: http://localhost:8080/roulette/open-roulette/{idRoulette}
+ Method: PUT
+ Params: idRoulette

### Hacer apuesta
+ URL: http://localhost:8080/roulette/wager/{idRoulette} 
+ Method: POST
+ Header: key: "username", 
+ Value: BODY { "betValue" : "red", "userAmount": 5000 }

### Cerrar ruleta
+ URL: http://localhost:8080/roulette/close-roulette/{idRoulette}
+ Method: PUT
+ Params: idRoulette

### Obtener todas las ruletas: 
+ URL: http://localhost:8080/roulette/get-roulettes
+ Method: GET
