# Info2020 🚀
## Etapa III-JAVA 💪

### Proyecto final API Rest con Spring Boot.

### Pre-requisitos 📋
Crear base de datos nombre: **springboot**
- **user**: root
- **pass**: root

```
En la descripción de los endpoint de Postman hay aclaraciones importantes. De igual manera aquí menciono los más importantes.
```

**Consulta**: para crear un comentario.
```
/api/v1/comentario/{post_id}/{usuario_id}
```

**Aclaración**: aquí funciona a medias el endpoint, es decir crea el comentario, pero arroja un status code: 500, esto creo que está relacionado al tipo de inicialización del fetch = FetchType.LAZY en el modelo de comentario, pero no estoy segura.

**Consulta**: Para crear Post lo hice de 2 formas con un endpoint de usuario y un endpoint de post

Crear Post con un Endpoint usuario
```
localhost:8080/api/v1/usuario/{usuario_id}/post
```

Crear Post con un Endpoint post
```
/api/v1/post/{usuario_id}
```
