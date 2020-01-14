package MainApp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Repository {
    Connection connection;
    Statement stmt;
    BufferedReader reader;

    public void init() throws IllegalArgumentException {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            stmt = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("couldn't connect to DB");
        }
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void createTable() throws Exception {
        String sql = "CREATE TABLE PAY " +
                "(pay_id INTEGER, " +
                "amount DOUBLE, " +
                "PRIMARY KEY (pay_id))";
        try {
            stmt.execute("DROP TABLE PAY");
        } catch (Exception ex) {
            System.out.println("table PAY not found, nothing deleted");
        }
        stmt.execute(sql);
        System.out.println("table PAY created");
    }

    public void insertPay() throws Exception {
        System.out.println("pay_id: ");
        int pay_id = Integer.parseInt(reader.readLine());
        System.out.println("amount: ");
        double amount = Double.parseDouble(reader.readLine());
        String sql = "INSERT INTO PAY " +
                "VALUES (?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(pay_id));
            pstmt.setString(2, String.valueOf(amount));
            pstmt.executeUpdate();
            System.out.println("record inserted");
        } catch (Exception e) {
            System.out.println("pay wasn't added");
            throw new IllegalArgumentException(e);
        }
    }

//    public void getPayList() throws Exception {
//        paymentList.clear();
//        String sql = "SELECT pay_id, amount from PAY";
//        ResultSet res = stmt.executeQuery(sql);
//
//        while (res.next()) {
//            int pay_id = res.getInt("pay_id");
//            int amount = res.getInt("amount");
//            paymentList.add(new MainApp.Payment(pay_id, amount));
//        }
//
//        for (MainApp.Payment p : paymentList) {
//            System.out.println(p);
//        }
//    }
}
