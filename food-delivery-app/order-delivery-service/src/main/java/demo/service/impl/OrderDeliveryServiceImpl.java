package demo.service.impl;

import demo.domain.Order;
import demo.service.OrderDeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class OrderDeliveryServiceImpl implements OrderDeliveryService {

    private Random rand;

    public OrderDeliveryServiceImpl() {
        this.rand = new Random();
    }

    @Override
    public Order processOrderDelivery(Order order) {
        order.setEstimateDeliveryTimeInMinutes(calcEstimateDeliveryTime(order));
        return order;
    }

    private int calcEstimateDeliveryTime(Order order) {
        rand.setSeed(System.currentTimeMillis());
        return ((rand.nextInt() % 55) + 5);
    }
}
