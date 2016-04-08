# Class definition modification example using Javassist

## About

Toggle that some `@Entity` class fields' are persistent or not with annotation and property.
Whether a field is persistent or not is judged by Hibernate using `@Transient`,
and if we'd like to treat a field persistent on development and transient on production,
`@Transient` should be given after checking Spring properties.
To achieve this, we listen Spring application loaded event,
checking all `@Entity` classes and modify class definition using Javassist.

## Launch

```
./gradlew bootRun
```
