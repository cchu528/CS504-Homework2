package demo.service;


import demo.domain.Order;

public interface OrderDeliveryService {

    Order processOrderDelivery(Order order);
}
