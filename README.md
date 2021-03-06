# ScotiabankMovies

Aplicaci贸n donde se muestran las peliculas segun la categoria y su respectivo detalle.

## Arquitectura 馃殌

la aplicaci贸n se estructura con DDD(Dise帽o guiado por el dominio) la cual se encarga de estructurar de la mejor manera la logica del negocio, ademas se implementa 
MVVM(Modelo-Vista-Modelo de vista) esta se encarga de desacoplar lo mas posible la logica de la vista con la de la aplicaci贸n.

## Librerias

* Dagger hilt
* Retrofit
* Corroutines
* LiveData
* ViewModel
* Room
 
## Modulos

### App (Vistas)

Este se encarga de la interfaz grafica la cual tiene  interacci贸n el usuario, en este modulo encontramos el patron de dise帽o MVVM.

#### Clases:

* App: clase principal de la aplicaci贸n, esta nos permite hacer la inyecci贸n de dependencias para todas las clases. 
* PageViewModel: es encargado de hacer la logica de la interfaz grafica y notificar al MovieFragment las acciones respectivas.
* MovieFragment: se encarga de pintar la informaci贸n segun lo que notifique el view model, este proceso lo hace por medio de observadores y es el encargado
del redireccionamiento al detalle de la pelicula.
* DashboardActivity: contiene el viewpager y el toolbar designado para los tabs, ademas maneja la acci贸n de buscar por cada fragmento.
* MovieDetailActivity: muestra la informaci贸n detallada de cada pelicula.
* SplashActiviy: este muestra un splash dando una presentaci贸n de la app.
* MovieFragmentView: es la que permite la comunicaci贸n entre la actividad y los fragmentos para realizar la acci贸n de busqueda.
* SectionsPagerAdapter: esta clase se encarga de crear los fragmentos respectivos para el viewPager.
* MovieRecyclerAdapter: esta clase se encarga de pintar la lista y hacer el respectivo filtro cuando se ejecute la acci贸n de busqueda.

### Application (Regla de negocio)

Este modulo se encarga de manejar las reglas del negocio

#### Clases:

* UseCaseModule: se encarga de crear la instancia para dagger, la cual permite la inyecci贸n de dependencias en las respectivas clases de este modulo.
* GetMoviesUseCase: se encarga de validar si la categoria obtenida del MovieFactory es valida, crea el withContext de la corrutina y retornar las peliculas pedidas por el viewModel. 
* MovieFactory: la responsabilidad de esta clase es mapear si la posici贸n solicitada se encuentra y devolver la respectiva categoria,
o la excepci贸n si no se encuentra la categoria.
* Iterator: Interfaz la cual muestra un comportamiento usual en los casos de uso. 
* SaveMoviesUseCase: se encarga de validar si la categoria obtenida del MovieFactory es valida, crea el withContext de la corrutina y guardar las peliculas. deseadas.

### Domain (Logica de negocio)

Este se encarga de manejar la logica de negocio.

#### Clases:

* MovieRequest: es encargado de la informaci贸n que es pre-requisito para consumir ya sea el servicio web o la base de datos local.
* MovieResponse: es encargado de almacenar la informaci贸n obtenida del servicio web o la base de datos local.
* Movie : Entidad perteneciente al MovieResponse para el almacenamiento de informaci贸n.
* ServiceModule : se encarga de crear la instancia para dagger, la cual permite la inyecci贸n de dependencias en las respectivas clases de este modulo.
* MovieService : encargado de la logica de negocio, tanto el obtener o guardar peliculas.

Nota: La carpeta testDataBuilder es para las pruebas unitarias para la creaci贸n de las instancias de los modelos con el patron testBuilder.


### DataAccess (Capa de persistencia y red)

Se encarga de consumir informaci贸n ya sea una base de datos local o un servicio web.

#### Clases:

* MovieTranslator: clase encargada de convertir la informacion de la base de datos a un objeto de uso general en la app y viceversa.
* MovieDao: en esta interfaz se encuentran los querys con respecto a la base de datos especificamente en la tabla moviesDB.
* AppDatabase: se encarga de crear la base de datos con las respectivas tablas.
* MovieDB: la tabla donde se va a guardar la informaci贸n de las peliculas.
* DatabaseModule: se encarga de crear la instancia de la base datos, para que dagger pueda hacer posible la inyecci贸n.
* NetworkModule: se encarga de crear la instancia de retrofit, para que dagger pueda hacer posible la inyecci贸n.
* RepositoryModule: se encarga de crear la instancia del repositorio, para que dagger pueda hacer posible la inyecci贸n.
* MovieService: esta se encarga de completar la url con los datos respectivos y su tipo para el consumo del servicio REST.
* MovieRepositoryImpl: este es el repositorio encargado de la obtenci贸n o guardado de la informaci贸n.

### Utilities

En este modulo podemos encontrar los  archivos que nos pueden ayudar en las distintas situaciones como los archivos de conversion, base,etc.


#### Clases:

* BaseFragment: tiene implementaciones de uso general para los fragmentos en este caso alerta y progress.
* Constants: archivo donde se encuentran datos estaticos como la url de la app, mensajes de la app, numeros entre otras cosas.
* Images: clase para el cargado de imagenes con glide.
* RequestImage: clase de pre-requisito para el uso de cargado de imagenes con glide.
