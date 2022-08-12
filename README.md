# Logging frameworks

* *java.logging* de jdk pas trop utilisé.
* *log4j*
* *log4j2*
* *logback*

****SLF4J**** : Facade library : Interface standard {} de méthodes commun à tous les frameworks de logging.
Après, c'est à chaque framework de donner sa propre implementation.


## utilisation dans spring boot
Il faut noter qu'à partir de la version 2 de spring boot, nous avons un framework de logging inclue par défaut.
En effet, le starter ``spring-boot-starter-logging``.
#### Zero Configuration Logging ( from [baeldung's website](https://www.baeldung.com/spring-boot-logging/) )
>Spring Boot is a very helpful framework. It allows us to forget about the majority of the configuration settings, many of which it opinionatedly auto-tunes.
In the case of logging, the only mandatory dependency is Apache Commons Logging.
We need to import it only when using Spring 4.x (Spring Boot 1.x) since it's provided by Spring Framework’s spring-jcl module in Spring 5 (Spring Boot 2.x).
We shouldn't worry about importing spring-jcl at all if we're using a Spring Boot Starter (which we almost always are). That's because every starter, like our spring-boot-starter-web, depends on spring-boot-starter-logging, which already pulls in spring-jcl for us.

SLF4J la facade, et deux frameworks qui implémentent l'interface slf4j
* logback ( utilisé par défaut dans spring boot) 
* log4j

###### voir dependency tree
```
[INFO] +- org.springframework.boot:spring-boot-starter-web:jar:2.7.2:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:2.7.2:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:2.7.2:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.2.11:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.2.11:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.17.2:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.17.2:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:1.7.36:compile
```

##### Tous les frameworks partagent 3 notions globales :
* loggers
* appenders : destination de log (console, file, rolling file 'pour chaque jours', jdbc, jms, smtp ...)
* layouts : prepare le format de msg

##### Tous les frameworks ont besoin de :
* ajouter leurs librairies dans le projet ( class path: maven dependencies )
* configurer le framework dans le projet ( via des fichiers xml / .properties)
* ajouter les instructions d'affichage dans le code source.

## utilisation sans spring boot
### Logback ( voir la branche logback-exemple)

##### Ajouter les dépendances
```
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-core</artifactId>
        <version>1.2.6</version>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>1.7.30</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.2.6</version>
    </dependency>
```