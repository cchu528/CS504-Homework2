package demo.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Data
@Table(name="restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = "restaurant_name")})
public class Restaurant {

    @Id
    @GeneratedValue
    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "restaurant_name")
    private String restaurantName;
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private Set<Menu> menus = new HashSet<>();

    @JsonCreator
    public Restaurant(@JsonProperty("restaurantName") String restaurantName,
                      @JsonProperty("address") String address,
                      @JsonProperty("phoneNumber") String phoneNumber,
                      @JsonProperty("menus") Set<Menu> menus) {
        this.restaurantName = restaurantName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.menus = menus;
        if(this.menus != null) {
            for (Menu menu: menus) {
                menu.setRestaurant(this);
            }
        }
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
        if(this.menus != null) {
            for (Menu menu: menus) {
                menu.setRestaurant(this);
            }
        }
    }

}
