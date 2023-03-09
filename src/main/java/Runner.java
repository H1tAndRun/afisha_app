import dao.CustomerDao;
import dao.EventDao;
import dao.TicketDao;
import model.Customer;
import model.Event;
import model.Ticket;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Runner {

    private static final String URL = "jdbc:postgresql://localhost:5432/afisha";

    private static final String USERNAME = "postgres";

    private static final String PASSWORD = "556677";


    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            EventDao eventDao = new EventDao();
            CustomerDao customerDao = new CustomerDao();
            TicketDao ticketDao = new TicketDao();

            /* Этап 2
            Event event = new Event("Rise entertainment",
                    LocalDate.of(2021, 02, 22),
                    new BigDecimal(500));
            eventDao.createEvent(event,connection);
            Event eventFromDb = eventDao.findOneByNameAndDate("Rise entertainment",
                    LocalDate.of(2021, 02, 22),
                    connection);
            Customer customer = new Customer("superman@gmail.com");
            customerDao.createCustomer(customer,connection);
            Customer customerFromDb = customerDao.findOneByEmail("superman@gmail.com",connection);
            Ticket ticket = new Ticket(customerFromDb.getId(),eventFromDb.getId());
            ticketDao.createOrder(ticket,connection);*/

            Long countCustomer = eventDao.getCountCustomerById(1, connection);
            BigDecimal sumEvent = eventDao.getIncomesById(2,connection);
            Long countTicket = ticketDao.getCountTickets(connection);
            Long countTicketCustomer = customerDao.getCountTicketById(1,connection);

            Customer oneByEmail = customerDao.findOneByEmail("superman@gmail.com", connection);
        }
    }
}
