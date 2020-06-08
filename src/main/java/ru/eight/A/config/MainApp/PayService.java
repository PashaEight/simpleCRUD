package ru.eight.A.config.MainApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class PayService {

    @Autowired
    public PayService(PayRepository payRepository) {
        this.payRepository = payRepository;
    }

    PayRepository payRepository;
    BufferedReader reader;

    @PostConstruct
    public void init() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void execCommand() throws Exception {
        String commandWithParams = reader.readLine();
        String command = Utils.getCommand(commandWithParams);

        switch (command) {
            case "insert":
                insert(commandWithParams);
                break;
            case "print":
                getAllPayments();
                break;
            case "getPay":
                printPaymentById(14);
                break;
            case "exit":
                System.exit(0);
            default:
                System.out.println("unknown command");
                break;
        }
    }

    public void insert(String s) throws Exception {
        try {
            Payment payment = Utils.createPay(s);
            payRepository.insertPay(payment);
        } catch (RuntimeException e) {
            System.out.println("record was not added: " + e.getMessage());
        }
    }

    public List<Payment> getAllPayments() {
        return payRepository.getPayList();
    }

    public void printPaymentById(int payId) {
        System.out.println(payRepository.getPaymentById(payId).toString());
    }

    public int getPaymentAmountById(int id) {
        Payment payment = payRepository.getPaymentById(id);
        return payment.getAmount();
    }

    public void start() throws Exception {
        while (true) {
            Utils.printMenu();
            execCommand();
        }
    }
}
