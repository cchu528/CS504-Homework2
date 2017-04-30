package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;


@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Order {

    private Long orderId;

    private String deliveryAddress;

    private Set<FoodItem> items = new HashSet<>();
    private Long paymentId;
    private Date timestamp = new Date();
    private int estimateDeliveryTimeInMinutes;

    @JsonCreator
    public Order(@JsonProperty("deliveryAddress") String deliveryAddress,
                 @JsonProperty("items") Set<FoodItem> items) {
        this.deliveryAddress = deliveryAddress;
        this.items = items;
    }
}
