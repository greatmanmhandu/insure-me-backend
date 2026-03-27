package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.PolicyHolderRequest;
import org.springframework.http.ResponseEntity;

public interface PolicyHolderService {

    ResponseEntity<BaseResult> getAllPolicyHolders(int page, int size);



    ResponseEntity<BaseResult> savePolicyHolders(PolicyHolderRequest policyHolderRequest);

    ResponseEntity<BaseResult> getPolicyHolderById(Long id);

    ResponseEntity<BaseResult> findPolicyHolderByPolicyNumber(String policyNumber);

    ResponseEntity<BaseResult> findPolicyHolderByNationalIDorPhone(String searchID, String searchPhone);

    ResponseEntity<BaseResult> getPolicyHoldersByFilter(String policyHolders);

    ResponseEntity<BaseResult> updatePolicyHolders(PolicyHolderRequest policyHolderRequest);

    ResponseEntity<BaseResult> deletePolicyHolders(Long id);

    ResponseEntity<BaseResult> findListOfPolicyHolderByNationalIDorPhone(String searchID, String searchPhone);
}

