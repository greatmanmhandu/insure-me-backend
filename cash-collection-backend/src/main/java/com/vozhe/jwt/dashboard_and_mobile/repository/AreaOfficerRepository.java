package com.vozhe.jwt.dashboard_and_mobile.repository;

import com.vozhe.jwt.dashboard_and_mobile.models.AreaOfficer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaOfficerRepository extends JpaRepository<AreaOfficer, Long> {

    Optional<AreaOfficer> findById(Long id);

    AreaOfficer findByAreaOfficerNumber(String areaOfficerNumber);

//    AreaOfficer findByUsernameOrderByTimeDesc(String username);
}
