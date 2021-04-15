package com.deloitte.SpaceStation.reservation.model;

import com.deloitte.SpaceStation.worksite.model.Worksite;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worksite_id")
    private Worksite worksite;
    @Column(name = "reservation_maker_id")
    private String reservationMakerId;
    @Column(name = "ownerId")
    private String ownerId;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
}
