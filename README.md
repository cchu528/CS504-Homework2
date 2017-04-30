# CS504-Homework2

FoodDeliveryApplication

##  Status:    
        Work: 
            -- Payment Service and Order Delivery Service
            
        Not fully work: 
            -- There is data saving issue in Restaurant Discovery Service and Order Service right now. The inifinite call
               on the bidirectional relationship of parent and child causes Stack Overflow exception and failed saving. 
               Will address later.
               
        Waiting for implementation:
            -- Order collection & Simulation Service, Order Distribution Service, MQ, UI, Eureka, and Hystrix stream.
      

## Service start Sequence 

     1. docker-compose up
     2. sh ./start-restaurant-discovery-service.sh
     3. sh ./start-order-service.sh
     4. sh ./start-payment-service.sh
     5. sh ./start-order-delivery-service.sh

## To upload Data

     1. go to resturant-discovery-service folder and run ./upload-restaurants.sh
     2. go to order-service folder and run ./upload-order.sh
     

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
        o	estimateDeliveryTimeInMinutes: random generating time between 5 minutes to 1 hour.
    -- DB: NoSQL DB
    
Payment Service

    -- Make payment for order.
    -- Contains paymentId, cardNumber, securityCode, expirationDate, nameOnCard, zip code, and timestamp.    
    -- DB: SQL DB
    
 Order Delivery Service
 
    -- Calculate estimate delivery time.
    
MQs for Order Distribution Service and Order Service.

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
