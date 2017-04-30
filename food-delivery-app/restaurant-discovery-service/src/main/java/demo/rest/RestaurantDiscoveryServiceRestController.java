package demo.rest;

import demo.domain.Restaurant;
import demo.domain.RestaurantRepository;
import demo.service.RestaurantDiscoveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class RestaurantDiscoveryServiceRestController {

    @Autowired
    private RestaurantDiscoveryService restaurantDiscoveryService;

    @RequestMapping(value = "/restaurants", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<Restaurant> restaurants) throws Exception {
        log.info("/restaurants post " + restaurants.toString());
        Iterable<Restaurant> list = this.restaurantDiscoveryService.saveRestaurants(restaurants);
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public Page<Restaurant> getAllRestaurants(@RequestParam(name = "page") Integer page,
                                              @RequestParam(name = "size") Integer size) {
        log.info("/restaurants get");
        return this.restaurantDiscoveryService.getAllRestaurants(new PageRequest(page, size));
    }

    @RequestMapping(value = "/restaurants/{restaurantName}", method = RequestMethod.GET)
    public Page<Restaurant> findByRestaurantName(@PathVariable String restaurantName,
                                                 @RequestParam(name = "page") Integer page,
                                                 @RequestParam(name = "size") Integer size) {
        log.info("/restaurants/" + restaurantName);
        return this.restaurantDiscoveryService.findByRestaurantName(restaurantName, new PageRequest(page, size));
    }
}
