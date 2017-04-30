package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name="order")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @OneToMany(cascade = CascadeType.ALL, fetch = EAGER, orphanRemoval = true, mappedBy = "order")
    @JsonManagedReference
    private Set<FoodItem> items = new HashSet<>();

    @Column(name = "payment_id")
    private Long paymentId;
    private Date timestamp = new Date();
    @Column(name = "estimateDeliveryTime")
    private int estimateDeliveryTimeInMinutes;

    @JsonCreator
    public Order(@JsonProperty("deliveryAddress") String deliveryAddress,
                 @JsonProperty("items") Set<FoodItem> items) {
        this.deliveryAddress = deliveryAddress;
        this.items = items;
    }

    public double getTotal() {
        double total = 0;
        if (this.items != null) {
            for (FoodItem item : items) {
                total += item.getPrice();
            }
        }
        return total;
    }
}
