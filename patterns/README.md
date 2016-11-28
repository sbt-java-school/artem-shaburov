# Паттерны

### Задания

#### Конвертер
Разработать класс(классы) по конвертации произвольных объектов классов: 
Integer, Long, Float, Double, BigDecimal, String 
в любой требуемый объект: 
Integer, Long, Float, Double, BigDecimal, String. 
Реализация должна быть написана таким образом, 
чтобы была возможность легко добавить новые правила конвертации 
любого произвольного объекта в любой другой произвольный объект. 
Должны быть тесты.

#### Кредитная система
Разработать систему, проверяющую можно ли выдать кредит, 
основываясь на заданных параметрах. Выполнить задание, 
используя паттерн проектирования Composite (компоновщик).
Условия, которые должны выполняться для выдачи кредита:

`(city == "Novosibirsk") && (amountOfCredit / periodOfMonths < salary / 2)`

### Выполнение

#### Конвертер
Решено было выполнить задание не так, как предлагалось на уроке, 
а несколько по-другому. Используя поведенческий шаблон **Strategy**, 
был реализован класс [UniversalConverter](https://github.com/sbt-java-school/artem-shaburov/blob/master/patterns/src/main/java/ru/sbt/converter/implementations/UniversalConverter.java), 
в котором хранится мапа конвертеров. 
Вместо **Class** как ключ используется кастомный объект, 
который хранит класс из которого нужно произвести конвертацию и 
класс в который нужно произвести конвертацию. 
Значением мапы является конвертер из одного объекта класса в другой. 
Этот подход отличается от рассмотренного на уроке, т.к. на уроке мы 
добавляли два слоя (как пример, для конвертации из **String** в **Double**, 
нужно было реализовать **FromString** конвертер для произвольного объекта, 
а затем **StringToDouble**). Здесь же достаточно реализовать интерфейс 
**ValueConverter<F, T>**, параметризованный исходным и 
ожидаемым после конвертации типом и добавить в **UniversalConverter**: 

`converter.add(Integer.class, Long.class, new IntegerToLongConverter());`.

#### Кредитная система
Для разработки данной системы использовался паттерн Composite (компоновщик). 
Были реализованы два класса, реализации интерфейса **Node**: 
**ParameterNode** (конечная нода) и **OperationNode** (нода, производящая операцию). 
При вызове метода **Node.getResult(Map<String, Double> values)**, 
этот метод вызывается у всех дочерних нод. 
Результатом операции является double, равный 1d или 0d, 
который и показывает можно ли выдавать кредит. 
Промежуточных значений у этого значения нет.