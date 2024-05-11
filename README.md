# Practica_Simulacion_Halting_Problem
### Enlace al directorio: https://github.com/itsedupablo/Practica_Simulacion_Halting_Problem

### Descripción problema
#### Esta práctica consiste en la creación de un programa en el que se analizan elementos concretos sintácticos con el fin de determinar si otro programa dado (en formato de fichero de texto) contiene un bucle infinito o no.
### Detalles implmentación
#### Paquete *haltingProblem.model*:
Este paquete cuenta con todas las clases involucradas en el modelo de negocio de nuestro programa.
- **CountUp.** Programa que contiene un bucle while y cuya sentencia hace que se incremente un valor de forma indefinida.
  **PSEUDOCÓDIGO COUNTUP**
  ```
  ejecutar():
   entero i = inicio;
     while (i >= 0) 
     i--;
  ```
- **CountDown** Programa que contiene un bucle while y cuya sentencia hace que se decremente un valor hasta un límite dado.
  **PSEUDOCÓDIGO COUNTDOWN**
```
 ejecutar():
  entero i = 0 + 1;
     while (i > 0) 
     i++;
```
En un principio la idea fue que se pudiesen ejecutar ambos programas de forma sencilla, es por eso que se implementó una **clase Programa** de la que extienden estas 2 y donde se crean instancias de CountUp y CountDown a partir de un *factory method*. Además esta clase cuenta con un método abstracto, ejecutar que es heredado por ambos programs y lo implementan según lo necesitan. Más tarde se optó por simplemente copiar el código en 2 ficheros .txt que son más sencillos de analizar
donde también se encuentran:
- **HaltChecker.** Esta clase devuelve un booleano que indica si el programa se va a parar o no. Para ello recibe información de distintos manejadores dentro de una *cadena de responsabilidad* de la que hablaremos después, que básicamente consiste en una serie de valores booleanos almacenados en flags. Si todos contienen el valor *true* se puede deducir que se ha encontrado un bucle infinito.
- **Reverser.** Esta clase recibe el booleano de haltChecker y devuelve el contrario.
  
#### Paquete *haltingProblem.controller*:
Este paquete cuenta con todas las clases involucradas en el funcionamiento interno del programa, que se basa en el patrón de diseño de comportamiento de Cadena de Responsabilidad.
- **Handler**. Interfaz con las funciones básicas que deben implementar cada uno de los handlers y que además permite crear instancias de ella.
**MANEJADOR SINTAXIS**
- **SyntaxAnalysisHandler.** Primer handler de la cadena de responsabilidad, y es un poco diferente porque además de las funciones básicas de cada handler cuenta con un método donde se analiza el código y se convierte a cadena de texto (String).
- **WhileLoopHandler.** Analiza si la palabra reservada *while* se encuentra en el código. Cuenta con un flag booleano que tendrá el valor de true si se ha encontrado la palabra.
- **DefinicionBooleanosHandler.** Analiza si se han definido variables booleanas.
- **DefinicionFlotantesHandler.** Analiza si se han definido variables de coma flotante.
- **DefinicionEnterosHandler.** Analiza si se han definido variables enteras.
- **DecrementoDirectoHandler.** Analiza si se han producido decrementos.
- **DecrementoValorAsignadoHandler.** Analiza si se han producido decrementos según un valor asignado. Por ejemplo (-=2).
- **IncrementoDirectoHandler.** Analiza si se han producido incrementos. Cuenta con un flag booleano que tendrá el valor de true si se ha encontrado la sentencia.
- **IncrementoValorAsignadoHandler.** Analiza si se han producido incrementos según un valor asignado. Por ejemplo (+=2).
- **ComparadorIgualHandler.** Analiza si se ha encontrado un comparador de igualdad.
- **ComparadorDesigualHandler.** Analiza si se ha encontrado un comparador de desigualdad
- **ComparadorMayorHandler.** Analiza si se ha encontrado un comparador mayor que.
- **ComparadorMayorIgualHandler.** Analiza si se ha encontrado un comparador mayor o igual que.Cuenta con un flag booleano que tendrá el valor de true si se ha encontrado la sentencia.
- **ComparadorMenorHandler.** Analiza si se ha encontrado un comparador menor que.
- **ComparadorMenorIgualHandler.** Analiza si se ha encontrado un comparador menor o igual que.

#### Paquete *haltingProblem.view*:
- **App.** Contiene toda la lógica de la cadena de responsabilidad (inicialización de los handlers, orden que han de seguir, etc.) y de la interfaz de usuario (GUI).
  
