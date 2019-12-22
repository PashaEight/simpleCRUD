import java.sql.*;

public class TestDrive {
    public static void main(String[] args) throws Exception {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        Statement st = connection.createStatement();

        try {
            st.execute("DROP TABLE PERSON");
        } catch (SQLException sqle) {
            System.out.println("not found table");
        }

        st.execute("CREATE TABLE PERSON (ID INT PRIMARY KEY, FIRSTNAME VARCHAR(64), LASTNAME VARCHAR(64))");

        String sqlInsert = "INSERT INTO PERSON " +
                "VALUES (1, 'Pasha', 'Lyu')";
        st.executeUpdate(sqlInsert);


        PreparedStatement ps = connection.prepareStatement("select ID, FIRSTNAME, LASTNAME from PERSON");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println(rs);
        }

        int id = rs.getInt("ID");
        String firstName = rs.getString("FIRSTNAME");
        String lastName = rs.getString("LASTNAME");
        System.out.println(id + firstName + lastName);

        rs.close();
        ps.close();
        st.close();
        connection.close();
    }
}
