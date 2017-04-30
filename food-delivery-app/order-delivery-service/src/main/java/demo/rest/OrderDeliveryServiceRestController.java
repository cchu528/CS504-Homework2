package demo.rest;

import demo.domain.Order;
import demo.service.OrderDeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class OrderDeliveryServiceRestController {

    private OrderDeliveryService orderDeliveryService;

    @Autowired
    public OrderDeliveryServiceRestController(OrderDeliveryService orderDeliveryService) {
        this.orderDeliveryService = orderDeliveryService;
    }

    @RequestMapping(value = "/orderDelverys", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Order upload(@RequestBody Order order) throws Exception {
        log.info("/orderDelivery post " + order.toString());
        return this.orderDeliveryService.processOrderDelivery(order);
    }
}
