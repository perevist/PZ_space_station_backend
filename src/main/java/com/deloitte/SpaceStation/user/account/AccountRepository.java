package com.deloitte.SpaceStation.user.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a LEFT JOIN FETCH a.authorities WHERE a.username=?1")
    Optional<Account> findByUsername(String username);
}
