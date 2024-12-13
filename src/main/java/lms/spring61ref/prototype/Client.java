package lms.spring61ref.prototype;

import lms.spring61ref.prototype.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

public class Client {

    @Autowired
    PaymentService paymentService;

    public static void main(String[] args) {

    }

}
