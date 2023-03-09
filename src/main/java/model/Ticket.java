package model;

public class Ticket {

    private Integer id;

    private Integer customerId;

    private Integer eventId;

    public Ticket(Integer id, Integer customerId, Integer eventId) {
        this.id = id;
        this.customerId = customerId;
        this.eventId = eventId;
    }

    public Ticket(Integer customerId, Integer eventId) {
        this.customerId = customerId;
        this.eventId = eventId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", eventId=" + eventId +
                '}';
    }
}
