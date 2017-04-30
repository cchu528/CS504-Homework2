# CS504-Homework2

FoodDeliveryApplication

User/Account Service

    -- Create an account: with first name, last name, email address, password, phone number, address.
    -- Sign in: with email address and password
    -- DB: SQL DB
    
Restaurant Service

    -- Provide insert/delete/update/search functionalities.    
    -- Listen to Order messsage.
    -- Contains all restaurant information including name, address, phone number, open hours, and a list of menus 
       where menu contains Id, name, description, category, and unitPrice.    
    --DB: SQL DB
    
Order collection & Simulation Service: Take order.

Order Distribution Service: Distribute order to Order Service.

Order Service

    -- Process order.    
    -- Publish Order message.
    -- Order contains 
        o	orderId
        o	userId,
        o	restaurantId
        o	A list of orderItem where orderItem contains menuId, name, quantity, note, and price.
        o	total
        o	deliveryAddress
        o	paymentId
        o	timestamp
        o	estimateDeliveryTime: random generating time between 5 minutes to 1 hour.
    -- DB: NoSQL DB
    
Payment Service

    -- Make payment for order.
    -- Contains paymentId, cardNumber, securityCode, expirationDate, nameOnCard, zip code, and timestamp.    
    -- DB: SQL DB
    
MQs for Order Distribution Service and Order Service, and between Order Service and Restaurant Service.

www.fooddelivery.com/users/

    -- Http method: Post
    -- Request:  { 
              emailAddress:  
              password:
              firstName: 
              lastName:
              phoneNumber:
              address:
              city:
              state:
              zip:
             }
    -- Response Http status: 201
    
www.fooddelivery.com/users/

    -- Http method: Get
    -- Request: { 
              emailAddress:  
              password:
             }
    -- Response Http Status: 200; 
    
www.fooddelivery.com/restaurants/

    -- Http method: Get
    -- Response: A list of restaurants and http status is 200
    
www.fooddelivery.com/restaurants/{restauarntName}

    -- Http method: Get
    -- Response: Searched restaurant and http status is 200.
    
www.fooddelivery.com/restaurants/{restaurantName}/menus

    -- Http method: Get
    -- Response: A list of menus of the restaurant.
    
www.fooddelivery.com/restaurants/{restaurantName}/menus/{menuId}

    -- Http method: Get
    -- Response: Searched restaurant menu and http status is 200. 
    
www.fooddelivery.com/restaurents/{restaurantName}/orders/{orderId}

    -- Http method: Get
    -- Response: Searched order and http status is 200.
    
www.fooddelivery.com/restaurents/{restaurantName}/orders/

    -- Http method: Post
    -- Request: order jason
    -- Response: { 
                  orderId: , 
                  estimateDeliveryTime:
                 }  and http status is 201
                 
www.fooddelivery.com/payments/

    -- Http method: Post
    -- Request:  { 
              cardNumber:, 
              securityCode:,
              expirationDate:,
              nameOnCard:, 
              zip code:
              }                  
    -- Response: { 
                   paymentId:, 
                   timestamp:
                 }  and http status is 201.
