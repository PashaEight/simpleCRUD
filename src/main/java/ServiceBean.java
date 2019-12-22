import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceBean {
    Connection connection;
    Statement stmt;
    List<Payment> paymentList;
    BufferedReader reader;

    public static void printMenu() {
        System.out.println();
        System.out.println("type a command: ");
        System.out.println("create - create table pay");
        System.out.println("insert - insert pay");
        System.out.println("read - get pays");
        System.out.println("exit - exit program");
        System.out.println();
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
        reader = new BufferedReader(new InputStreamReader(System.in));
        paymentList = new ArrayList<>();
    }

    public void createTable() throws Exception{
        String sql = "CREATE TABLE PAY " +
                "(pay_id INTEGER, " +
                "amount INTEGER, " +
                "PRIMARY KEY (pay_id))";
        try {
            stmt.execute("DROP TABLE PAY");
        } catch (Exception ex) {
            System.out.println("table PAY not found, nothing deleted");
        }
        stmt.execute(sql);
        System.out.println("table PAY created");
    }

    public void insertPay() throws Exception{
        System.out.println("pay_id: ");
        int pay_id = Integer.parseInt(reader.readLine());
        System.out.println("amount: ");
        int amount = Integer.parseInt(reader.readLine());
        String sql = "INSERT INTO PAY " +
                "VALUES (?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(pay_id));
            pstmt.setString(2, String.valueOf(amount));
            pstmt.executeUpdate();
            System.out.println("record inserted");
        }
        catch (Exception e) {
            System.out.println("pay wasn't added");
        }
    }

    public void getPays() throws Exception{
        paymentList.clear();
        String sql = "SELECT pay_id, amount from PAY";
        ResultSet res = stmt.executeQuery(sql);

        while (res.next()) {
            int pay_id = res.getInt("pay_id");
            int amount = res.getInt("amount");
            paymentList.add(new Payment(pay_id, amount));
        }

        for (Payment p : paymentList) {
            System.out.println(p);
        }
    }

}
