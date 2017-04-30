package demo.service.impl;

import demo.domain.Order;
import demo.domain.OrderInfo;
import demo.domain.OrderRepository;
import demo.domain.Payment;
import demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${com.foodDelivery.running.payment.service}")
    private String runningPaymentService;

    @Value("${com.foodDelivery.running.orderDelivery.service}")
    private String runningOrderDeliveryService;

    // constructor dependency injection
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order processOrderInformation(OrderInfo orderInfo) {

        Payment payment = orderInfo.getPayment();
        Order order = orderInfo.getOrder();
        if (payment == null || order == null) {
            return null;
        }
        payment.setAmount(order.getTotal());

        // Make payment
        payment = processPayment(payment);
        log.info("return from Payment Service.");
        order.setPaymentId(payment.getPaymentId());
        order.setTimestamp(payment.getTimestamp());

        // Get estimate delivery time.
        Order returnOrder = processOrderDelivery(order);
        log.info("return from Order Delivery Service.");

        // TO DO: Need to fix the infinite issue due to the bidirectional reltionship of parent and child.
        // order = orderRepository.save(orderInfo.getOrder());

        return returnOrder;
    }

    @Override
    public Page<Order> findAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order findByOrderId(long orderId) {
        return orderRepository.findOne(orderId);
    }

    @Override
    public void deleteByOrderId(long orderId) {
        orderRepository.delete(orderId);
    }

    //    @HystrixCommand(fallbackMethod = "processOrderDeliveryFallback")
    private Order processOrderDelivery(Order order) {

        //String runningOrderDeliveryService = "http://order-delivery-service";
        return this.restTemplate.postForObject(runningOrderDeliveryService + "/orderDelivery/", order, Order.class);
    }

    public void processOrderDeliveryFallback(Order order) {
        log.error("Hystrix Fallback Method. Unable to make order delivery service rest api call.");
    }

    //    @HystrixCommand(fallbackMethod = "processPaymentFallback")
    private Payment processPayment(Payment payment) {

        //String runningPaymentService = "http://payment-service";
        return this.restTemplate.postForObject(runningPaymentService + "/payments/", payment, Payment.class);
    }

    public void processPaymentFallback(Order order) {
        log.error("Hystrix Fallback Method. Unable to make payment service rest api call.");
    }

}
