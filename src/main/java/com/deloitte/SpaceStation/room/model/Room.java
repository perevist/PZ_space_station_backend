package com.deloitte.SpaceStation.room.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long floor;
    @Column(name = "number_of_worksites")
    private Long numberOfWorksites;
}
