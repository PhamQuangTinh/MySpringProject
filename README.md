# Clothes Shop Online

### Technology:
  * Back end: Spring boot
  * Front end: Angular
  * Database: Mysql

### Design Pattern: n-tier(Presentation Layer, Business Layer & Data Layer), solid

### Port:
  * Spring boot: https://localhost:8080
  * Angular: http://localhost:4200

### Project Structure:
  **There are 5 main modules in project: domain, repository, service, web, src**
  * domain: domain contains classes which map with database's tables (ORM)
  * repository: repository cantains interfaces which create methods to query database
  * service: service contains classes which execute logic code
  * web: web contains classes which create api
  * src: src contains Angular ClientApp



### How to run application
  * 1. Change your username, password and url to mysql account in ou.phamquangtinh.entity.resources.domain.properties in domain module
  * 2. Import database to mysql by database.sql in database module
  * 3. Change your ssl key store(server.ssl.key-password) passworld in ou.phamquangtinh.controller.resources.application.properties in web module
  * 4. Run Application in ou.phamquangtinh.controller.Application in web module
  * 5. Change the current url folder to /MySpringProject/src/main/java/shop-web
  * 6. Run Angular project by command: ng serve
  * 7. To check api: https://localhost:8080/swagger-ui.html
  * 8. To show ui: http://localhost:4200
