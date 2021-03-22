# BUHOLINGO | DESKTOP
Repositorio de la parte "SERVER" del proyecto Buholingo. <br>
Aplicación JAVA hecha con JavaFX encargada de labores administrativas de la BBDD, desde esta aplicación se pueden leer, crear, modificar y eliminar:
 - CURSOS
 - CATEGORIAS
 - NIVELES
 - EJERCICIOS
 - ITEMS

## SPECS:
- MVC JavaFX
- Implementación de patron de diseño DAO
- Conexion con APP mediante ServerSocket
- Des/Habilitación de servidor con interruptor


## FUNCIONALIDADES:
- Filtraje de CURSOS mediante IDIOMAS
- Creación de CURSOS, CATEGORÍAS y NIVELES
- Creación de EJERCICIOS con layout adaptativo
- Posibilidad de visualización demo de los ejercicios de una categoría
- Creación, modificacion y eliminación de ITEMS 
- Control de estado SERVER.

## FAQ:
**VM ARGUMENTS JAVAFX:** 
```bash
--module-path src/main/resources/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml
```

## IMAGENES:
![MainFrame](https://i.ibb.co/Ry3dcmh/DuoDesk.png)
![CrearEjercicio](https://i.ibb.co/wQz2JZr/image.png)
![AddItem](https://i.ibb.co/KV0Yp8V/image.png)
