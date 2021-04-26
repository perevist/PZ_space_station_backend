package com.deloitte.SpaceStation.room.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Builder
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long floor;
    @Column(name = "number_of_worksites")
    private Long numberOfWorksites;
    @Column(name = "dimension_x")
    private Long dimensionX;
    @Column(name = "dimension_y")
    private Long dimensionY;

    public Room() {
    }
}
