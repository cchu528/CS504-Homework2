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
public class Payment {
    @Id
    private Long paymentId;

    private String nameOnCard;
    private String cardNumber;
    private String securityCode;
    private Date expirationDate;
    private String zipCode;
    private Date timestamp;
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
