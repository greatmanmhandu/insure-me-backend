package com.vozhe.jwt.dashboard_and_mobile.repository;

import com.vozhe.jwt.dashboard_and_mobile.models.Agent;
import com.vozhe.jwt.dashboard_and_mobile.models.FuneralOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuneralOptionRepository extends JpaRepository<FuneralOption, Long> {
}
