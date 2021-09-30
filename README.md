
1) List of Employees API 
I have implemented API to retrieve all the Employees available in the Database.

URL : http://127.0.0.1:8080/1.0/allEmployees
Method : GET
Produces : application/json

Sample Response :

[{"userId":"001000","firstName":"David","lastName":"Beckham"},{"userId":"001001","firstName":"Audrey","lastName":"Hepburn"},{"userId":"001002","firstName":"Kim","lastName":"Kardashian"},{"userId":"001003","firstName":"Will","lastName":"Smith"},{"userId":"001004","firstName":"Franklin","lastName":"Roosevelt"},{"userId":"001005","firstName":"Harry","lastName":"Truman"},{"userId":"001006","firstName":"Dwight","lastName":"Eisenhower"},{"userId":"001007","firstName":"John","lastName":"Kennedy"},{"userId":"001008","firstName":"Tom","lastName":"Cruise"},{"userId":"001009","firstName":"Richard","lastName":"Nixon"},{"userId":"001010","firstName":"Gerald","lastName":"Ford"},{"userId":"001011","firstName":"Camille","lastName":"Saint-Saëns"},{"userId":"001013","firstName":"Brad","lastName":"Pitt"},{"userId":"001014","firstName":"Bill","lastName":"Clinton"},{"userId":"001015","firstName":"Sinéad","lastName":"O’Connor"},{"userId":"001016","firstName":"Céline","lastName":"Dion"},{"userId":"001017","firstName":"René","lastName":"Magritte"},{"userId":"001018","firstName":"Joe","lastName":"Biden"},{"userId":"002018","firstName":"Adam","lastName":"Driver"},{"userId":"005018","firstName":"Adam","lastName":"Sandler"},{"userId":"001062","firstName":"Adam","lastName":"West"},{"userId":"001218","firstName":"Adam","lastName":"Smith"}]

2) Update an Employee API
I have implemented API to update an Employee based on Employee number.

URL : http://127.0.0.1:8080/1.0/updateEmployee
Method : PATCH
Consumes : application/json
Produces : application/json
Sample Request : 
{"userId":"001000","firstName":"David New","lastName":"Beckham new"}

Sample Response :
{
    "userId": "001000",
    "firstName": "David New",
    "lastName": "Beckham new"
}

Note : I have implemented exception scenario if employee not found

3) Employee Search criteria API based on firstname and lastname
In this API I have implemented search criteria based on firstname and lastname, we can use both the names or either one for searching.
This will result based on like operation.

URL : http://127.0.0.1:8080/1.0/allEmployees?firstname=David&lastname=Becham // This is to retrieve data based on firstname like '%David%' and 	lastname like '%Beckham%'
	  http://127.0.0.1:8080/1.0/allEmployees?firstname=David  // This is to retieve data based on firstname like '%David%'
	  http://127.0.0.1:8080/1.0/allEmployees?lastname=Beckham  // This is to retieve data based on lastname like '%Beckham%'
	  
Method : GET
Produces : application/json

Sample Response :
{
    "userId": "001000",
    "firstName": "David New",
    "lastName": "Beckham new"
}

Notes:
I have used 'specification-arg-resolver' library for search API implementation.
If we spend time then we can provide the security for all the APIs with oAuth2 based authentication to provide the security.
Also we can add organisation specific headers to exchange the information.
We can also implement the same APIs by using Microservice architecture to provide better performance and maintenance.

I have implemented the test cases in EmployeeControllerTest.java file, you can execute this file for trest case results.
I have also implemented for negative scenarios.
If I have more time, I can aslo cover more negative test scenarios as well.


Thanks,
Aluru Nagarjuna
Email : nagarjuna.aluru@gmail.com
