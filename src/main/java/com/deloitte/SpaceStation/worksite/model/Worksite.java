package com.deloitte.SpaceStation.worksite.model;

import com.deloitte.SpaceStation.room.model.Room;
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
    @JoinColumn(name = "worksite_in_room_id")
    private Long worksiteInRoomId;
}
