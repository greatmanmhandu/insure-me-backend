package zw.co.lifewebcore.domain.response;

import zw.co.hcpwebcommons.domain.enums.BeneficiaryCategory;
import zw.co.hcpwebcommons.domain.enums.BeneficiaryType;
import zw.co.hcpwebcommons.domain.enums.Gender;
import zw.co.hcpwebcommons.domain.enums.Title;

import java.time.Instant;
import java.time.LocalDate;

public record BeneficiaryResponse(String firstName, String lastName,
                                  String gender, String dateOfBirth, String nationalId, String msisdn, String title,
                                  String relationship, String beneficiaryCategory, String policyNumber,Instant createdDate,
                                  boolean isActive, double coverAmount, double premium ) {
}