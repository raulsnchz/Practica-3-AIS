# Practica-3-AIS

Nombre de los alumnos: Raul Sanchez Benitez y Andres Muñoz Muñoz.



## Ejemplo 1

**INPUT y OUTPUT**: "1" -> "1"

**EJ1. Código de test**
```java
@Test
public void test1() {
    CalculatorParser calculator = new CalculatorParser();
    assertTrue(calculator.parse("1") == 1);
}
```

**EJ1. Mensaje del test añadido que NO PASA**

```log
java.lang.UnsupportedOperationException: Not implemented yet
```

**EJ1. Código mínimo para que el test pase**

Hemos hecho que devuelva 1 para que pase el test.

```java
public int parse(String expression) {
    return 1;
}
```

**EJ1. Captura de que TODOS los test PASAN**

![Pasa](Capturas/test1.png "Pasa")


**EJ1. Refactorización**
> No es necesaria.



<br>

## Ejemplo 2

**INPUT y OUTPUT**: "2" -> "2"

**EJ2. Código de test**
```java
@Test
public void test2(){
    CalculatorParser calculator = new CalculatorParser();
    assertEquals(calculator.parse("2"), 2);
}
```

**EJ2. Mensaje del test añadido que NO PASA**

```log
org.opentest4j.AssertionFailedError: expected: [1] but was: [2]
```

**EJ2. Código mínimo para que el test pase**

Ahora tenemos que cambiar el código para que pase los dos tests.

```java
public int parse(String expression) {
    if (expression == "1"){
        return 1;
    }else {
        return 2;
    }
}
```

**EJ2. Captura de que TODOS los test PASAN**

![Pasa](Capturas/test2.png "Pasa")

**EJ2. Refactorización**
> No es necesaria.


<br>


## Ejemplo 3

**INPUT y OUTPUT**: "3" -> "3"

**EJ3. Código de test**
```java
@Test
public void test3(){
    CalculatorParser calculator = new CalculatorParser();
    assertEquals(calculator.parse("3"), 3);
}
```

**EJ3. Mensaje del test añadido que NO PASA**

Ahora tenemos que cambiar el código para que pase los 3 tests.

**EJ3. Código mínimo para que el test pase**

Es el mismo código que en el test anterior.

```java
public int parse(String expression) {
    if (expression == "1"){
        return 1;
    }else if (expression == "2"){
        return 2;
    }else{
        return 3;
    }
}
```

**EJ3. Captura de que TODOS los test PASAN**

![Pasa](Capturas/test3.png "Pasa")

**EJ3. Refactorización**
> Como esta es la tercera vez que se duplica codigo, vamos a refactorizar el metodo para eliminar el codigo duplicado, al igual que los tests cambiandolos a un test parametrizado.


```java
public int parse(String expression) {
    return Integer.parseInt(expression);
}
```
```java
@ParameterizedTest
@ValueSource(strings = {"1", "2", "3"})
public void testParse(String input) {
    CalculatorParser calculator = new CalculatorParser();
    assertEquals(Integer.parseInt(input), calculator.parse(input));
}
```

**EJ3. Captura de que TODOS los tests PASAN tras la refactorización**

![Pasa](Capturas/test1to3.png "Pasa")



<br>

## Ejemplo 4

**INPUT y OUTPUT**: "1+1" -> "2"

**EJ4. Código de test**
```java
@Test
public void test4(){
    CalculatorParser calculator = new CalculatorParser();
    assertEquals(calculator.parse("1+1"), 2);
}
```

**EJ4. Mensaje del test añadido que NO PASA**

```log
java.lang.NumberFormatException: For input string: "1+1"
```

**EJ4. Código mínimo para que el test pase**

Se añade un if con el "1+1".

```java
public int parse(String expression) {
    if (expression == "1+1") {
        return 2;
    } else {
        return Integer.parseInt(expression);
    }
}
```

**EJ4. Captura de que TODOS los test PASAN**

![Pasa](Capturas/test4.png "Pasa")

**EJ4. Refactorización**
> No es necesaria.



<br>

## Ejemplo 5

**INPUT y OUTPUT**: "2+3" -> "5"

