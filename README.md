# Coding Challenge

## What's Provided

A simple [Spring Boot](https://projects.spring.io/spring-boot/) web application has been created and bootstrapped with
data. The application contains information about all employees at a company. On application start-up, an in-memory Mongo
database is bootstrapped with a serialized snapshot of the database. While the application runs, the data may be
accessed and mutated in the database without impacting the snapshot.

### How to Run

The application may be executed by running `gradlew bootRun`.

### How to Use

The following endpoints are available to use:

```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/employee
    * PAYLOAD: Employee
    * RESPONSE: Employee
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/employee/{id}
    * RESPONSE: Employee
* UPDATE
    * HTTP Method: PUT 
    * URL: localhost:8080/employee/{id}
    * PAYLOAD: Employee
    * RESPONSE: Employee
```

The Employee has a JSON schema of:

```json
{
  "type": "Employee",
  "properties": {
    "employeeId": {
      "type": "string"
    },
    "firstName": {
      "type": "string"
    },
    "lastName": {
      "type": "string"
    },
    "position": {
      "type": "string"
    },
    "department": {
      "type": "string"
    },
    "directReports": {
      "type": "array",
      "items": "string"
    }
  }
}
```

The Report has a JSON schema of:

```json
{
  "type": "ReportingStructure",
  "properties": {
    "employee": {
      "type": "Employee"
    },
    "numberOfReports": {
      "type": "integer"
    }
  }
}
```

The Compensation has a JSON schema of:

```json
{
  "type": "Compensation",
  "properties": {
    "employee": {
      "type": "Employee"
    },
    "effectiveDate": {
      "type": "string",
      "format": "mm/dd/yyyy"
    },
    "salary": {
      "type": "double"
    }
  }
}
```

For all endpoints that require an "id" in the URL, this is the "employeeId" field.

## What to Implement

Clone or download the repository, do not fork it.

### Task 1

Create a new type, ReportingStructure, that has two properties: employee and numberOfReports.

For the field "numberOfReports", this should equal the total number of reports under a given employee. The number of
reports is determined to be the number of directReports for an employee and all of their distinct reports. For example,
given the following employee structure:

```
                    John Lennon
                /               \
         Paul McCartney         Ringo Starr
                               /        \
                          Pete Best     George Harrison
```

The numberOfReports for employee John Lennon (employeeId: 16a596ae-edd3-4847-99fe-c4518e82c86f) would be equal to 4.

This new type should have a new REST endpoint created for it. This new endpoint should accept an employeeId and return
the fully filled out ReportingStructure for the specified employeeId. The values should be computed on the fly and will
not be persisted.

## Task Completed

- [x] Created a new type, ReportingStructure, that has two properties: employee and numberOfReports.
- [x] Created a new REST endpoint for the new type ReportingStructure that accepts an employeeId and returns the fully 
filled out ReportingStructure for the specified employeeId. 
- [x] Unit tested the new REST endpoint in ReportingStructureServiceImplTest.
- [x] Sample Response:
<img width="683" alt="image" src="https://user-images.githubusercontent.com/14349274/173548108-548ae405-c9d1-4cfa-ab02-919c7dcf72b6.png">


### Task 2

Create a new type, Compensation. A Compensation has the following fields: employee, salary, and effectiveDate. Create
two new Compensation REST endpoints. One to create and one to read by employeeId. These should persist and query the
Compensation from the persistence layer.

## Task Completed
- [x] Created a new type, Compensation.
- [x] Created two new REST endpoints for the new type (create and read) that persist and query the Compensation from the
persistence layer.
- [x] Unit tested the new REST endpoints in CompensationServiceImplTest.
- [x] Sample Response:
<img width="694" alt="image" src="https://user-images.githubusercontent.com/14349274/173548163-cbfe05db-53af-47da-b1ca-5b7d65331f5a.png">



## Delivery

Please upload your results to a publicly accessible Git repo. Free ones are provided by Github and Bitbucket.
