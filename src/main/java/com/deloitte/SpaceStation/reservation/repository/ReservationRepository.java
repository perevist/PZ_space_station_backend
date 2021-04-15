package com.deloitte.SpaceStation.reservation.repository;

import com.deloitte.SpaceStation.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Eliminate N+1 problem
    @Query("SELECT r FROM Reservation r INNER JOIN FETCH r.worksite")
    List<Reservation> findAll();

    @Query("SELECT r FROM Reservation r " +
            "WHERE (r.startDate >= :startDate AND r.endDate <= :endDate)")
    List<Reservation> findAllByDate(LocalDate startDate, LocalDate endDate);

    @Query("SELECT r FROM Reservation r " +
            "WHERE ((r.startDate >= :startDate AND r.endDate <= :endDate) AND r.ownerId = :ownerId)")
    List<Reservation> findAllByDateAndOwnerId(LocalDate startDate, LocalDate endDate, String ownerId);

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.ownerId = :ownerId ")
    List<Reservation> findAllByOwnerId(String ownerId);


    @Query("SELECT r FROM Reservation r WHERE r.worksite.id = :worksiteId AND (" +
            "(r.startDate BETWEEN :startDate AND :endDate) OR " +
            "(r.endDate BETWEEN :startDate AND :endDate) OR " +
            "(r.startDate <= :startDate AND r.endDate >= :endDate))")
    List<Reservation> findAllByBookedWorksite(Long worksiteId, LocalDate startDate, LocalDate endDate);

    void deleteById(Long id);


}
