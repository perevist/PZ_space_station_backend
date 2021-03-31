package com.deloitte.SpaceStation.room;

import com.deloitte.SpaceStation.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.NamedNativeQueries;
import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r")
    List<Room> findAll();


//    @Modifying
//    @Query(value = " ",
//            nativeQuery = true)
//    List<Room> getRoomsByAvailability(LocalDate dateStart, LocalDate dateEnd);





}
