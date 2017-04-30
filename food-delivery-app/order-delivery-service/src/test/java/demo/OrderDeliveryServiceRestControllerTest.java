package demo;

import demo.domain.FoodItem;
import demo.domain.Order;
import demo.rest.OrderDeliveryServiceRestController;
import demo.service.OrderDeliveryService;
import demo.service.impl.OrderDeliveryServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class OrderDeliveryServiceRestControllerTest {

    private OrderDeliveryService service;
    private OrderDeliveryServiceRestController controller;
    private Order inputOrder;

    @Before
    public void setupSpy() {
        service = spy(new OrderDeliveryServiceImpl());
        controller = new OrderDeliveryServiceRestController(service);
        Set<FoodItem> inputFoodItems = new HashSet<FoodItem>();
        inputFoodItems.add(new FoodItem(Long.valueOf("1"), Long.valueOf("1"), 1, 8.95, "Hot"));
        inputFoodItems.add(new FoodItem(Long.valueOf("3"), Long.valueOf("1"), 1, 8.95, ""));
        inputOrder = new Order("901 Massachusetts Ave NW, Washington, DC 20001", inputFoodItems);
    }

    @Test
    public void whenProcessOrderDelivery() {
        int estimateDeliveryTimeInMinutes = service.processOrderDelivery(inputOrder).getEstimateDeliveryTimeInMinutes();
        assertThat(estimateDeliveryTimeInMinutes >= 5 && estimateDeliveryTimeInMinutes <= 60 );
    }
}
