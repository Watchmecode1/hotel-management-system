package com.hotel.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class RoomType {

    @Id
    @Enumerated(EnumType.STRING)
    private Type type;

    private BigDecimal price;

    @OneToMany
    @JoinColumn(name = "room_type_id")
    private Set<Room> rooms;

    public enum Type {
        DOUBLE(2), TRIPLE(3), QUADRUPLE(4), QUINTUPLE(5);

        private final int people;

        Type(int persone) {
            this.people = persone;
        }

        public int getPeople() { return people; }
    }
    
    public RoomType() {}

    public RoomType(Type type, BigDecimal price) {
        this.type = type;
        this.price = price;
    }

    public Type getType() { return type; }

    public void setType(Type type) { this.type = type; }

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }

    public Set<Room> getRooms() { return rooms; }

    public void setRooms(Set<Room> rooms) { this.rooms = rooms; }
}