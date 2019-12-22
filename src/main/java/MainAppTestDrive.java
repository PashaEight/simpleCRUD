import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainAppTestDrive {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        MainApp td = new MainApp();
        td.start();

        String command;
        while (true) {
            MainApp.printMenu();
            command = reader.readLine();
            switch (command) {
                case "create":
                    td.createTable();
                    break;
                case "insert":
                    td.insertPay();
                    break;
                case "read":
                    td.getPays();
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
