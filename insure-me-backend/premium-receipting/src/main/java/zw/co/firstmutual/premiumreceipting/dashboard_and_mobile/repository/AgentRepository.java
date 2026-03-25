package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    Optional<Agent> findById(Long id);

    Agent findByAgentNumber(String agentNumber);

//    Agent findByUsernameOrderByTimeDesc(String username);
}

