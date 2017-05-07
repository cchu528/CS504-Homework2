package demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.geo.Point;

import javax.persistence.*;

@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Embeddable
@Entity
@Table(name="menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_name", "restaurant_id"})})
public class Menu {

    public enum MenuCategory {
        APPETIZER, SOUP, SALAD, ENTREE;
    }

    @Id
    @GeneratedValue
    @Column(name = "menu_Id")
    private Long menuId;


    @Column(name = "menu_name")
    private String menuName;
    private String description;

    @Enumerated(EnumType.STRING)
    private MenuCategory category;
    private double price;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    @JsonCreator
    public Menu(@JsonProperty("menuName") String menuName, @JsonProperty("description") String description,
                @JsonProperty("category") MenuCategory category, @JsonProperty("price") double price) {
        this.menuName = menuName;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
