package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.Impl;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.*;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.PolicyHolderRequest;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository.PolicyHolderRepository;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.PolicyHolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PolicyHolderServiceImpl implements PolicyHolderService {

    private final PolicyHolderRepository policyHolderRepository;


    @Override
    public ResponseEntity<BaseResult> getAllPolicyHolders(int page, int size) {
        // Fetching only requested page from DB (Database-level pagination)
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<PolicyHolder> policyHoldersPage = policyHolderRepository.findAll(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("content", policyHoldersPage.getContent());
        response.put("currentPage", policyHoldersPage.getNumber());
        response.put("totalItems", policyHoldersPage.getTotalElements());
        response.put("totalPages", policyHoldersPage.getTotalPages());

        return ResponseEntity.ok(new BaseResult(response, "policy holders fetched successfully", "00", 200));
    }



    @Override
    public ResponseEntity<BaseResult> savePolicyHolders(PolicyHolderRequest policyHolderRequest) {
        PolicyHolder policyHolder = new PolicyHolder();
        policyHolder.setAddress(policyHolderRequest.getAddress());
        policyHolder.setId(policyHolderRequest.getId());
        policyHolder.setFirstName(policyHolderRequest.getFirstName());
        policyHolder.setLastName(policyHolderRequest.getLastName());
        policyHolder.setIdNumber(policyHolderRequest.getIdNumber());
        policyHolder.setGender(policyHolderRequest.getGender());
        policyHolder.setPhoneNumber(policyHolderRequest.getPhoneNumber());
        policyHolder.setSumAssured(policyHolderRequest.getSumAssured());
        policyHolder.setPolicyNumber(policyHolderRequest.getPolicyNumber());
        policyHolder.setCreationDate(new Date());
        policyHolder.setContractPremium(policyHolderRequest.getPolicyHolderContractPremium());
        policyHolder.setCreatedBy(policyHolderRequest.getCreatedBy());
        policyHolder.setProductType(policyHolderRequest.getProductType());
        policyHolder.setAddress(policyHolderRequest.getAddress());
        policyHolder.setLastModifiedDate(new Date());
        return ResponseEntity.ok(new BaseResult(policyHolderRepository.save(policyHolder), "policy holder saved successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getPolicyHolderById(Long id) {
        return ResponseEntity.ok(new BaseResult(policyHolderRepository.findById(id), "policy holder fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> findPolicyHolderByPolicyNumber(String policyNumber) {
        List<PolicyHolder> allByPolicyNumber = policyHolderRepository.findAllByPolicyNumber(policyNumber);

        PolicyHolder policyHolder = new PolicyHolder();
        if (!allByPolicyNumber.isEmpty()) {
            policyHolder = allByPolicyNumber.get(0); // Getting the first element from the list
        }
        return ResponseEntity.ok(new BaseResult(policyHolder, "policy holder fetched successfully","00",200));
    }


    public static String formatPhoneNumber(String searchPhone) {
        if (searchPhone == null || searchPhone.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }

        // Remove any non-digit characters
        String cleanedNumber = searchPhone.replaceAll("\\D", "");

        // Check if the number starts with '263' (country code) and is followed by the rest
        if (cleanedNumber.startsWith("263")) {
            // Remove the country code
            return cleanedNumber.substring(3);
        } else if (cleanedNumber.startsWith("0")) {
            // If it starts with '0', just remove the leading '0'
            return cleanedNumber.substring(1);
        }

        // If the number is not in the expected formats, return null or throw an exception
        throw new IllegalArgumentException("Invalid phone number format");
    }

    @Override
    public ResponseEntity<BaseResult> findPolicyHolderByNationalIDorPhone(String searchID, String phone) {
        String searchPhone = formatPhoneNumber(phone);
        List<PolicyHolder> searchedPolicyHolders;
        PolicyHolder policyHolder = new PolicyHolder();
        // Convert searchID to uppercase only if it's not empty
        String upperCaseSearchID = searchID.isEmpty() ? "" : searchID.toUpperCase();

        if (!searchPhone.isEmpty() && searchID.isEmpty()) {
            searchedPolicyHolders = policyHolderRepository.findAllByPhoneNumber(searchPhone);
        } else if (searchPhone.isEmpty() && !searchID.isEmpty()) {
            searchedPolicyHolders = policyHolderRepository.findAllByIdNumber(upperCaseSearchID);
        } else if (!searchPhone.isEmpty() && !searchID.isEmpty()) {
            searchedPolicyHolders = policyHolderRepository.findAllByPhoneNumber(searchPhone);
            if (searchedPolicyHolders.isEmpty()) {
                searchedPolicyHolders = policyHolderRepository.findAllByIdNumber(upperCaseSearchID);
            }
        } else {
            searchedPolicyHolders = new ArrayList<>();
        }
        if (!searchedPolicyHolders.isEmpty()) {
            policyHolder = searchedPolicyHolders.get(0); // Getting the first element from the list
        }

        return ResponseEntity.ok(new BaseResult(policyHolder, "Policy holder fetched successfully", "00", 200));
    }

    @Override
    public ResponseEntity<BaseResult> getPolicyHoldersByFilter(String policyHolders) {
        List<PolicyHolder> policyHoldersList ;

            List<String> policyHolderList = Arrays.asList(policyHolders.split(","));
        policyHoldersList =   policyHolderRepository.findAllByPolicyNumberIn(policyHolderList);

        return ResponseEntity.ok(new BaseResult(policyHoldersList, "Policy holders fetched successfully", "00", 200));
    }

    @Override
    public ResponseEntity<BaseResult> updatePolicyHolders(PolicyHolderRequest policyHolderRequest) {
        System.out.println(policyHolderRequest.getId());
        Optional<PolicyHolder> policyHolderOptional = policyHolderRepository.findById(policyHolderRequest.getId());

        if (policyHolderOptional.isPresent()) {
            PolicyHolder policyHolder = policyHolderOptional.get();
            policyHolder.setSumAssured(policyHolderRequest.getSumAssured());
            policyHolder.setContractPremium(policyHolderRequest.getPolicyHolderContractPremium());
            System.out.println(policyHolderRequest.getPolicyHolderContractPremium());
            policyHolder = policyHolderRepository.save(policyHolder);
            return ResponseEntity.ok(new BaseResult(policyHolder, "Policy holder updated successfully", "00", 200));
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if the policy holder is not found
        }
    }

    @Override
    public ResponseEntity<BaseResult> deletePolicyHolders(Long id) {
        policyHolderRepository.deleteById(id);
        return ResponseEntity.ok(new BaseResult(null, "Policy holders deleted successfully", "00", 200));
    }

    @Override
    public ResponseEntity<BaseResult> findListOfPolicyHolderByNationalIDorPhone(String searchID, String phone) {
        String searchPhone = formatPhoneNumber(phone);
        List<PolicyHolder> searchedPolicyHolders;
        // Convert searchID to uppercase only if it's not empty
        String upperCaseSearchID = searchID.isEmpty() ? "" : searchID.toUpperCase();

        if (!searchPhone.isEmpty() && searchID.isEmpty()) {
            searchedPolicyHolders = policyHolderRepository.findAllByPhoneNumber(searchPhone);
        } else if (searchPhone.isEmpty() && !searchID.isEmpty()) {
            searchedPolicyHolders = policyHolderRepository.findAllByIdNumber(upperCaseSearchID);
        } else if (!searchPhone.isEmpty() && !searchID.isEmpty()) {
            searchedPolicyHolders = policyHolderRepository.findAllByPhoneNumber(searchPhone);
            if (searchedPolicyHolders.isEmpty()) {
                searchedPolicyHolders = policyHolderRepository.findAllByIdNumber(upperCaseSearchID);
            }
        } else {
            searchedPolicyHolders = new ArrayList<>();
        }


        return ResponseEntity.ok(new BaseResult(searchedPolicyHolders, "Policy holder fetched successfully", "00", 200));
    }



}


