package demo.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {
    @RestResource(path = "restaurantName", rel = "by-name")
    Page<Restaurant> findByRestaurantName (@Param("restaurantName") String restaurantName, Pageable pageable);
}
