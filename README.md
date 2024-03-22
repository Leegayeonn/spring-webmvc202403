# Spring Boot3 Web MVC 학습!

## 프로젝트 초기 설정시 추가할 문법
- build.gradle 파일에 jsp 설정 추가

````
implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
  implementation 'jakarta.servlet:jakarta.servlet-api'
  implementation 'jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api'
  implementation 'org.glassfish.web:jakarta.servlet.jsp.jstl'

