# Logging frameworks

* *java.logging* de jdk pas trop utilisé.
* *log4j*
* *log4j2*
* *logback*

****SLF4J**** : Facade library : Interface standard {} de méthodes commun à tous les frameworks de logging.
Après, c'est à chaque framework de donner sa propre implementation.


## utilisation dans spring boot
Il faut noter qu'à partir de la version 2 de spring boot, nous avons un framework de logging inclue par défaut.
En effet, le starter ``spring-boot-starter-logging`` prepare le terrain pour nous en activant un framework de logging par défaut logback.
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
* loggers : c'est le context de log, il peut etre lié à une classe, un package.
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
        <artifactId>logback-classic</artifactId>
        <version>1.3.0-beta0</version>
    </dependency>
```
logback-classic inclue à la fois ``slf4j`` et ``logback-core``.
```
[INFO] \- ch.qos.logback:logback-classic:jar:1.3.0-beta0:compile
[INFO]    +- ch.qos.logback:logback-core:jar:1.3.0-beta0:compile
[INFO]    \- org.slf4j:slf4j-api:jar:2.0.0-beta1:compile
```

#### Niveau de log
A Logger has a Level, which can be set either via ``configuration`` or with ``Logger.setLevel()``. Setting the level in code overrides configuration files.

The possible levels are, in order of precedence: ``TRACE, DEBUG, INFO, WARN and ERROR``. Each level has a corresponding method that we use to log a message at that level.

***If a Logger isn't explicitly assigned a level, it inherits the level of its closest ancestor ( also inherit the appender-ref from the root logger if the log is not defining a loger appender )***. 
The root logger defaults to DEBUG.

:point_right: **en gros si un log ne définie pas explicitement sa propre configuration : son level, appender, format de msg ( à travers le fichier logback.xml ou bien dans le code source ), 
il va hériter cela de son log parent que ce soit niveau supérieur ou bien jusqu'à arriver au niveau root log.

:point_right: remarque pour les appenders: Appenders are cumulative, cad si un logger possède un fileAppender et sont root logger utilise une console appender notre log va avoir les deux appender à la fois ** 

on peut modifier ce comportement en utilisant le paramètre ``additivity`` à ``false``.
```
<logger name="com.baeldung.logback.tests" level="WARN" additivity="false" >
<appender-ref ref="FILE" />
</logger>

<root level="debug">
    <appender-ref ref="STDOUT" />
</root>
```
>Setting additivity to false disables the default behavior. Tests won't log to the console, and neither will any of its descendants.
#### Chargement de configuration
A configuration file can be placed in the classpath and named either ``logback.xml`` or ``logback-test.xml``.

Here's how Logback will attempt to find configuration data:
1. Search for files named ``logback-test.xml``, ``logback.groovy``, or ``logback.xml`` in the classpath, in that order
2. If the library doesn't find those files, it will attempt to use Java's ``ServiceLoader`` to locate an implementor of the ``com.qos.logback.classic.spi.Configurator``.
3. Configure itself to log output directly to the console

#### Troubleshooting Configuration
1. on peut voir le chargement de la configuration logback si nous activons le mode debug dans le fichier de configuration.
```
logback.xml
   <configuration debug="true">
   ...
   </configuration>
```
2. on peut avoir aussi des informations sur la configuration en activant un listener logback **StatusListener**.
```
 <configuration>
 
   <statusListener class="ch.qos.logback.core.status.OnConsoleStatusListener" />
   ...
 </configuration>
```

3. on peut recharger le fichier de configuration ( en cas de changement ) pendant l'exécution de l'application.
 ```
 <configuration scan="true" scanPeriod="15 seconds">
   ...
 </configuration>
 ```
#### Appenders
1. ConsoleAppender
2. FileAppender
3. RollingFileAppender (based on time, file size, or a combination of both)
4. Custom Appenders ([voir exemple](https://www.baeldung.com/custom-logback-appender))