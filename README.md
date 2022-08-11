# Logging frameworks

* *java.logging* de jdk pas trop utilisé.
* *log4j*
* *log4j2*
* *logback*

****SLF4J**** : Facade library : Interface standard {} de méthodes commun à tous les frameworks de logging.
Après, c'est à chaque framework de donner sa propre implementation.

Il faut noter qu'à partir de la version 2 de spring boot, nous avons un framework de logging inclue par défaut.

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
* appenders (console, file, rolling file 'pour chaque jours', jdbc, jms, smtp ...)
* layouts

##### Tous les frameworks ont besoin de :
* ajouter leurs librairies dans le projet ( class path: maven dependencies )
* configurer le framework dans le projet ( via des fichiers xml / .properties)
* ajouter les instructions d'affichage dans le code source.