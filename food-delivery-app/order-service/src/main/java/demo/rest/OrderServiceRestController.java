package demo.rest;

import demo.domain.Order;
import demo.domain.OrderInfo;
import demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class OrderServiceRestController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public JSONObject upload(@RequestBody OrderInfo orderInfo) throws Exception {
        //log.info("/orders post " + orderInfo.toString());
        Order order = this.orderService.processOrderInformation(orderInfo);
        return convertToJson(order);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public Page<Order> getAllOrders(@RequestParam(name = "page") Integer page,
                                              @RequestParam(name = "size") Integer size) {
        log.info("/orders get");
        return orderService.findAllOrders(new PageRequest(page, size));
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    public Order findByOrderId(@PathVariable String orderId) {
        log.info("/orders/" + orderId);
        return orderService.findByOrderId(Long.valueOf(orderId));
    }

    private JSONObject convertToJson(Order order) {
        JSONObject jsonObject = null;

        if (order != null) {
            jsonObject = new JSONObject();
            jsonObject.put("orderId", order.getOrderId());
            jsonObject.put("timestamp", order.getTimestamp());
            jsonObject.put("estimateDeliveryTime", order.getEstimateDeliveryTimeInMinutes());
        }

        return jsonObject;
    }
}
