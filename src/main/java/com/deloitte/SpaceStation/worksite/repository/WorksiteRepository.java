package com.deloitte.SpaceStation.worksite.repository;

import com.deloitte.SpaceStation.worksite.model.Worksite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface WorksiteRepository extends JpaRepository<Worksite, Long> {

    @Query("SELECT w FROM Worksite w WHERE w.id NOT IN (" +
            "SELECT w2.id FROM Worksite w2 JOIN Reservation re ON w2 = re.worksite " +
            "WHERE (re.startDate BETWEEN :startDate AND :endDate) " +
            "OR (re.endDate BETWEEN :startDate AND :endDate) " +
            "OR (re.startDate <= :startDate AND re.endDate >= :endDate)" +
            "GROUP BY w2 " + ")")
    List<Worksite> getAllByAvailabilityDate(LocalDate startDate, LocalDate endDate);


    @Query("SELECT w FROM Worksite w WHERE w.room.id = :roomId AND w.id NOT IN (" +
            "SELECT w2.id FROM Worksite w2 JOIN Reservation re ON w2 = re.worksite " +
            "WHERE (re.startDate BETWEEN :startDate AND :endDate) " +
            "OR (re.endDate BETWEEN :startDate AND :endDate) " +
            "OR (re.startDate <= :startDate AND re.endDate >= :endDate)" +
            "GROUP BY w2 " + ")")
    List<Worksite> getAllByRoomAndAvailabilityDate(Long roomId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT w FROM Worksite w WHERE w.room.id = :roomId ")
    List<Worksite> getAllByRoom(Long roomId);
}
