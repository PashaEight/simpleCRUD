package ru.eight.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PayRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<Payment> rm = new RowMapper<Payment>() {
        @Override
        public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Payment payment = new Payment();
            payment.setId(rs.getInt("ID"));
            payment.setAmount(rs.getInt("AMOUNT"));
            return payment;
        }
    };

    @PostConstruct
    public void init() throws Exception {
        createTable();
    }

    public void createTable() {
        jdbcTemplate.update("DROP TABLE PAY");
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
    }

    public void insertPay(Payment payment) {
        int id = payment.getId();
        int amount = payment.getAmount();
        jdbcTemplate.update("INSERT INTO PAY VALUES (?, ?)", id, amount);
    }

    public Payment getPaymentById(int payId) {
        String sql = "SELECT * from PAY WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{payId}, rm);
    }

    public List<Payment> getPayList() {
        String sql = "SELECT * FROM PAY";
        List<Payment> paymentList = jdbcTemplate.query(sql, new RowMapper<Payment>() {
            @Override
            public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
                Payment payment = new Payment();
                payment.setId(rs.getInt("ID"));
                payment.setAmount(rs.getInt("AMOUNT"));
                return payment;
            }
        });

        return paymentList;
    }
}
