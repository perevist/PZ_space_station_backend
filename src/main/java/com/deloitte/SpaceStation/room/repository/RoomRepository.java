package com.deloitte.SpaceStation.room.repository;

import com.deloitte.SpaceStation.room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r.id NOT IN (" +
            "SELECT w.room.id FROM Worksite w JOIN Reservation re ON w = re.worksite " +
            "WHERE (re.startDate BETWEEN :startDate AND :endDate) " +
            "OR (re.endDate BETWEEN :startDate AND :endDate) " +
            "OR (re.startDate <= :startDate AND re.endDate >= :endDate)" +
            "GROUP BY w.room " +
            "HAVING COUNT(re.worksite.id) >= (SELECT r2.numberOfWorksites FROM Room r2 WHERE r2 = w.room))")
    List<Room> getAllByAvailabilityDate(LocalDate startDate, LocalDate endDate);

    @Query("SELECT r FROM Room r WHERE r.floor = :floor AND r.id NOT IN (" +
            "SELECT w.room.id FROM Worksite w JOIN Reservation re ON w = re.worksite " +
            "WHERE (re.startDate BETWEEN :startDate AND :endDate) " +
            "OR (re.endDate BETWEEN :startDate AND :endDate) " +
            "OR (re.startDate <= :startDate AND re.endDate >= :endDate)" +
            "GROUP BY w.room " +
            "HAVING COUNT(re.worksite.id) >= (SELECT r2.numberOfWorksites FROM Room r2 WHERE r2 = w.room))")
    List<Room> getAllByFloorAndAvailabilityDate(int floor, LocalDate startDate, LocalDate endDate);

    @Query("SELECT r FROM Room r WHERE r.name = :name")
    List<Room> findAllByName(String name);

    List<Room> getAllByFloor(int floor);
}
