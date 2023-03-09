package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Event {

    private Integer id;

    private String name;

    private LocalDate date;

    private BigDecimal price;

    public Event(Integer id, String name, LocalDate date, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.price = price;
    }

    public Event(String name, LocalDate date, BigDecimal price) {
        this.name = name;
        this.date = date;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
