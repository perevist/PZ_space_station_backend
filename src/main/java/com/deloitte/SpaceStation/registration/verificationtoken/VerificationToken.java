package com.deloitte.SpaceStation.registration.verificationtoken;

import com.deloitte.SpaceStation.user.model.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "verification_tokens")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;
}
