import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ServiceBean service = context.getBean("Service", ServiceBean.class);
        service.start();

        String command;
        while (true) {
            ServiceBean.printMenu();
            command = reader.readLine();
            switch (command) {
                case "create":
                    service.createTable();
                    break;
                case "insert":
                    service.insertPay();
                    break;
                case "read":
                    service.getPays();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    throw new IllegalAccessException("no such command");
            }
        }
    }
}
