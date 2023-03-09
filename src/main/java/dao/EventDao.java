package dao;

import model.Event;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

public class EventDao {

    public void createEvent(Event event, Connection connection) {
        String sql = "INSERT INTO events (name,date,price) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, event.getName());
            preparedStatement.setObject(2, event.getDate());
            preparedStatement.setObject(3, event.getPrice());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Event findOneByNameAndDate(String eventName, LocalDate dateEvent, Connection connection) {
        String sql = "SELECT * FROM events WHERE name=? AND date=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, eventName);
            preparedStatement.setObject(2, dateEvent);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Event(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getBigDecimal("price"));
            } else {
                throw new RuntimeException("Нет такого ивента");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Long getCountCustomerById(Integer eventId, Connection connection) {
        String sql = "SELECT COUNT(*) FROM events e " +
                "JOIN tickets t ON e.id = t.event_id " +
                "JOIN customers c on c.id = t.customer_id\n" +
                "WHERE e.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, eventId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public BigDecimal getIncomesById(Integer eventId, Connection connection) {
        String sql = "SELECT sum(price) FROM events e " +
                "JOIN tickets t ON e.id = t.event_id " +
                "JOIN customers c on c.id = t.customer_id\n" +
                "WHERE e.id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, eventId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBigDecimal("sum");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
