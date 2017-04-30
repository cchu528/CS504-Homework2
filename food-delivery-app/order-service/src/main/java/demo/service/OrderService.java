package demo.service;


import demo.domain.Order;
import demo.domain.OrderInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Order processOrderInformation(OrderInfo orderInfo);

    Order findByOrderId(long orderId);

    Page<Order> findAllOrders(Pageable pageable);

    void deleteByOrderId(long orderId);
}
