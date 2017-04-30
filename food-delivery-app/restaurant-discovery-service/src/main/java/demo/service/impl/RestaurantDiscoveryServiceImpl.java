package demo.service.impl;

import demo.domain.Restaurant;
import demo.domain.RestaurantRepository;
import demo.service.RestaurantDiscoveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RestaurantDiscoveryServiceImpl implements RestaurantDiscoveryService {
    private RestaurantRepository repository;

    @Autowired
    public RestaurantDiscoveryServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }


    @Override
    public Iterable<Restaurant> saveRestaurants(List<Restaurant> restaurants) {
        return this.repository.save(restaurants);
    }

    @Override
    public Page<Restaurant> getAllRestaurants(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public Page<Restaurant> findByRestaurantName(String restaurantName, Pageable pageable) {
        return this.repository.findByRestaurantName(restaurantName, pageable);
    }
}
