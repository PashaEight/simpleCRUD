package MainApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class Service {
    @Autowired
    Repository repository;
    BufferedReader reader;

    @PostConstruct
    public void init() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void execCommand() throws Exception {
        String commandWithParams = reader.readLine();
        String command = Utils.getCommand(commandWithParams);

        switch (command) {
            case "create":
                create();
                break;
            case "insert":
                insert(commandWithParams);
                break;
            case "read":
                read();
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
            repository.insertPay(payment);
        } catch (RuntimeException e) {
            System.out.println("record was not added: " + e.getMessage());
        }
    }

    public void read() throws Exception {
        repository.printPayList();
    }

    public void create() throws Exception {
        repository.createTable();
    }

    public void start() throws Exception {
        while (true) {
            Utils.printMenu();
            execCommand();
        }
    }
}
