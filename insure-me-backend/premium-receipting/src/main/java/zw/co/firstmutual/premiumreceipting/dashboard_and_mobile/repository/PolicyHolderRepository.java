package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.PolicyHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PolicyHolderRepository extends JpaRepository<PolicyHolder, Long> {

//    List<PolicyHolder> findAllByOrderByTimeDesc();



    Optional<PolicyHolder> findByPolicyNumber(String policyNumber);
    List<PolicyHolder> findAllByPolicyNumber(String policyNumber);

    List<PolicyHolder> findAllByPhoneNumber(String searchPhone);

    List<PolicyHolder> findAllByIdNumber(String searchID);

    List<PolicyHolder> findAllByPolicyNumberIn(List<String> policyHolderList);

    Optional<PolicyHolder> findFirstByPolicyNumber(String policyNumber);
}

