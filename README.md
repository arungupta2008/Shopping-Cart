## *Overview*
Create Sample Shopping Cart Application for home work assignment to the Saket Tiwari.

## *Prerequisites*
* Java 1.8
* Maven 3
* MySQL
* Spring Boot
* Hibernate
* HTML , CSS & Javascript (AngularJS/Bootstrap)

## *Build Project*
1. Create Database in local ```CREATE DATABASE shopping_cart_db;```
2. Set Username and Password in the ```application.properties``` file. If Database is in remote please update the url also.
3. Clone the project
4. Navigate to root path (ShoppingCart)
5. Invoke ```mvn clean install -DskipTests```
6. Navigate to target folder

## *Run Project*
* Invoke following command
```java -jar ShoppingCart-0.0.1-SNAPSHOT.jar``` then run curl commands


## *API Descriptions*

1. API to get all Products

```curl --location --request GET 'localhost:8080/shoppingcart/products'```

2. APIs To search Products

```curl --location --request GET 'localhost:8080/shoppingcart/products/search/?key=id&value=1'```

Above API is used to find products by id

```curl --location --request GET 'localhost:8080/shoppingcart/products/search/?key=category&value=Wallet'```
Above API used to get the Products by Category


```curl --location --request GET 'localhost:8080/shoppingcart/products/search/?key=name&value=Leather%20Wallets'```

Above API used to fetch products by name

3. API to Add products in Cart

```
curl --location --request POST 'localhost:8080/shoppingcart/shoppingCart' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productId": "1",
    "stock" : 10,
    "status": "Active",
    "userId": "2"
}'
```

4. API To find all Active Shopping carts

```curl --location --request GET 'localhost:8080/shoppingcart/shoppingCart'```


5. Update Shopping Cart 

```curl --location --request PUT 'localhost:8080/shoppingcart/shoppingCart/1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productId": "1",
    "stock" : 12,
    "status": "Active",
    "userId": "2"
}'
```

6. Delete A Cart 

```
curl --location --request DELETE 'localhost:8080/shoppingcart/shoppingCart/2'
```

7. For Admin Clear All carts

```curl --location --request DELETE 'localhost:8080/shoppingcart/shoppingCart'```

8. Purchange Cart

```curl --location --request POST 'localhost:8080/shoppingcart/shoppingCart/purchase/3'```


