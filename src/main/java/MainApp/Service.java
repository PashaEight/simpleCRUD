package MainApp;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Service {
    @Autowired
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
                printAllPayments();
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

    public void printAllPayments() {
        payRepository.printPayList();
    }

    public void printPaymentById(int payId) {
        System.out.println(payRepository.getPaymentById(payId).toString());
    }

    public void start() throws Exception {
        while (true) {
            Utils.printMenu();
            execCommand();
        }
    }
}
