# Проблема 1
```
org.hibernate.InstantiationException: No default constructor for entity:  : com.witcher.OwnerEntity
```
### Решение:
Подключить [no-arg-plugin](https://kotlinlang.org/docs/no-arg-plugin.html).
Для того, чтобы не задавать список аннотаций, можно использовать [jpa-support](https://kotlinlang.org/docs/no-arg-plugin.html#jpa-support).

# Проблема 2
```
java.lang.StackOverflowError
	at com.witcher.ChildEntity.toString(Entities.kt)
```
### Решение:
Переопределим метод `toString` для `ChildEntity`.

# Проблема 3
```
java.lang.StackOverflowError
	at org.h2.command.Parser.readTerm(Parser.java:5224)
```
### Решение:
Переопределим методы `equals`, `hashCode` для сущностей.
[Guide](https://thorben-janssen.com/ultimate-guide-to-implementing-equals-and-hashcode-with-hibernate/)

# Проблема 4
```
org.hibernate.AnnotationException: Collection has neither generic type or OneToMany.targetEntity()
```
### Решение:
Меняем `Set` на `MutableSet`


# Best Practices
[Best Practices and Common Pitfalls of Using JPA (Hibernate) with Kotlin](https://www.jpa-buddy.com/blog/best-practices-and-common-pitfalls/)
