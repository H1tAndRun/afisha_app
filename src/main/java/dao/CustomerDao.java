package dao;

import model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {

    public void createCustomer(Customer customer, Connection connection) {
        String sql = "INSERT INTO customers (email) VALUES (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getEmail());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Customer findOneByEmail(String email, Connection connection) {
        String sql = "SELECT * FROM customers WHERE email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Customer(resultSet.getInt("id"),
                        resultSet.getString("email"));
            } else {
                throw new RuntimeException("Нет такого покупателя");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long getCountTicketById(Integer customerId, Connection connection) {
        String sql = "select count(*) from tickets where customer_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
