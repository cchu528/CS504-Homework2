package demo.service;

import demo.domain.Payment;

public interface PaymentService {
    Payment processPayment(Payment payment);

    Payment findByPaymentId(long paymentId);
}
