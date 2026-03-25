package zw.co.lifewebcore.request;

import zw.co.hcpwebcommons.domain.value.MobileNumber;

public record UpdateMemberDto(MobileNumber mobileNumber, String policyNumber) {
}
