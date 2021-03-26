package com.deloitte.SpaceStation.user.account.authority;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    public enum AuthorityName {
        ROLE_USER,
        ROLE_ADMIN
    }
}