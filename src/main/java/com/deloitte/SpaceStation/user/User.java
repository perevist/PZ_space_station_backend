package com.deloitte.SpaceStation.user;

import com.deloitte.SpaceStation.user.account.Account;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    private Account account;
}
