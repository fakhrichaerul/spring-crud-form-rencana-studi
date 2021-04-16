# Aplikasi Form Rencana Studi
Features :

    • CRUD mahasiswa
    • CRUD jurusan
    • CRUD matakuliah
    • CRUD form rencana studi (FRS)

Quickstart :

1. Run the app

    ```
   mvn spring-boot:run
    ```

2. Database in-memory can be accessed from url :
    ```
    http://localhost:8080/h2-console
    ```
   Login with jdbc url, username and password
    same as `application.properties` configuration
    ```
    • JDBC URL = jdbc:h2:mem:h2db
    • User Name = h2
    • Password = password
    ```

3. Restful API docs can be accessed from url :

    ```
    http://localhost:8080/swagger-ui.html
    ```