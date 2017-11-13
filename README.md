# Employee Hierarchy   API

### How To Build and Run the API

Pre-requisites
--------------
JDK 1.8.x 

Technologies/ Libraries used

    Java 8
    Spring boot
    Spring boot security
    Sprin Rest Web Services 
    Mockito
    AssertJ
    Angularjs 1.X
    Gradle wrapper 

#### How to run and deploy
Go to the project root directory and run following commands
###### To build the application
    ./gradlew build    or just gradle build
    
###### To Run the application    
    ./gradlew bootRun or just gradle bootRun
###Once deployed the web application can be accessed using the below url 
   http://localhost:8080/


## Access the API
Please use one of the below tools to access the application 
  1. Postman either stand alone or Ext to chrome 
  2. SOAP UI 

##Useful information , data structures 
 ### The main data structure which has been exercise in this project is Stack. For this I have use the java recursion has been used as the stack to traverse the elements to form the tree structure. However 
 	 we could define a separate stack data structure to handle this
 ### All the post requests will be authenticated against an api key. Please see below for further details. All the post requests will be filtered through HttpMethodAuthorisationFilter.java
 ### Rest-assured has been used to test the web services
 ### Mockito with JUnit has been used for the TDD approach  
 ### Spring Rest web services has been used to develop  Rest-ful api for the application.
 ### Have used an in memory data base , just to keep the project simple. Can modify this application to work with spring repository by extending  JpaRepository<Entity,Long>
 ### Please click the pre-loaded employee button if you need to have a quick demonstration 

 

=======================================================================
## API Information
The contact information API provides 

    1   Add a new Employee to the system 
    2   Clear all the employees 
    3   Initialize pre-loaded  employee ( Just for the quick demonstrations)
    4   View all the existing employees 
    5   VIEW EMPLOYEE HIERARCHY OF CURRENT EMPLOYEE STRUCTURE - (Main Requirement )

## How to use the API (All requests should have the header key/value  API-KEY xJ9a34fo, and please select the media type to JSON. Please find the sample request from postman)
key 		:   API-KEY
Value   :   xJ9a34fo

# View Employee Hierarchy 
### view url/Request Method POST  : http://localhost:8080/api/v0/employeeDetails/fetchEmpHierarchy
### use the header values with the key API-KEY and value xJ9a34fo (*required)
### use the below request for both 

## Sample Request for viewEmployeeHierarchy
				{
				    "viewEmployeeHierarchyRequest": {
				        "limitHierarchy": "10"
				    }
				}
### Sample response for viewEmployeeHierarchy
		{
    "viewEmployeeHierarchyResponse": {
        "errorMessage": null,
        "responseStatus": "Success",
        "employeeRepresentationDTO": {
            "employeeName": "Jamie",
            "subs": [
                {
                    "employeeName": "Alan",
                    "subs": [
                        {
                            "employeeName": "Martin",
                            "subs": []
                        },
                        {
                            "employeeName": "Alex",
                            "subs": []
                        },
                        {
                            "employeeName": "Roshan",
                            "subs": []
                        }
                    ]
                },
                {
                    "employeeName": "Steve",
                    "subs": [
                        {
                            "employeeName": "David",
                            "subs": []
                        },
                        {
                            "employeeName": "Allan",
                            "subs": [
                                {
                                    "employeeName": "Sarah",
                                    "subs": []
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    }
}

# Create Employee
### create employee		: http://localhost:8080/api/v0/employeeDetails/create
### use the header values with the key API-KEY and value xJ9a34fo
### Validations :
     1. Employee name is mandatory 
     2. Should have a valid manager id 

## Sample Request for create employee details 
 
	{
	    "employeeId": null,
	    "employeeName": "Rashendra",
	    "managerId": 10
	}
### Sample response for create employee details 
	{
	    "employeeId": 1,
	    "employeeName": "Rashendra",
	    "managerId": 10
	}
	
	
# Fetch all employees
### api endpoint : http://localhost:8080/api/v0/employeeDetails/fetchAll
### method : GET 

## Sample response 
	[
	    {
	        "employeeId": 100,
	        "employeeName": "Alan",
	        "managerId": 150
	    },
	    {
	        "employeeId": 150,
	        "employeeName": "Jamie",
	        "managerId": null
	    },
	    {
	        "employeeId": 190,
	        "employeeName": "David",
	        "managerId": 400
	    },
	    {
	        "employeeId": 220,
	        "employeeName": "Martin",
	        "managerId": 100
	    },
	    {
	        "employeeId": 275,
	        "employeeName": "Alex",
	        "managerId": 100
	    },
	    {
	        "employeeId": 276,
	        "employeeName": "Roshan",
	        "managerId": 100
	    },
	    {
	        "employeeId": 400,
	        "employeeName": "Steve",
	        "managerId": 150
	    }
	]

# Delete all employees
## api url : http://localhost:8080/api/v0/employeeDetails/clearDb
### will clear the data in inMemory database



## Security Features *    
####If you do not have a valid Auth Token, the API will return following error
        HTTP STATUS 403 
       {
	    "timestamp": "2017-11-12T22:16:58.898+0000",
	    "status": 403,
	    "error": "Forbidden",
	    "message": "Could not verify the provided CSRF token because your session was not found.",
	    "path": "/api/v0/employeeDetails/create"
		}
    
#Validations
## The current organization should have only one CEO (allowing one record to be set without a manager id)
## All other employees should have a valid manager id ( Should be one of the employee as his manager)
## Employee name is mandatory when adding employees   
    
# Further enhancement and limitations 
## Use Karma test cases in the front end
## Replace the in memory database with RDMS such as mysql, postgres
## Use JpaRepository or spring CrudRepository which will build the ORM support - Can use RDS instance hosted in AWS 

     
    
    