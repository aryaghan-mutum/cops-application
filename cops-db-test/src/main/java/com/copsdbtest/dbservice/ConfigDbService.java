package com.copsdbtest.dbservice;

import com.copsdbtest.constants.DbConstants;
import com.copsdbtest.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.Description;

import java.sql.*;

@Slf4j
public class ConfigDbService {

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
    }

    @Description("log sql exception")
    public static void printSQLException(SQLException ex) {
        for (Throwable throwable : ex) {
            if (throwable instanceof SQLException) {
                throwable.printStackTrace(System.err);
                log.error("SQLState: " + ((SQLException) throwable).getSQLState());
                log.error("Error Code: " + ((SQLException) throwable).getErrorCode());
                log.error("Message: " + throwable.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    log.info("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public long insertCustomer(CustomerDto customerDto) {
        String SQL = "INSERT INTO customers \n" +
                "(customer_id, customer_name, contact_name, address, city, postal_code, country)\n" +
                "VALUES ('fdsfdsf4', 'Bob', 'James', 'soem st', 'Miami', '2323', 'USA')";

        long id = 0;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, customerDto.getCustomerId());
            pstmt.setString(2, customerDto.getCustomerName());
            pstmt.setString(3, customerDto.getContactName());
            pstmt.setString(4, customerDto.getAddress());
            pstmt.setString(5, customerDto.getCity());
            pstmt.setString(6, customerDto.getPostalCode());
            pstmt.setString(7, customerDto.getCountry());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }
}
