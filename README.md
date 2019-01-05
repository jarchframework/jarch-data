# JArch - Framework for Java Architects
## What is JArch Framework?
JArch is a modular framework for Java that help developers to accelerate their work while developing 
application architectures. It contains a bunch of battle tested utilities and solid structures that guides developers 
to perform best practice OOP techniques.

# jarch-data
JArch Framework Data Module
## Features:
1. AuditLogInterceptor to provide basic entity level auditing for Hibernate<br>
An implementation of Hibernate EntityInterceptor provided [AuditLogInterceptor](src/main/java/org/jarchframework/data/audit/AuditLogInterceptor.java). It is an abstract class you should create your  own implementation by extending from [AuditLogInterceptor](src/main/java/org/jarchframework/data/audit/AuditLogInterceptor.java) to provide your system specific authenticated username. This is required to log the user info that executes entity operations like save, update etc. 

#### Note:
Java 8 is required to build and use this project. 

