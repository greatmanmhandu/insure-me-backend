package zw.co.lifewebcore.mapper;

import org.springframework.stereotype.Component;
import zw.co.lifewebcore.domain.dto.GenericChildrenDto;
import zw.co.lifewebcore.domain.dto.GenericParentDto;
import zw.co.lifewebcore.domain.model.NewBusinessFuneralForEfmlandELP;


@Component
public class NewBusinessFuneralForEfmlandELPMapper {

    public NewBusinessFuneralForEfmlandELP mapToNewBusinessFuneralForEfmlandELP(GenericParentDto memberDto, GenericChildrenDto dependantDto, boolean isDependant) {
        NewBusinessFuneralForEfmlandELP data = new NewBusinessFuneralForEfmlandELP();

        // Common fields
        data.setProductType(memberDto.getProductType());
        data.setActualProd(memberDto.getActualProd());
        data.setAgentName(memberDto.getAgentName());
        data.setAgentNumber(memberDto.getAgentNumber());
        data.setAgentRegion(memberDto.getAgentRegion());
        data.setAgentUnit(memberDto.getAgentUnit());
        data.setDateSigned(memberDto.getDateSigned());
        data.setProcessTypeVal(memberDto.getProcessTypeVal());
        data.setCurrencyVal(memberDto.getCurrencyVal());
        data.setAddress(memberDto.getAddress());

        // Set fields based on whether it's a dependant or not
        if (isDependant) {
            data.setPlanTypeDepandance(dependantDto.getPlanType());
            data.setBurialSpace(dependantDto.getBurialSpace());
            data.setCashBenefit(dependantDto.getCashBenefit());
            data.setDob(dependantDto.getDob());
            data.setEcNumber(dependantDto.getEcNumber());
            data.setFullName(dependantDto.getFullName());
            data.setEmail(dependantDto.getEmail());
            data.setTitle(dependantDto.getTitle());
            data.setGender(dependantDto.getGender());
            data.setMobile(dependantDto.getMobile());
            data.setNationalId(dependantDto.getNationalId());
            data.setRelationShip(dependantDto.getRelationShip());
            data.setSelectMaritalStatus(dependantDto.getSelectMaritalStatus());
            data.setTombStone(dependantDto.getTombStone());
        } else {
            // Main fields from main GenericChildrenDto
            data.setBurialSpace(dependantDto.getBurialSpace());
            data.setCashBenefit(dependantDto.getCashBenefit());
            data.setAnnually(dependantDto.getAnnually());
            data.setEcoCash(dependantDto.getEcoCash());
            data.setHalfYearly(dependantDto.getHalfYearly());
            data.setQuarterly(dependantDto.getQuarterly());
            data.setOneMoney(dependantDto.getOneMoney());
            data.setMonthly(dependantDto.getMonthly());
            data.setFirstname(dependantDto.getFirstname());
            data.setSurname(dependantDto.getSurname());
        }

        return data;
    }
}
