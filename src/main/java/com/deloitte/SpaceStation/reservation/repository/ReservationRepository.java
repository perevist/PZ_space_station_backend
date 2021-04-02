package com.deloitte.SpaceStation.reservation.repository;

import com.deloitte.SpaceStation.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Eliminate N+1 problem
    @Query("SELECT r FROM Reservation r INNER JOIN FETCH r.reservationMaker INNER JOIN FETCH r.owner " +
            "INNER JOIN FETCH r.worksite")
    List<Reservation> findAll();

    @Query("SELECT r FROM Reservation r WHERE r.worksite.id = :worksiteId AND (" +
            "(r.startDate BETWEEN :startDate AND :endDate) OR " +
            "(r.endDate BETWEEN :startDate AND :endDate) OR " +
            "(r.startDate <= :startDate AND r.endDate >= :endDate))")
    List<Reservation> findAllByBookedWorksite(Long worksiteId, LocalDate startDate, LocalDate endDate);
}
