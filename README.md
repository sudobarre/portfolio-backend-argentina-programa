<h3 align="center">Portfolio para Argentina Programa</h3>

<div align="center">

  [![Status](https://img.shields.io/badge/status-active-success.svg)]() 

</div>

---

<p align="center"> Backend para el proyecto final de la 2da etapa de Argentina Programa.
    <br> 
</p>

## 📝 Table of Contents
- [About](#about)
- [Getting Started](#getting_started)
- [Deployment](#deployment)
- [Built Using](#built_using)
- [Authors](#authors)
- [Acknowledgments](#acknowledgement)

## 🧐 About <a name = "about"></a>
EL frontend esta disponible <a href="https://github.com/sudobarre/portfolio-frontend-argentina-programa">aca</a>. Para ver el sitio en vivo, visita https://ap-portfolio-frontend-4cfb0.web.app/portfolio

<p>Esta app esta hecha en Spring Boot 3.0.4. Usa un HTTP-Only JWT para mejor seguridad, y tambien tiene un Refresh Token. Este backend lo construi con Docker y lo subi a Github Container Registry, para asi poder hacer el deploy de Koyeb mas facil. El package esta disponible en ghcr.io/sudobarre/portfolio-backend-argentina-programa:latest y esta construido con GraalVM para poder convertirlo en una imagen native. La razon por esto es que las images nativas de Java consumen mucha menos memoria, y son mas rapidas en comparacion a las aplicaciones compiladas de manera "tradicional". Las unicas desventajas son que tardan mucho en crearse, ya que todo se tiene que compilar Ahead Of Time, y que todavia es algo relativamente nuevo, con lo que no hay demasiado soprte/documentacion todavia. Para mas informacion de crear imagenes nativas con SPring Boot y GraalVM, visita <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/native-image.html#native-image.developing-your-first-application">aca</a>. </p>

## 🏁 Getting Started <a name = "getting_started"></a>
Se puede clonar y correr este repo de la manera tradicional, o se puede traer la imagen de Docker del Github Container Registry con el comando ```docker pull ghcr.io/sudobarre/portfolio-backend-argentina-programa:latest ```

Para correr el proyecto se puede hacer ```mvn clean install```, o si se quiere crear la imagen nativa,  ```mvn clean -Pnative native:compile```. Pasar las variables de entorno.
### Prerequisites
<ul>
  <li>Docker</li>
  <li>GraalVM 22.3 JDK para la creacion de imagenes nativas, si se quisiera.  <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/native-image.html#native-image.advanced">Aca</a> para saber mas.</li>
  <li>Spring Boot 3.0.1, o mas tarde. https://start.spring.io/</a></li>
</ul>

### Installing

<a href="https://docs.docker.com/get-docker/">Instalar Docker</a>

## 🚀 Deployment <a name = "deployment"></a>
Hice el deploy en <a href='https://app.koyeb.com/'>Koyeb</a>, eligiendo la opcion de correr la imagen que esta en Github Container Registry, ghcr.io/sudobarre/portfolio-backend-argentina-programa:latest

## ⛏️ Built Using <a name = "built_using"></a>
- [PostgreSQL](https://www.postgresql.org/) - Base De Datos
- [Spring Boot 3.0.1](https://spring.io/) - Server Framework
- [Docker](https://www.docker.com/)

## ✍️ Author <a name = "author"></a>
- [@sudobarre](https://github.com/sudobarre)

## 🎉 Acknowledgements <a name = "acknowledgement"></a>
<ul>
  <li>Este <a href="https://blog.codecentric.de/spring-boot-flyio">blog</a> hecho por <a href="https://github.com/jonashackt">Jonas Hecht</a> que me inspiro a crear una imagen nativa con Java</li>
  <li>HTTP-Only JWT inspirado por <a href="https://github.com/bezkoder/spring-security-refresh-token-jwt">BezKoder</a></li>
  <li>Este README template de <a href="https://github.com/kylelobo/The-Documentation-Compendium">The Documentation Compendium</a>.</li>
</ul>
