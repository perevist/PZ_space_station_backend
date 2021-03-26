package com.deloitte.SpaceStation.user.account.authority;

import com.deloitte.SpaceStation.user.account.authority.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(Authority.AuthorityName name);
}
