package demo.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.Date;

@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "name_on_card")
    private String nameOnCard;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "security_code")
    private String securityCode;
    @Column(name = "expiration_date")
    private Date expirationDate;
    @Column(name = "zip_code")
    private String zipCode;
    private Date timestamp = new Date();
    private double amount;

    @JsonCreator
    public Payment(@JsonProperty("nameOnCard") String nameOnCard,
                   @JsonProperty("cardNumber") String cardNumber,
                   @JsonProperty("securityCode") String securityCode,
                   @JsonProperty("expirationDate") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy") Date expirationDate,
                   @JsonProperty("zipCode") String zipCode,
                   @JsonProperty("amount") double amount) {
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.expirationDate = expirationDate;
        this.zipCode = zipCode;
        this.amount = amount;
    }
}
