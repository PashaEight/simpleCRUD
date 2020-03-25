package MainApp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;

@Component
public class Repository {
    Connection connection;
    Statement stmt;
    @Value("${databaseUrl}")
    String url;
    @Value("${databaseLogin}")
    String login;
    @Value("${databasePass}")
    String password;

    @PostConstruct
    public void init() throws IllegalArgumentException {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url, login, password);
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
        }
        stmt.execute(sql);
        System.out.println("table PAY created");
    }

    public void insertPay(Payment payment) {
        String sql = "INSERT INTO PAY " +
                "VALUES (?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, payment.getId());
            pstmt.setInt(2, payment.getAmount());
            pstmt.executeUpdate();
            System.out.println("record inserted");
        } catch (Exception e) {
            System.out.print("record was not added: " + e.getMessage());
        }
    }

    public void printPayList() {
        String sql = "SELECT pay_id, amount from PAY";
        try {
            ResultSet res = stmt.executeQuery(sql);

            // 2 раза делается .next(), пропускается первая запись
            if (res.next()) {
                while (res.next()) {
                    int pay_id = res.getInt("pay_id");
                    int amount = res.getInt("amount");
                    System.out.println("pay_id: " + pay_id + ", " + "amount: " + amount);
                }
            } else {
                System.out.println("table is empty");
            }
        }
        catch (Exception e) {
            System.out.println("need to create table first");
        }
    }
}
