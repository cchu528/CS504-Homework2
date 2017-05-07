package demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;

@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
//@Table(name="food_item", uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_id", "restaurant_id"})})
@Table(name="food_item")
public class FoodItem {

    @Id
    @AttributeOverrides({
            @AttributeOverride(name="menuId", column = @Column(name="menu_id")),
            @AttributeOverride(name="restaurantId", column = @Column(name="restaurant_id"))
    })

    private Long menuId;
    private Long restaurantId;
    private int quantity;
    private double price;
    private String note;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @JsonCreator
    public FoodItem(@JsonProperty("menuId") Long menuId,
                    @JsonProperty("restaurantId") Long restaurantId,
                    @JsonProperty("quantity") int quantity,
                    @JsonProperty("price") double price,
                    @JsonProperty("note") String note) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.quantity = quantity;
        this.price = price;
        this.note = note;
    }
}
