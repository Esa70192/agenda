# 📱 Agenda Escolar

Aplicación Android desarrollada en **Java** y conectada a **Firebase**, pensada como una herramienta de apoyo para la organización de actividades escolares.

La aplicación fue diseñada específicamente para la escuela en la que se realizó el proyecto (aunque podria adaptarse para cualquier institucion) con diferentes apartados para administrar tareas, consultar bibliografías y horarios escolares, así como gestionar información del perfil personal del usuario.

## ✨ Características

### 📝 Tareas

El apartado de tareas permite a los estudiantes llevar un registro de las actividades que tienen que realizar.

Cada tarea contiene:

* **Título**
* **Descripción**
* **Fecha de entrega**
* **Fecha de creación**
* **Estado**

Al crear una tarea:

* La **fecha de creación** se guarda automáticamente.
* El estado se establece como **"No finalizado"** por defecto.

El usuario puede:

* Crear nuevas tareas.
* Consultar las tareas creadas.
* Actualizar la información de una tarea.
* Cambiar el estado de una tarea a **"Finalizado"**.
* Eliminar tareas.

Una vez que una tarea cambia al estado **"Finalizado"**, este estado ya no puede modificarse.

### 📚 Bibliografías

Este apartado está pensado para que el **administrador de la aplicación** pueda recomendar libros y material bibliográfico relacionados con las diferentes materias.

La idea es proporcionar a los estudiantes recursos que puedan utilizar como apoyo para sus estudios.

Actualmente, las bibliografías no cuentan con un sistema específico de ordenamiento o clasificación.

### 🕐 Horario

El apartado de horario permite consultar el horario escolar correspondiente al semestre seleccionado.

El administrador puede ingresar los horarios actuales de cada semestre y el usuario puede seleccionar el semestre que desea consultar.

La información del horario incluye:

* **Materia**
* **Horario**
* **Profesor**

De esta manera, el estudiante puede consultar sus clases desde la aplicación.

### 👤 Perfil personal

El apartado de perfil permite al usuario agregar y administrar información personal.

Los datos del perfil pueden ser:

* Consultados por el propio usuario.
* Editados cuando sea necesario.

Originalmente, esta sección formaba parte de una idea más amplia para convertir la aplicación en una especie de **red social escolar**, donde los estudiantes pudieran comunicarse con sus compañeros.

Sin embargo, esta funcionalidad no llegó a implementarse, por lo que actualmente el perfil funciona únicamente como un espacio para consultar y editar la información personal del usuario.

## ☁️ Firebase

La aplicación utiliza **Firebase** para proporcionar la funcionalidad de conexión y almacenamiento de información en línea.

Esto permite que los datos utilizados por la aplicación puedan gestionarse de manera remota en lugar de almacenarse únicamente de forma local en el dispositivo.

## 🛠️ Tecnologías utilizadas

* **Java** — Lenguaje principal de programación.
* **Android** — Plataforma de la aplicación.
* **Android Studio** — Entorno de desarrollo.
* **Firebase** — Servicios de backend y almacenamiento de datos.
* **Gradle** — Sistema de construcción del proyecto.

## 📂 Estructura del proyecto

El proyecto sigue la estructura estándar de una aplicación Android:

```text
agenda/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           ├── res/
│           └── AndroidManifest.xml
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── gradlew
└── gradlew.bat
```

## 🚀 Instalación

### Requisitos

Para ejecutar el proyecto se necesita:

* Android Studio.
* Android SDK.
* JDK compatible con el proyecto.
* Un dispositivo Android o un emulador.
* Configuración de Firebase correspondiente al proyecto.

### Clonar el repositorio

```bash
git clone https://github.com/Esa70192/agenda.git
```

Entrar al directorio:

```bash
cd agenda
```

Abrir el proyecto desde **Android Studio** y esperar a que Gradle sincronice las dependencias.

### Ejecutar

La aplicación puede ejecutarse utilizando:

* Un dispositivo Android conectado mediante USB.
* Un emulador configurado en Android Studio.

Selecciona el dispositivo y ejecuta el proyecto mediante **Run ▶**.

> **Nota:** Debido a que la aplicación utiliza Firebase, es necesario contar con la configuración correspondiente de Firebase para que las funciones que dependen del servicio en línea funcionen correctamente.

## 🎯 Objetivo del proyecto

El proyecto fue creado con el propósito de desarrollar una aplicación orientada a las necesidades de los estudiantes de una institución educativa.

La idea principal era concentrar en una sola aplicación diferentes herramientas relacionadas con la vida escolar:

* Organización de tareas.
* Consulta de horarios.
* Recomendaciones bibliográficas.
* Información personal.
* Una posible comunicación entre estudiantes.

Aunque algunas de las ideas iniciales, como la red social escolar, no fueron implementadas, la aplicación cuenta con los módulos principales para la organización académica.

## 🔮 Posibles mejoras

Entre las funcionalidades que podrían incorporarse en futuras versiones se encuentran:

* Clasificación de bibliografías por materia.
* Búsqueda de libros.
* Mejor organización de los recursos bibliográficos.
* Sistema de comunicación entre estudiantes.
* Perfiles públicos para los usuarios.
* Notificaciones para recordar fechas de entrega.
* Mejoras en la interfaz de usuario.
* Nuevas herramientas para la organización académica.

## 📌 Estado del proyecto

Proyecto desarrollado como práctica de **desarrollo de aplicaciones Android utilizando Java y Firebase**, orientado a la gestión y organización de información escolar.

Algunas de las funcionalidades planteadas originalmente, como la comunicación entre estudiantes, quedaron fuera del alcance de la versión desarrollada.

## 👤 Autor

**Esa70192**

GitHub:
https://github.com/Esa70192/agenda
