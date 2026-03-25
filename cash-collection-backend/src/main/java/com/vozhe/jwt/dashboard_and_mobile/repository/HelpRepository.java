package com.vozhe.jwt.dashboard_and_mobile.repository;

import com.vozhe.jwt.dashboard_and_mobile.models.Help;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HelpRepository extends JpaRepository<Help, Long> {
}
