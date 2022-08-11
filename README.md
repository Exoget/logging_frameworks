# Logging framework


Il faut noter qu'à partir de la version >= 2 de spring boot, nous avons un framework de logging inclue.

slf4j la facade, et deux frameworks qui implémentent l'interface slf4j
* logback ( utilisé par défaut dans spring boot) 
* log4j. 
voir dependency tree

[INFO] +- org.springframework.boot:spring-boot-starter-web:jar:2.7.2:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:2.7.2:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:2.7.2:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.2.11:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.2.11:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.17.2:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.17.2:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:1.7.36:compile