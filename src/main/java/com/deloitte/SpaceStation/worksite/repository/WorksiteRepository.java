package com.deloitte.SpaceStation.worksite.repository;

import com.deloitte.SpaceStation.worksite.model.Worksite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorksiteRepository extends JpaRepository<Worksite, Long> {
//    Optional<Worksite> findById();
}
