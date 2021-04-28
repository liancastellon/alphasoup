# Sopa de Letras
Genera una sopa de letras aleatoria.

## Clonar el proyecto
```
git clone https://github.com/liancastellon/alphasoup.git
```

## Ejecutar
### Manualmente
cd alphashoup
./gradlew bootRun
```
O, generando el jar con todas las dependencias - para ejecutarlo directamente:

```
./gradlew bootJar
cp build/libs/alphasoup-0.0.1-SNAPSHOT.jar <destdir>/<dest-name>.jar
cd <destdir>
java -jar <dest-name>.jar
```

El web service por defecto estará en `http://localhost:8080`

## docker
```
docker build -t liancg-alphasoup .
docker run -it -p 8080:8080 liancg-alphasoup
```

## docker-compose
```
docker-compose up
```

## Uso del web service

### Creación de sopa de letras

POST / con un JSON con las opciones:

```
{
  "w":15, // Ancho 
  "h":15, // Alto
  "ltr":true,  // Palabras izquierda-derecha
  "rtl":true,  // Palabras derecha-izquierda
  "ttb":true,  // Palabras arriba-abajo
  "btt":true,  // Palabras abajo-arriba
  "d":true     // Palabras diagonales
}

```

```
curl -X POST -d '{"w":15, "h":15, "ltr":true, "rtl":true, "ttb":true, "btt":true, "d":true}' -H 'Content-Type: application/json' http://localhost:8080/
```

La respuesta será un JSON con el UUID de la nueva sopa generada:

```
{"id":"42a8f832-b1df-4d44-8daa-5535dadf2eaa"}
```

Este id será usado en los siguientes requests, dondequiera que se indique `<id>`

### Lista de palabras

GET /list/<id>

```
curl http://localhost:8080/list/42a8f832-b1df-4d44-8daa-5535dadf2eaa

[
  "palabra1",
  "palabra2",
  ....
]
```

### Visualizar de palabras
GET /view/<id>

```
curl http://localhost:8080/view/42a8f832-b1df-4d44-8daa-5535dadf2eaa

b|t|x|o|o|x|n|i|p|t|c|y|z|n|y|
e|u|h|s|q|d|p|y|z|t|e|i|k|s|t|
x|b|s|k|b|c|b|d|h|b|x|g|d|k|r|
p|i|w|o|r|s|d|m|l|w|c|t|r|c|t|
w|s|s|q|m|d|d|m|h|x|p|o|q|f|a|
a|c|l|j|d|a|c|e|z|f|k|b|h|m|a|
s|f|h|d|c|n|n|e|d|q|g|j|n|u|a|
t|a|d|t|g|y|a|e|a|b|q|w|p|d|a|
u|d|q|x|p|j|p|e|s|v|t|x|u|h|a|
d|o|d|c|x|r|k|e|b|y|k|i|v|d|a|
e|i|g|p|k|y|p|e|w|m|f|q|k|d|a|
g|e|c|g|e|m|m|e|i|s|t|b|z|d|a|
i|m|r|p|h|j|f|e|f|e|j|d|g|e|a|
v|i|u|o|z|z|s|e|y|e|l|a|p|n|a|
e|s|t|l|i|w|p|e|y|v|j|a|m|z|a|
```

### Marcar palabra encontrada
PUT /<id> con un JSON con las opciones:

```
{
  "sr":0,   // Fila inicial
  "sc":0,   // Columna inicial 
  "er":10,  // Fila final
  "ec":10   // Columna final
}
```

```
curl -X PUT -d '{"sr":0,"sc":0, "er":10,"ec":10}' http://localhost:8080/42a8f832-b1df-4d44-8daa-5535dadf2eaa
```

Todas las palabras seleccionadas correctamente se mostraran en la sopa (al ejecutar el /view) en MAYUSCULAS:

```
b|t|x|o|o|x|n|i|p|t|c|y|z|n|y|
e|u|h|s|q|d|p|y|z|t|e|i|k|s|t|
x|b|s|k|b|c|b|d|h|S|x|g|d|k|r|
p|i|w|o|r|s|d|m|l|E|c|t|r|c|t|
w|s|s|q|m|d|d|m|h|L|p|o|q|f|a|
a|c|P|A|L|A|B|R|A|E|k|b|h|m|a|
s|f|h|d|c|n|n|e|d|C|g|j|n|u|a|
t|a|d|t|g|y|a|e|a|C|q|w|p|d|a|
u|d|q|x|p|j|p|e|s|I|t|x|u|h|a|
d|o|d|c|x|r|k|e|b|O|k|i|v|d|a|
e|i|g|p|k|y|p|e|w|N|f|q|k|d|a|
g|e|c|g|e|m|m|e|i|A|t|b|z|d|a|
i|m|r|p|h|j|f|e|f|D|j|d|g|e|a|
v|i|u|o|z|z|s|e|y|A|l|a|p|n|a|
e|s|t|l|i|w|p|e|y|v|j|a|m|z|a|
```

### Errores
En todos los requests, si se produce un error se devolverá un JSON con el mensaje de error:

```
{
  “message”:”Mensaje de error”
}
```

## Ejecutar los tests
`./gradlew test`