**EJ5. Código de test**
```java
@Test
public void test5(){
    CalculatorParser calculator = new CalculatorParser();
    assertEquals(calculator.parse("2+3"), 5);
}
```

**EJ5. Mensaje del test añadido que NO PASA**

```log
java.lang.NumberFormatException: For input string: "2+3"
```

**EJ5. Código mínimo para que el test pase**

Se añade otro if con el "2+3".
```java
public int parse(String expression) {
    if (expression == "1+1") {
        return 2;
    } else if (expression == "2+3") {
        return 5;
    } else {
        return Integer.parseInt(expression);
    }
}
```

**EJ5. Captura de que TODOS los test PASAN**

![Pasa](Capturas/test5.png "Pasa")

**EJ5. Refactorización**
> Como solo hemos duplicado codigo 2 veces vamos a esperar a ver si duplicamos otra vez para refactorizar aplicando la regla del 3 del codigo duplicado. 


<br>


## Ejemplo 6

**INPUT y OUTPUT**: "2+3+4" -> "9"

**EJ6. Código de test**
```java
@Test
public void test6(){
    CalculatorParser calculator = new CalculatorParser();
    assertEquals(calculator.parse("2+3+4"), 9);
}
```

**EJ6. Mensaje del test añadido que NO PASA**

```log
java.lang.NumberFormatException: For input string: "2+3+4"
```

**EJ6. Código mínimo para que el test pase**

Añadimos otro if para el nuevo caso "2+3+4"

```java
public int parse(String expression) {
    if (expression == "1+1") {
        return 2;
    } else if (expression == "2+3") {
        return 5;
    } else if (expression == "2+3+4") {
        return 9;
    } else {
        return Integer.parseInt(expression);
    }
}
```

**EJ6. Captura de que TODOS los test PASAN**

![Pasa](Capturas/test6.png "Pasa")

**EJ6. Refactorización**
> Como ya hemos duplicado codigo 3 veces, procedemos a refactorizar el método, extrayendo el código duplicado y añadiendo la funcionalidad de sumar. En cuanto a los test, vamos a meter un "@beforeEach" y vamos a crear un test parametrizado para los casos de uso de las sumas.

```java
public int parse(String expression) {
    String[] tokens = expression.split("\\+");
    int result = 0;
    for (String token : tokens) {
        result += Integer.parseInt(token.trim());
    }
    return result;
}
```

```java
@ParameterizedTest
@CsvSource({"1+1, 2" , "2+3, 5" , "2+3+4, 9"})
public void test4to6(String expression, int expected) {
    assertEquals(expected, calculator.parse(expression));
}
```
**EJ6. Captura de que TODOS los tests PASAN tras la refactorización**

![Pasa](Capturas/test4to6.png "Pasa")

<br>

## Ejemplo 7

**INPUT y OUTPUT**: "1+2+3+4" -> "10"

**EJ7. Código de test**
```java
@Test
public void test7(){
    CalculatorParser parser = new CalculatorParser();
    assertEquals(parser.parse("1+2+3+4"), 10);
}
```

**EJ7. Mensaje del test añadido que NO PASA**

El test si que pasa con la implementación anterior.

**EJ7. Código mínimo para que el test pase**

Es el mismo código que el anterior.
```java
public int parse(String expression) {
    expression = expression.trim();
    if (expression.contains("+")) {
        String[] parts = expression.split("\\+", 2);
        return parse(parts[0]) + parse(parts[1]);
    }
    return Integer.parseInt(expression.trim());
}
```

**EJ7. Captura de que TODOS los test PASAN**

![Pasa](Capturas/test7.png "Pasa")

**EJ7. Refactorización**
> En este caso solo vamos meter el nuevo test dentro de los casos del test parametrizado de las sumas.

```java
@ParameterizedTest
@CsvSource({"1+1, 2" , "2+3, 5" , "2+3+4, 9" , "1+2+3+4, 10"})
public void test4to6(String expression, int expected) {
    assertEquals(expected, calculator.parse(expression));
}
```
**EJ7. Captura de que TODOS los tests PASAN tras la refactorización**

![Pasa](Capturas/test4to7.png "Pasa")



<br>
