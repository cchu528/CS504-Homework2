package demo.service;


import demo.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RestaurantDiscoveryService {

    public Iterable<Restaurant> saveRestaurants(List<Restaurant> restaurants);;
    public Page<Restaurant> getAllRestaurants(Pageable pageable);

    public Page<Restaurant> findByRestaurantName(String restaurantName, Pageable pageable);
}
