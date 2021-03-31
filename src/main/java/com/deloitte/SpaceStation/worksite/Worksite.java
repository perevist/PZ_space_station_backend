package com.deloitte.SpaceStation.worksite;

import com.deloitte.SpaceStation.room.Room;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "worksites")
public class Worksite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
    private Long site;
}
