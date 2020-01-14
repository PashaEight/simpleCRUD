package MainApp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Service {
    Repository repository;
    List<Payment> paymentList;
    BufferedReader reader;

    public void init() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        repository = context.getBean("RepositoryBean", Repository.class);
        repository.init();
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void execCommand() throws Exception{
        String commandWithParams = reader.readLine();
        String[] params = Utils.getParams(commandWithParams);
        String command = Utils.getCommand(commandWithParams);
        switch (command) {
            case "insert":
                insert();
                break;
            default:
                throw new RuntimeException();
        }
    }

    public void insert() throws Exception{
        repository.insertPay();
    }

    public void start() throws Exception{
        while (true) {
            Utils.printMenu();
            execCommand();
        }
    }
}
