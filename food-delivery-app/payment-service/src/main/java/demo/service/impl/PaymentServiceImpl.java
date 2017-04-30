package demo.service.impl;

import demo.domain.Payment;
import demo.domain.PaymentRepository;
import demo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository repository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment findByPaymentId(long paymentId) {

        // TO DO: call third party rest api for payment information.
        return this.repository.findOne(paymentId);
    }

//    @HystrixCommand(fallbackMethod = "processPaymentFallback")
    public Payment processPayment(Payment payment) {

        // TO EXPAND: call third party rest api to process payment instead here.
        return repository.save(payment);
    }

    public void processPaymentFallback(Payment payment) {
        log.error("Hystrix Fallback Method. Unable to make third party payment rest api call.");
    }

}
