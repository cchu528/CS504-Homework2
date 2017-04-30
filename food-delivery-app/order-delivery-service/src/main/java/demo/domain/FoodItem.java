package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class FoodItem {

    private Long menuId;
    private Long restaurantId;
    private int quantity;
    private double price;
    private String note;

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
