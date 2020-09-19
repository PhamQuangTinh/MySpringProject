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
