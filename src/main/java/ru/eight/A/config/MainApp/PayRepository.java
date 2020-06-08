package ru.eight.A.config.MainApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class PayRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() throws Exception {
        createTable();
    }

    public void createTable() {
        try {
            jdbcTemplate.update("DROP TABLE PAY");
        } catch (Exception e) {
        }
        String sql;
        sql = "CREATE TABLE PAY " +
                "(ID INTEGER, " +
                "AMOUNT DOUBLE, " +
                "PRIMARY KEY (ID))";
        jdbcTemplate.update(sql);

        sql = "INSERT INTO PAY VALUES (14, 88)";
        jdbcTemplate.update(sql);

        sql = "INSERT INTO PAY VALUES (15, 89)";
        jdbcTemplate.update(sql);

        System.out.println("table PAY created");
    }

    public void insertPay(Payment payment) {
        try {
            int id = payment.getId();
            int amount = payment.getAmount();
            jdbcTemplate.update("INSERT INTO PAY VALUES (?, ?)", id, amount);
            System.out.println("record inserted");
        } catch (Exception e) {
            System.out.print("record was not added: " + e.getMessage());
        }
    }

    public Payment getPaymentById(int payId) {
        String sql = "SELECT * from PAY WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{payId}, new PaymentRowMapper());
    }

    public List<Payment> getPayList() {
        String sql = "SELECT * FROM PAY";

        List<Payment> paymentList = jdbcTemplate.query(
                sql,
                new PaymentRowMapper());

        return paymentList;
    }
}
