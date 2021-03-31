package com.deloitte.SpaceStation.user.repository;

import com.deloitte.SpaceStation.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u INNER JOIN FETCH u.account WHERE u.id=?1")
    Optional<User> findById(Long id);

    @Query("SELECT u FROM User u INNER JOIN FETCH u.account WHERE u.email=?1")
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u INNER JOIN FETCH u.account WHERE u.account.username=?1")
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u INNER JOIN FETCH u.account")
    List<User> findAll();

    @Query("SELECT u FROM User u INNER JOIN FETCH u.account WHERE u.firstName=?1 AND u.lastName=?2")
    List<User> findAllByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT u FROM User u INNER JOIN FETCH u.account WHERE u.firstName=?1")
    List<User> findAllByFirstName(String firstName);

    @Query("SELECT u FROM User u INNER JOIN FETCH u.account WHERE u.lastName=?1")
    List<User> findAllByLastName(String lastName);
}
