package zw.co.lifewebcore.domain.response;

import zw.co.hcpwebcommons.domain.enums.PolicyStatus;
import zw.co.hcpwebcommons.domain.enums.PolicyType;
import zw.co.lifewebcore.domain.dto.GenericChildrenDto;

import java.util.List;


public record PolicyResponse(String nameOfPolicyHolder, String policyNumber, PolicyType policyType, PolicyStatus policyStatus, int numberOfAdults,
                             int numberOfChildren, List<GenericChildrenDto> beneficiaries) {
}
