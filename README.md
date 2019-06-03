# employee-management-service
Backend for employee management problem

A restful project is created using spring boot to:
1. Retrieve list of employees
2. Sorting employees using salary, name and hiredate
3. Add employees

The swagger api documentation is present at http://localhost:9000/swagger-ui.html#/employee-controller

To run the project
In project directory execute following:
1.   <br>mvn clean install
2.   <br> docker build -t ems:latest .
3.   <br>docker-compose up -d

<br>Endpoints:<br>
To list employees:<br>
  GET http://localhost:9000/employee<br>
Get employee by id<br>
  GET http://localhost:9000/employee/{id}<br>
To sort employees by salary for a sort sequence:<br>
  GET http://localhost:9000/employee?sortby={salary/name/hiredate}&sortseq={asc/desc}
  

