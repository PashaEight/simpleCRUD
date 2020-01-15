package MainApp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Service {
    Repository repository;
    List<Payment> paymentList;
    Payment payment;
    BufferedReader reader;

    public void init() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        repository = context.getBean("RepositoryBean", Repository.class);
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
