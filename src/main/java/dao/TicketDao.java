package dao;

import model.Ticket;
import java.sql.*;

public class TicketDao {

    public void createOrder(Ticket ticket, Connection connection) {
        String sql = "INSERT INTO tickets (customer_id,event_id) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticket.getCustomerId());
            preparedStatement.setInt(2, ticket.getEventId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ticket findOneByEmail(String email, Connection connection) {
        String sql = "SELECT * FROM tickets JOIN customers " +
                "ON tickets.customer_id = customers.id WHERE email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Ticket(resultSet.getInt("id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("event_id"));
            } else {
                throw new RuntimeException("Нет такого тикета");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long getCountTickets(Connection connection) {
        String sql = "SELECT COUNT(*) FROM tickets";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
