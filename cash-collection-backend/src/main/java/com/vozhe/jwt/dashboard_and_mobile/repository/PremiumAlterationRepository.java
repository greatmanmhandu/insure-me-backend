package com.vozhe.jwt.dashboard_and_mobile.repository;

import com.vozhe.jwt.dashboard_and_mobile.models.PremiumPayment;
import com.vozhe.jwt.dashboard_and_mobile.models.PremiumPaymentForAlteration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PremiumAlterationRepository extends JpaRepository<PremiumPaymentForAlteration, Long> {

    List<PremiumPaymentForAlteration>  findAllByAgentNumberAndType(String agentNumber, String type);
}
