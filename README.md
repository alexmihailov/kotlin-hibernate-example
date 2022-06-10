# Проблема 1
```
org.hibernate.InstantiationException: No default constructor for entity:  : com.witcher.problem.OwnerEntity
```
### Решение:
Подключить [no-arg-plugin](https://kotlinlang.org/docs/no-arg-plugin.html).
Для того, чтобы не задавать список аннотаций, можно использовать [jpa-support](https://kotlinlang.org/docs/no-arg-plugin.html#jpa-support).

# Проблема 2
```
java.lang.StackOverflowError
	at com.witcher.problem.ChildEntity.toString(Entities.kt)
```
### Решение:
Переопределим метод `toString` для `ChildEntity`.
