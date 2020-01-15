package MainApp;

import java.sql.*;

public class Repository {
    Connection connection;
    Statement stmt;

    public void init() throws IllegalArgumentException {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            stmt = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("couldn't connect to DB");
        }
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

    public void insertPay(Payment payment) throws Exception {
        String sql = "INSERT INTO PAY " +
                "VALUES (?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, payment.getId());
            pstmt.setInt(2, payment.getAmount());
            pstmt.executeUpdate();
            System.out.println("record inserted");
        } catch (Exception e) {
            System.out.println("pay wasn't added");
            throw new IllegalArgumentException(e);
        }
    }

    public void getPayList() throws Exception {
        String sql = "SELECT pay_id, amount from PAY";
        ResultSet res = stmt.executeQuery(sql);

        while (res.next()) {
            int pay_id = res.getInt("pay_id");
            int amount = res.getInt("amount");
            System.out.println(pay_id + " / " + amount);
        }
    }
}
