# AREP- ARQUITECTURAS EMPRESARIAL - Taller 4.

## AUTOR.

> Eduardo Ospina Mejia

## Taller Clientes y Servicios.

### Introduccion.

Para este laboratorio se requieren conocimientos de tres herramientas para su desarrollo, siendo estas :
1) MVN
2) GIT
3) Librerias de java.

#### Descripción

Para este taller los estudiantes deberán construir un servidor Web (tipo Apache) en Java. El servidor debe ser capaz de entregar páginas html e imágenes
tipo PNG. Igualmente el servidor debe proveer un framework IoC para la construcción de aplicaciones web a partir de POJOS. Usando el servidor se debe
construir una aplicación Web de ejemplo y desplegarlo en Heroku. El servidor debe atender múltiples solicitudes no concurrentes.

Para este taller desarrolle un prototipo mínimo que demuestre capcidades reflexivas de JAVA y permita por lo menos cargar un bean (POJO) y derivar una
aplicación Web a partir de él. Debe entregar su trabajo al final del laboratorio.

En su versión final el framework debe explorar el directorio raiz (o classpath) buscando classes con una anotación que indique que son componentes, por
ejemplo @Component, y cargar todos los que tengan dicha anotación. Así no tendrá que especificarlos siempre en la línea de comandos.


#### Entregables

1. El código fuente del proyecto y el ciclo de vida debe ser estructurado y manejado usando Maven.

2. El proyecto se debe almacenar en la cuenta de GitHub del estudiante.


### Desplegando localmente.

1) clonar el repositorio, ya se a traves de cmd o de GIT: que contiene todos los ejercicios y retos. 

![](https://i.postimg.cc/903b4YH3/Capture1.png)
![](https://i.postimg.cc/wMcX1KkJ/Capture2.png)

2) ingresamos al proyecto clonado y desde cmd hacer uso de mvn para clean y package:

![](https://i.postimg.cc/L4zBCfMX/Capture3.png)

![](https://i.postimg.cc/RV41jFMT/Capture4.png)


3) Ejecutamos el proyecto utilizando los comandos en el cmd o corremos directamente desde la ide.
   

```maven
mvn exec:java -Dexec.mainClass="edu.escuelaing.arep.CorrerServidor"
```

![](https://i.postimg.cc/d0brnQHr/Capture5.png)

![](https://i.postimg.cc/4dpt0CqM/Capture6.png)

![](https://i.postimg.cc/GtrYw71p/Capture7.png)

![](https://i.postimg.cc/pVYzYHD5/Capture8.png)

4) abrirlo en el navegador utilizando localhost con las rutas aplicadas:

![](https://i.postimg.cc/NfsTdz4J/Capture9.png)

![](https://i.postimg.cc/3xWGT0z2/Capture10.png)

![](https://i.postimg.cc/pL7FXXt4/Capture11.png)

![](https://i.postimg.cc/761GV3Tr/Capture12.png)

![](https://i.postimg.cc/NMtL5KCJ/Capture13.png)

![](https://i.postimg.cc/Yq00GK8H/Capture14.png)

![](https://i.postimg.cc/NfnMRJPr/Capture15.png)


### Requisitos:
1)   [Java 8](https://www.java.com/download/ie_manual.jsp)
2)   [Maven](https://maven.apache.org/download.cgi)
3)   [Git](https://git-scm.com/downloads)
4)   IDE de java.

### Desplegando de forma remota.

Ya se encuentra desplguegado de forma remota, utilizando el recurso de heroku se esta cumpliendo esta funcion por lo que paracorrer el API o el recurso web
se puede lograr ingresando la url:

**https://taller-4arep.herokuapp.com/**

![](https://i.postimg.cc/3xnPyNr8/Capture16.png)

![](https://i.postimg.cc/5trT3rFD/Capture17.png)

![](https://i.postimg.cc/GtwN0sKQ/Capture18.png)

![](https://i.postimg.cc/qBySsjBZ/Capture19.png)

![](https://i.postimg.cc/vHyj3fc8/Capture20.png)

![](https://i.postimg.cc/W1byPVGQ/Capture21.png)

![](https://i.postimg.cc/SxTvSPVj/Capture22.png)

### Tecnologias Implementadas.

* [Heroku](https://heroku.com) - Plataforma de despliegue en la nube.

* [Maven](https://maven.apache.org/) - Administrador de dependencias.


### Extender

La extensibilidad de este proyecto llega a ser muy alta, gracias a implementar un microframework le pueden agregar facilemtne nuevas url de accesso a nuevas
paginas que cree el que utilice el programa. aunque llegue a contar con estos metodos, al momento que intenten agregar cosas nuevas puede pasar que no este
soportado y genere error al correr el servidor. Por lo que aun tiene muchos puntos pro mejorar.

## Construido con
-   HTML
-   javascript
-   java

### Javadocs:

se genera el Javadoc:

```maven
mvn javadoc:javadoc
```

![](https://i.postimg.cc/zXMGSS1y/Capture1.png)

![](https://i.postimg.cc/NjHfN9gp/Capture2.png)

![](https://i.postimg.cc/02JsvTLN/Capture3.png)

## Despliegue Heroku:

[![ProjectDesign](https://www.herokucdn.com/deploy/button.png)](https://taller-4arep.herokuapp.com/)

### Licencia.

Licencia, especificamente dentro del texto licencia.