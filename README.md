# employee-management-service
Backend for employee management problem

A restful project is created using spring boot to:
1. Retrieve list of employees
2. Sorting employees using salary, name and hiredate
3. Add employees

The swagger api documentation is present at http://localhost:9000/swagger-ui.html#/employee-controller

To run the project
1. run  mvn clean install
2. run  docker build -t ems:latest .
3. run  docker-compose up -d

Endpoints:
To list employees:
  GET http://localhost:9000/employee
Get employee by id
  GET http://localhost:9000/employee/{id}
To sort employees by salary for a sort sequence:
  GET http://localhost:9000/employee?sortby={salary/name/hiredate}&sortseq={asc/desc}
  

