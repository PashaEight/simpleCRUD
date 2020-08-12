package ru.eight.App;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PayService {

    @Autowired
    public PayRepository payRepository;

    public ObjectMapper objectMap = new ObjectMapper();

    public Payment createPay(String payAsString) throws IOException{
        Payment payment = objectMap.readValue(payAsString, Payment.class);
        payRepository.insertPay(payment);
        return payment;
    }

    public List<Payment> getAllPayments() {
        return payRepository.getPayList();
    }

    public Payment getPay(String idAsString) {
        int id = Integer.parseInt(idAsString);
        return payRepository.getPaymentById(id);
    }

}
