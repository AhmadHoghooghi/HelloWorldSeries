package com.rhotiz.rws.model;

import javax.persistence.*;

@Entity
public class Room {

    private long id;
    private RoomCategory roomCategory;
    private String name;
    private String description;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne( cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }
    @Column(unique = true, nullable = false,length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
