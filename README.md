# student-service


###  Build application
**cd /absolute-path-to-directory/student-service**  
and try below command in terminal
> **```mvn clean install```** it will build application and create **jar** file under target directory 





Run below docker-compose command to download and up/deploy containers  
> docker-compose up --force-recreate  


List the containers and check the status with this command  
> docker-compose ps


To view the container logs, run this command  
> docker-compose logs


To connect mongo CLI via terminal run below  
> docker exec -it mongo bash


   
### API Endpoints

- #### Student CRUD Operations
    > **GET Mapping** http://localhost:8080/api/students  - Retreive all students

    > **GET Mapping** http://localhost:8080/api/one-by-depName/Ecommerce  - Find fiter by dep name
    
    > **GET Mapping** http://localhost:8080/api/one-by-firstName/Sam  - Find fiter by first name
    
    > **GET Mapping** http://localhost:8080/api/one-by-lastName/John  - Find fiter by last name
       
    > **POST Mapping** http://localhost:8080/api/students  - Add new Student in DB  
    
      Request Body  
      ```
        {
            "firstName":"Sam",
            "lastName":"John",
            "depName":"Ecommerce"
        }
      ```
    
   