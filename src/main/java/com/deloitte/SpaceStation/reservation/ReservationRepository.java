package com.deloitte.SpaceStation.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Eliminate N+1 problem
    @Query("SELECT r FROM Reservation r INNER JOIN FETCH r.owner")
    List<Reservation> findAll();
}
