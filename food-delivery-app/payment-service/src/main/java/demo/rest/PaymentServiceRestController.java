package demo.rest;

import demo.domain.Payment;
import demo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentServiceRestController {

    private PaymentService paymentService;

    @Autowired
    public PaymentServiceRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(value = "/payments", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Payment upload(@RequestBody Payment payment) throws Exception {
        // log.info("/payment post " + payment.toString());
        return this.paymentService.processPayment(payment);
    }

    @RequestMapping(value = "/payments/{paymentId}", method = RequestMethod.GET)
    public Payment findByPaymentId(@PathVariable String paymentId){
        Payment payment = this.paymentService.findByPaymentId(Long.valueOf(paymentId));
        // log.info(payment.toString());
        return payment;
    }
}
