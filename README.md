# spring-boot-practice

[![Build Status](https://travis-ci.org/ksoichiro/spring-boot-practice.svg?branch=master)](https://travis-ci.org/ksoichiro/spring-boot-practice)

This is a project to show 'how-to' for Spring Boot application development.  

To check each topics, see each sub-directories' README.

## Contents

1. [Hello world](20150622-hello-world)
1. [Connecting to database](20150622-db)
1. Join tables
    1. [Join using ManyToMany annotation](20150829-join-manytomany)
    1. [Join using OneToMany annotation](20150829-join-manytomany2)
    1. [Join using JPQL with association table](20150829-join-manytomany3)
    1. [Join using JPA Specification](20150901-join-manytomany4)
    1. [Join using Querydsl](20150903-join-manytomany5)
    1. [Join using Querydsl with pagination](20150903-join-manytomany6)
    1. [Join using Querydsl with soft delete](20150903-join-manytomany7)

## Notice

* This project uses Gradle and developed with IntelliJ IDEA.
* This project uses Java 8 lambda expression.
    * Please set Gradle JVM to Java 8 to build properly.
* This project uses Lombok to generate getters/setters.
    * Please set Annotation Processor function enabled (Preferences > Build, Execution, Deployment > Compiler > Enable annotation processing).

## License

    Copyright 2015 Soichiro Kashima

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
