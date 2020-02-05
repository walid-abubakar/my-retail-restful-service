# My Retail Services
myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 

The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller.


## Installation and Setup

Tools to Install
* Docker Container (latest preferably)
* Kitematic (may be packaged with docker installation)
* MongoDB (we will run it in the docker container). You can pull one of the official images directly using Kitematic or you can create your own. I installed the official bitnami mongodb.
* Insomnia

Frameworks/Language
* Spring Boot MVC
* Kotlin
* JUnit Mockk for unit tests
* Maven

Run It!

* Clone this repo
* Open it in your favorite IDE (or run it directly from the command line using maven) 
* Open the Docker container or Kitematic. Make sure the port the db is listening on is 27017. You can verify this if you navigate to Hostname/Ports tab in Kitematic. Set the published ports to localhost:27017. Then run it so it can listen for connections.
* If you want to explore the database, click on the exec menu that will open the container's shell window. Then run


```bash
mongo
```
view existing collections :
```bash
show collections
```

It's fine if its empty since we didn't add anything yet.

## APIs

Ids are the TCINs. Can be found from Target.com

* POST: http://localhost:8080/products

example body
```JSON
{
	"id": "14930591",
	"value": "6.29",
	"currency_code": "USD"
}
```
* GET: http://localhost:8080/products/{Id}

example response

```JSON
{
  "id": 14930591,
  "name": "Buitoni All Natural Mixed Cheese Tortellini - 20oz",
  "current_price": {
    "value": 6.99,
    "currency_code": "USD"
  }
}
```

* PUT: http://localhost:8080/{Id}

example body: note: though I include the name as part of the body. Name won't get updated. An improvement would be to not send it at all.
```JSON
{
	"id": 14930591,
	"name": "The Big Lebowski (Blu-ray) (Widescreen)",
	"current_price": {
		"value": "6.99",
		"currency_code": "USD"
	}
}
```

* DELETE: http://localhost:8080/products/{Id}




## Tests
Two unit tests are written under the test directory. They are implemented using junit jupiter and mockk 
