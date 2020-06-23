![línea horizontal](.//media/image52.png)

**Imposoft - Grupo 5**

Arturo Sánchez Díaz-Güemes

Noemi Almohano Vidick

Memoria de TWINS

Documentación sobre el proyecto TWINS desarrollado por el grupo
Imposoft. Se incluye información sobre los patrones de diseño
implementados, pruebas unitarias creadas para asegurar el correcto
funcionamiento de la app, y algunas muestras del refactoring aplicado.

**[Arquitectura de la aplicación:](#arquitectura-de-la-aplicación) 2**

**[PATRONES APLICADOS](#patrones-aplicados) 3**

> [Patrón AAA (Arrange, Act, Assert)](#patrón-aaa-arrange-act-assert) 3
> 
> [Patrón Builder](#patrón-builder) 4
> 
> [Patrón Estrategia](#patrón-estrategia) 5
> 
> [Patrón Singleton](#patrón-singleton) 7
> 
> [Patrón Plantilla](#patrón-plantilla) 9
> 
> [Patrón Decorador](#patrón-decorador) 11
> 
> [Patrón Sirviente](#patrón-sirviente) 13

**[PRUEBAS UNITARIAS PARCIALES](#pruebas-unitarias-parciales) 14**

> [Prueba de la funcionalidad de las
> cartas](#prueba-de-la-funcionalidad-de-las-cartas) 14
> 
> [Prueba de la funcionalidad del
> sonido](#prueba-de-la-funcionalidad-del-sonido) 17
> 
> [Prueba de funcionamiento correcto de la
> música](#prueba-de-funcionamiento-correcto-de-la-música) 17
> 
> [Prueba del sistema de
> puntuaciones](#prueba-del-sistema-de-puntuaciones) 19
> 
> [Pruebas para cartas de tipo
> normal](#pruebas-para-cartas-de-tipo-normal) 19
> 
> [Pruebas para cartas de tipo
> especial](#pruebas-para-cartas-de-tipo-especial) 19

**[REFACTORIZACIÓN](#refactorización) 21**

> [Extract method](#extract-method) 21
> 
> [Inline method](#inline-method) 23
> 
> [Extract class](#extract-class) 23
> 
> [Self encapsulate field](#self-encapsulate-field) 24
> 
> [Replace Magic Number with Symbolic
> Constant](#replace-magic-number-with-symbolic-constant) 24
> 
> [Decompose conditional](#decompose-conditional) 25
> 
> [Consolidate Duplicate Conditional
> Fragments](#consolidate-duplicate-conditional-fragments) 25
> 
> [Rename Method](#rename-method) 26
> 
> [Pull Up Constructor Body](#pull-up-constructor-body) 27

**[Bibliografía](#bibliografía) 28**

> [Información utilizada:](#información-utilizada) 28

# Arquitectura de la aplicación:

La aplicación se ha divido en 3 capas, **Vista**, donde se encuentran
todas las actividades de Android que se encargan de manejar la entrada
de datos del usuario. Por otra parte tenemos la **Lógica**, que son
todos los objetos que a partir de la entrada de datos realizan una serie
de funciones y actualizan la vista posteriormente. Y por último tenemos
la **Persistencia**, que es la capa encargada de almacenar los datos de
la aplicación y también de cargar estos datos.

Al estar trabajando en un proyecto planteado para 4 personas entre tan
solo 2 integrantes, manejar esta estructura de capas ha sido complicado,
teniendo en cuenta los plazos indicados por el product owner de TWINS.

![](.//media/image29.png)

#   

# PATRONES APLICADOS

## Patrón AAA (Arrange, Act, Assert)

Este patrón (también conocido como Given-When-Then) es utilizado en las
pruebas unitarias del juego, se basa en organizar los test unitarios en
3 fases diferenciadas, **Arrange**, donde se hace las preparaciones e
inicializaciones necesarias para la prueba, **Act**, donde hacemos las
acciones necesarias para la prueba y **Assert**, donde comprobamos la
salida de la prueba.

Un ejemplo de código sería el siguiente:

|                           |
| ------------------------- |
| ![](.//media/image15.png) |

Uno de los beneficios que sacamos de este patrón es mantener un código
más fácil de leer, mantener y mejorar en un futuro si modifica el
código que se somete a la prueba.

## 

## 

## 

## 

## 

## 

## 

## Patrón Builder

Este patrón es utilizado para dividir la creación de niveles en
sencillos pasos y así facilitar la creación de las distintas partidas y
su futura modificación.

Diagrama UML del patrón:

![](.//media/image35.png)

Este builder está simplificando la creación de niveles, para que aquella
persona que los configure no tenga que saber del funcionamiento interno
de la aplicación para hacerlo y meramente tenga que jugar con los
parámetros ya existentes. Un ejemplo de esta mejora de vida que ofrece
el patrón es la cantidad total de cartas (*deckSize*), que dicta la
escena que tendremos que cargar y futuros cálculos de repartición de
cartas, así pues alguien que diseñe niveles tan solo sabrá que existen
tres tamaños, sin tener que saber las cantidades de cartas necesarias
para la creación del objeto de la clase *Game*.

<table>
<tbody>
<tr class="odd">
<td><img src=".//media/image17.png" style="width:5.93229in;height:2.17059in" /><br />
<em>Ejemplo del código de uno de los pasos de la construcción de la clase Game.</em></td>
</tr>
</tbody>
</table>

El patrón sufrió un cambio puesto que la mayoría de métodos que teníamos
en esta clase hacían el código igual de largo y no simplificaban mucho
la tarea. Nuestra forma de solucionarlo fue organizando mejor los
métodos, agrupándolos en distintos pasos, y así hacer más sencilla la
creación de cada partida y facilitar sus futuros cambios, puesto que en
un futuro puede existir la necesidad de modificar la clase que define la
lógica de la partida y que aparezcan nuevos parámetros y por lo tanto
toque modificar más de una clase para aplicarlos correctamente.

## Patrón Estrategia

El patrón estrategia facilita la creación de las cartas de juego
dependiendo del tipo de partida que estemos jugando, pues lo único que
hay que hacer es, dependiendo de la estrategia a aplicar, elegir una de
las clases que implementan la interfaz Dealer y ejecutar su método
**assignCardTheme(***parámetros***)** que aplica un algoritmo distinto
según nuestras necesidades en el momento.

![](.//media/image23.png)

Ejemplo de código del patrón:

<table>
<tbody>
<tr class="odd">
<td><p><img src=".//media/image46.png" style="width:4.77604in;height:3.69671in" /><br />
<em>Ejemplo de código de la clase DealSpecialDecks, usada para las partidas en las que se permite el uso de cartas especiales.</em></p>
<p><img src=".//media/image49.png" style="width:4.61458in;height:0.96875in" /><br />
<em>Ejemplo de código de la clase DealOneDeck, empleada en las partidas en las que tan solo se juega con una baraja.</em></p>
<p><img src=".//media/image55.png" style="width:6.29167in;height:1.81944in" /><br />
<em>Empleo del patrón estrategia en la clase Deck.</em></p></td>
</tr>
</tbody>
</table>

Emplear este patrón nos sirve para que, con una misma llamada dentro del
GameActivity, se nos asigne y cree el tablero de juego en función de las
especificaciones de las distintas partidas, como es el hecho de que se
puedan emplear cartas especiales o no, o que se juegue con dos barajas
distintas al mismo tiempo.

## Patrón Singleton

Aplicar este patrón nos ayuda a que tanto la música de fondo como los
sonidos extras que se puedan dar tengan una única instancia accesible,
evitando así crear múltiples instancias que darían lugar a errores de
reproducción o a fallos a la hora de elegir en las opciones si queremos
que se reproduzca sonido o no.

![](.//media/image40.png)

Ejemplo de código del patrón:

<table>
<tbody>
<tr class="odd">
<td><p><img src=".//media/image7.png" style="width:4.89583in;height:2.83333in" /><br />
<em>Ejemplo de código de la clase MusicService.</em></p>
<p><img src=".//media/image43.png" style="width:4.0625in;height:1.90625in" /><br />
<em>Código de la clase BackgroundMusicActivity.</em></p></td>
</tr>
</tbody>
</table>

A la hora de manejar todo lo relacionado con la música y los sonidos de
la aplicación, utilizando este patrón se simplifica en gran manera la
forma de hacerlo, pues apenas con un par de llamadas en el código
podemos conseguir la instancia del elemento sin tener que crear otro
nuevo y producir así sonidos duplicados o no poder parar la música
cuando lo necesitamos.

## 

## 

## 

## 

## Patrón Plantilla

El patrón también es conocido como Prototype.

Hemos decidido aplicar este patrón en nuestra aplicación para poder
simplificar el cálculo de las puntuaciones de los distintos modos de
juego en una misma llamada.

![](.//media/image39.png)

Ejemplo de código del patrón:

<table>
<tbody>
<tr class="odd">
<td><p><img src=".//media/image54.png" style="width:4.21354in;height:2.26042in" /><br />
<em>Ejemplo de código de la clase AbstractScore.</em></p>
<p><img src=".//media/image1.png" style="width:3.25in;height:2.35417in" /><br />
<em>Código de la clase ScoreCasual.</em></p>
<p><img src=".//media/image13.png" style="width:3.89583in;height:2.86458in" /><br />
<em>Código de la clase GameActivity perteneciente al selector del ScoreManager en función del tipo de juego.</em></p></td>
</tr>
</tbody>
</table>

En un principio, tuvimos ciertos problemas a la hora de entender el
patrón y aplicarlo correctamente, pues se reescribían métodos que
debían estar únicamente creados en la clase AbstractScore, esto se
subsano eliminando estos métodos de las clases donde no eran necesarios
para evitar duplicación del código y un correcto funcionamiento.

El beneficio que nos proporciona es que, como elegimos un ScoreManager
en función del tipo de juego que inicializamos, a la hora de calcular la
puntuación se tiene en cuenta ese ScoreManager, que es quien llama al
método ***updateScore(...)*** que todos los managers heredan de la
clase abstracta, y en función de este se realiza uno u otro de los
***calculateAndSetScore()***, sin tener que hacer nosotros nada de forma
directa.

## 

## Patrón Decorador

Este patrón ha sido empleado para poder hacer la asignación de las
cartas, el hecho de si son normales o especiales, de manera más
sencilla. De esta forma, también se facilita el uso de las distintas
características de las cartas, ya sean normales o especial, pues
trabajamos con una instancia de la carta con el decorador ya aplicado.

![](.//media/image6.png)

Ejemplo de código del patrón:

<table>
<tbody>
<tr class="odd">
<td><p><img src=".//media/image2.png" style="width:5.79787in;height:1.70313in" /><em>Ejemplo de código de la clase ConcreteCard, que implementa Card y crea la carta.</em></p>
<p><img src=".//media/image5.png" style="width:5in;height:1.40278in" /><br />
<em>Código de la clase SpecialCardDecorator1, que extiende CardDecorator y modifica, en nuestro caso, la puntuación que se obtiene por carta</em></p></td>
</tr>
</tbody>
</table>

La ventaja de aplicar este patrón es que, como se trabaja en función de
las cartas a la hora de obtener las puntuaciones, y este patrón se
aplica a las cartas a la hora de crearlas, cuando se hace la función
***updateScore(...)***, el valor points que se recoge de la carta ya
esta actualizado en función de si la carta es normal o especial, por lo
tanto no hay que hacer realmente ninguna comprobación extra para saber
si esa carta debería o no asignar más puntos a la hora de hacer el
cálculo, facilitando la operación y reduciendo la cantidad de código
necesaria en comparación a si no estuviese el patrón aplicado.

## 

## 

## 

## 

## 

## 

## Patrón Sirviente

Este patrón se utiliza a la hora de repartir cartas, es un método
auxiliar que ejecutan todas las estrategias del patrón explicado
anteriormente para poder sacar una lista de números aleatorios según el
total de cartas a repartir y el máximo de cartas que existen. Como el
código era el mismo en las tres clases hemos extraído este método a una
clase sirviente, para poder tenerlo unificado y evitar así duplicación
de código y facilitar cambios futuros.

La estructura del patrón sería la siguiente:

![](.//media/image19.png)

El código es muy simple, se trata de una clase con un método estático
randomList que se ejecuta desde cada una de las estrategias cuando se
necesita, el código es el siguiente:

|                           |
| ------------------------- |
| ![](.//media/image36.png) |

#   

# PRUEBAS UNITARIAS PARCIALES

En este apartado hemos decidido testear tres partes del código
cruciales, que son el sistema de cartas (y su emparejación), el sistema
de puntuaciones y el sistema de sonido. Estas pruebas han sido llevadas
a cabo siguiendo el patrón AAA.

## Prueba de la funcionalidad de las cartas

Una parte importante de la aplicación son las cartas, para comparar las
cartas se usa un entero que identifica al bitmap en la carpeta de
recursos de Android. La comparación de estas cartas se hace
posteriormente comparando estos id, por lo que para nuestros tests
revisaremos que dadas dos cartas con el mismo nombre, estas sean pareja,
y si no tienen el mismo nombre no sean pareja.

<table>
<tbody>
<tr class="odd">
<td><p><img src=".//media/image56.png" style="width:6.29167in;height:2.20833in" /></p>
<p><em>Comprobamos que dadas dos cartas con el mismo bitmap, estas son pareja.</em></p>
<p><img src=".//media/image20.png" style="width:6.29167in;height:2.15278in" /></p>
<p><em>Comprobamos que dadas dos cartas con distinto bitmap, estas no son pareja.</em></p>
<p><img src=".//media/image10.png" style="width:6.29167in;height:2.48611in" /></p>
<p><em>Test realizados sobre la misma funcionalidad en las cartas especiales.</em></p></td>
</tr>
</tbody>
</table>

También se comprueba que las cartas decoradas funcionan como deseamos,
haciendo los cálculos de puntos por carta correctamente.

<table>
<tbody>
<tr class="odd">
<td><p><img src=".//media/image22.png" style="width:6.29167in;height:1.54167in" /><br />
<em>Test sobre la carta especial 1.</em></p>
<p><img src=".//media/image18.png" style="width:6.29167in;height:1.51389in" /><em><br />
Test sobre la carta especial 2.</em></p>
<p><img src=".//media/image34.png" style="width:6.29167in;height:1.52778in" /></p>
<p><em>Test sobre la carta especial 3.</em></p></td>
</tr>
</tbody>
</table>

Resultados de los tests:

<table>
<tbody>
<tr class="odd">
<td><p><img src=".//media/image21.png" style="width:4.75in;height:2.34375in" /></p>
<p><em>Resultados sobre las cartas normales.</em></p>
<p><img src=".//media/image45.png" style="width:4.76042in;height:3.40625in" /></p>
<p><em>Resultados sobre las cartas especiales.</em></p></td>
</tr>
</tbody>
</table>

## 

## 

## 

## 

## Prueba de la funcionalidad del sonido

Para estas pruebas hemos utilizado las pruebas instrumentales de
Android, que nos permiten acceder a recursos propios de Android que
existirían en tiempo ejecución para facilitar las pruebas.

Antes de cada prueba pararemos toda la música para simular una situación
normal.

![](.//media/image8.png)

### Prueba de funcionamiento correcto de la música

En esta parte comprobamos si la música carga como debería, probando a
activarla en una situación normal, justo después de crear el
reproductor, otra prueba donde revisamos que la música no carga si la
hemos desactivado y por último una prueba para comprobar que la música
se puede reactivar correctamente tras haberla desactivado.

<table>
<tbody>
<tr class="odd">
<td><p><img src=".//media/image44.png" style="width:6.29167in;height:2.20833in" /></p>
<p><em>Comprobación de si la música carga correctamente al inicio.</em></p>
<p><img src=".//media/image30.png" style="width:6.29167in;height:2.23611in" /><br />
<em>Comprobación de si la música no carga cuando está desactivada.</em></p>
<p><img src=".//media/image31.png" style="width:6.29167in;height:2.375in" /></p>
<p><em>Comprobación de si la música carga después de volver a activarla.</em></p></td>
</tr>
</tbody>
</table>

Resultado de los tests:

|                           |
| ------------------------- |
| ![](.//media/image25.png) |

## 

## 

## Prueba del sistema de puntuaciones

Las pruebas se han realizado sobre el método que se emplea para hacer el
cálculo de las puntuaciones en los distintos tipos de
partida(***updateScore(...)***). Se han realizado en función de los
distintos tipos de carta existentes en el juego.

### Pruebas para cartas de tipo normal 

Las cartas de tipo normal se emplean en todos los tipos de juego, sin
embargo solo el Estándar, Casual y Por Niveles hacen cálculo de la
puntuación, con lo que hacemos el testeo de esos tres tipos de
scoreManagers.

|                           |
| ------------------------- |
| ![](.//media/image48.png) |

### Pruebas para cartas de tipo especial

Las cartas de tipo especial solo se pueden usar en partidas de tipo
Estándar o Por Niveles, por lo tanto solo es necesario hacer el testeo
del cálculo de las puntuaciones con estos dos tipos de scoreManager.

**SpecialCardDecorator1:**

|                           |
| ------------------------- |
| ![](.//media/image32.png) |

**SpecialCardDecorator2:**

|                           |
| ------------------------- |
| ![](.//media/image27.png) |

**SpecialCardDecorator3:**

|                           |
| ------------------------- |
| ![](.//media/image16.png) |

Como resultado hemos obtenido que todos los test han conseguido
finalizar la ejecución con éxito:

|                           |
| ------------------------- |
| ![](.//media/image26.png) |

# REFACTORIZACIÓN

## Extract method

Este tipo de refactoring implica que todo aquello que se pueda sacar de
un método para hacerlo más simple, debe sacarse. En nuestro caso, el
extract method lo hemos realizado en bastantes métodos en diversas
clases, consiguiendo así métodos más simples y legibles.

El siguiente es uno de los muchos ejemplos de aplicación:

|                                                                                                                 |
| --------------------------------------------------------------------------------------------------------------- |
| ![](.//media/image38.png)M*étodo onCreate antes de haber sido aplicada la refactorización, clase GameActivity.* |

<table>
<tbody>
<tr class="odd">
<td><p><img src=".//media/image33.png" style="width:6.29167in;height:2.29167in" /><em>Método onCreate tras aplicar la refactorización, clase GameActivity.</em></p>
<p><img src=".//media/image14.png" style="width:4.10417in;height:4.5625in" /><br />
<em>Método extraído de onCreate, clase GameActivity.</em></p></td>
</tr>
</tbody>
</table>

## 

## Inline method

Este refactoring se aplica para simplificar todos aquellos métodos que,
con una única línea de código, puedan realizarse de manera correcta.
También ha sido aplicado de manera continuada en la aplicación.

Aquí un ejemplo de aplicación del refactoring:

<table>
<tbody>
<tr class="odd">
<td><p><img src=".//media/image11.png" style="width:5.98958in;height:1.29167in" />M<em>étodo antes de aplicarle la refactorización, en la clase Deck.</em></p>
<p><img src=".//media/image50.png" style="width:6.29167in;height:0.56944in" /><em>Método inLine en la clase Deck.</em></p></td>
</tr>
</tbody>
</table>

## Extract class

Este refactoring es empleado para eliminar carga de una misma clase,
portandola a una segunda clase que realice las funciones. A lo largo del
desarrollo de la aplicación hemos empleado esta refactorización en
varias ocasiones.

Uno de los posibles ejemplos sería el caso de las clases MainActivity y
GameActivity. Al inicio del desarrollo de la app, esta segunda clase no
existía, con lo que todas las funcionalidades de lo que era la partida
se realizaban siempre en la clase MainActivity, después decidimos crear
una única clase que se encargará de la funcionalidad de la partida
(GameActivity), dejando que la clase MainActivity únicamente realice las
funciones de inicio de la aplicación.

## 

## 

## 

## 

## Self encapsulate field

Este refactoring se utiliza para, en lugar de acceder directamente a la
variable privada, acceder a una instancia de la misma mediante el empleo
de getters y setters.

Un ejemplo de uso del refactoring:

<table>
<tbody>
<tr class="odd">
<td><p><img src=".//media/image53.png" style="width:5.90625in;height:0.75in" /><em>Métodos set implementados en la clase ConcreteCard.</em></p>
<p><img src=".//media/image4.png" style="width:6.29167in;height:1.125in" /><em>La encapsulación se realiza en el método setFrontImage y setBackImage(de la clase Card) en clase DealOneDeck.</em></p></td>
</tr>
</tbody>
</table>

## Replace Magic Number with Symbolic Constant

Este tipo de refactoring se aplica para que, en lugar de emplear un
número que tiene un valor simbólico, se emplea una variable cuyo nombre
está asociado a ese valor simbólico.

|                                                            |
| ---------------------------------------------------------- |
| ![](.//media/image24.png)*Imagen del refactoring aplicado* |

En nuestro caso, un ejemplo de empleo es, en lugar de utilizar el entero
12 sin más, como por convenio del diseño sabemos que la partida no va a
tener más de 12 diseños de carta simultáneos, emplearemos ese valor
máximo(***MAX\_CARD\_DESIGNS***) para poder crear la lista de
posiciones aleatorias.

## Decompose conditional

En lugar de hacer varias comprobaciones, obteniendo asi un metodo
if-else / switch largo y complejo, se simplifica de manera sustancial la
comprobación. En nuestro caso, un ejemplo sería el siguiente:

En lugar de ir comprobando uno por uno los distintos modos de juego,
aplicandoles los cambios que queremos, hemos optado por hacer la
comprobación de cuál es el modo de juego que no funciona igual al resto
en este caso y, si comprueba que no es ese, que se realice todo lo
incluido dentro del if.

|                                                                                       |
| ------------------------------------------------------------------------------------- |
| ![](.//media/image9.png)*Aplicación del refactoring dentro de la clase GameActivity.* |

## Consolidate Duplicate Conditional Fragments

Este tipo de refactorización se emplea para que, en las condiciones en
las que hay una llamada que se realiza en todos los casos, en lugar de
que aparezca repetidamente dentro del código, lo que hacemos es ponerla
una única vez tras hacer las comprobaciones.  
En nuestro ejemplo de código, esto sucede así en nuestra llamada
refresh(), pues en lugar de hacerla tanto dentro del if como del else,
directamente la hacemos después de que se realicen las comprobaciones y
el código asociado a las mismas.

|                           |
| ------------------------- |
| ![](.//media/image42.png) |

|                                                                                                       |
| ----------------------------------------------------------------------------------------------------- |
| ![](.//media/image12.png)*Refactorización aplicada en la clase MAinActivity, en el método refresh().* |

## Rename Method

Renombrar un método nos sirve para hacerlo más descriptivo a la hora de
entender cuál es su funcionalidad. A lo largo del desarrollo de la
aplicación nos ha tocado hacer refactoring de muchos de los nombres de
los métodos para clarificar su propósito.

Este es un ejemplo de ello:

<table>
<tbody>
<tr class="odd">
<td><p><img src=".//media/image28.png" style="width:6.13293in;height:0.65799in" /><em>Código antes de haber aplicado la refactorización</em></p>
<p><img src=".//media/image3.png" style="width:6.09703in;height:0.67882in" /><em>Código tras haber aplicado la refactorización</em></p></td>
</tr>
</tbody>
</table>

## 

## Pull Up Constructor Body

Este refactoring se emplea para que en lugar de hacer que las subclases
tengan un código idéntico o prácticamente idéntico al de la clase padre,
todos los métodos que vayan a ser similares se crean en la superclase, y
de ser necesarios, son llamados desde la subclase.

Ejemplo de aplicación de la refactorización, la clase
SpecialCardDecorator1 en lugar de sobreescribir los métodos, llama a los
que necesite de CardDecorator, su superclase:

|                                                                                          |
| ---------------------------------------------------------------------------------------- |
| ![](.//media/image37.png)*Aplicación del refactoring en la clase SpecialCardDecorator1.* |

## 

# 

# Bibliografía

## Información utilizada:

  - > Patrón AAA:
    > [<span class="underline">https://martinfowler.com/bliki/GivenWhenThen.html</span>](https://martinfowler.com/bliki/GivenWhenThen.html)

  - > Refactoring:
    > [<span class="underline">https://link.springer.com/content/pdf/bbm%3A978-1-4302-0725-2%2F1.pdf</span>](https://link.springer.com/content/pdf/bbm%3A978-1-4302-0725-2%2F1.pdf)

  - > Refactoring:
    > [<span class="underline">https://refactoring.guru/</span>](https://refactoring.guru/)

  - > Guia de patrones:
    > [<span class="underline">https://java-design-patterns.com/</span>](https://java-design-patterns.com/)

  - > Guia de patrones:
    > [<span class="underline">https://refactoring.guru/</span>](https://refactoring.guru/)
