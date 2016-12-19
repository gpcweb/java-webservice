# java-webservice

Para revisar este proyecto debes descargarlo con:

```git clone git@github.com:gpcweb/java-webservice.git```

Luego debes abrirlo con Spring STS y ejecutar como "Run as -> Spring Boot App"

Este webservice tiene los siguientes endpoints:

* Un endpoint que lista todos los recursos
```curl -v localhost:8080/users```
* Un endpoint que expone un recurso en particular
```curl -v localhost:8080/users/1``` 
* Un endpoint que permite crear un nuevo recurso:
```curl -v -H "Content-Type: application/json" -X POST -d '{"userName": "usuario1", image":"dafadsfasd"}' http://localhost:8080/users```

* Este ultimo endpoint tiene un par de validaciones:
  * Debes siempre como username enviar "usuario1", cualquier otro nombre devuelve un 401.
  * Debes siempre enviar el parametro userName, sino lo envias devuelve un 400.
  * Debes siempre enviar el parametro image, sino lo envias devuelve un 400.
  
 * Cuando un nuevo recurso es creado el webservice devuelve un 201 sin body pero con el header location del recurso recien creado.
