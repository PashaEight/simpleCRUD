import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestDrive {
    Connection connection;
    Statement stmt;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        TestDrive td = new TestDrive();
        td.start();

        String command = "";
        printMenu();
        while (true) {
            command = reader.readLine();
            switch (command) {
                case "create":
                    td.createTable();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    throw new IllegalAccessException("no such command");
            }
        }
    }

    public static void printMenu() {
        System.out.println("type a command: ");
        System.out.println("create - create table");
        System.out.println("exit - exit program");
    }

    public void start() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            stmt = connection.createStatement();
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }

    public void createTable() throws Exception{
        String sql = "CREATE TABLE PAY " +
                "(pay_id INTEGER, " +
                "amount INTEGER, " +
                "PRIMARY KEY (pay_id))";
        try {
            stmt.execute("DROP TABLE PAY");
        } catch (Exception ex) {
            System.out.println("table PAY not found");
        }
        stmt.execute(sql);
        System.out.println("table PAY created");
    }
}
