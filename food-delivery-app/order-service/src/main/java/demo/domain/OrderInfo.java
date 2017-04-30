package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderInfo {
    private Order order;
    private Payment payment;

    @JsonCreator
    public OrderInfo(@JsonProperty("order") Order order,
                     @JsonProperty("payment") Payment payment) {
        this.order = order;
        this.payment = payment;
    }
}
