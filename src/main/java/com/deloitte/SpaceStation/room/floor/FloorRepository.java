package com.deloitte.SpaceStation.room.floor;

import com.deloitte.SpaceStation.room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FloorRepository extends JpaRepository<Room, Long> {

    @Query("SELECT COUNT(DISTINCT r.floor) FROM Room r")
    Long getFloorQuantity();

}
