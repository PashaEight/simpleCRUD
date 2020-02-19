package MainApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class Service {
    @Autowired
    Repository repository;
    List<Payment> paymentList;
    Payment payment;
    BufferedReader reader;

    public void init() {
        repository.init();
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void execCommand() throws Exception {
        String commandWithParams = reader.readLine();

        List<String> args = Utils.getParams(commandWithParams);
        String id = args.get(0);
        String amount = args.get(1);
        String command = Utils.getCommand(commandWithParams);

        switch (command) {
            case "insert":
                payment = new Payment(id, amount);
                insert(payment);
                break;
            case "read":
                read();
                break;
            default:
                throw new RuntimeException();
        }
    }

    public void insert(Payment payment) throws Exception {
        repository.insertPay(payment);
    }

    public void read() throws Exception{
        repository.getPayList();
    }

    public void start() throws Exception {
        while (true) {
            Utils.printMenu();
            execCommand();
        }
    }
}
