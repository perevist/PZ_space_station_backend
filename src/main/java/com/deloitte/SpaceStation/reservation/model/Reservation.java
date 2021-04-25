package com.deloitte.SpaceStation.reservation.model;

import com.deloitte.SpaceStation.worksite.model.Worksite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
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

    public Reservation(){
    }
}
