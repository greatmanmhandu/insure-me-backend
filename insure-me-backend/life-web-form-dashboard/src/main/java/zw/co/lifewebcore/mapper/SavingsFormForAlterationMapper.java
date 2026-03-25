package zw.co.lifewebcore.mapper;

import org.springframework.stereotype.Component;
import zw.co.lifewebcore.domain.dto.GenericChildrenDto;
import zw.co.lifewebcore.domain.dto.GenericParentDto;
import zw.co.lifewebcore.domain.model.SavingsFormForAlteration;

@Component
public class SavingsFormForAlterationMapper {

    public SavingsFormForAlteration mapToSavingsForm(GenericParentDto memberDto, GenericChildrenDto dependantDto) {
        SavingsFormForAlteration data = new SavingsFormForAlteration();

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

        // Dependant-specific fields
        data.setBurialSpace(dependantDto.getBurialSpace());
        data.setCashBenefit(dependantDto.getCashBenefit());
        data.setAnnually(dependantDto.getAnnually());
        data.setEcoCash(dependantDto.getEcoCash());
        data.setHalfYearly(dependantDto.getHalfYearly());
        data.setQuarterly(dependantDto.getQuarterly());
        data.setOneMoney(dependantDto.getOneMoney());
        data.setMonthly(dependantDto.getMonthly());
        data.setPlanType(dependantDto.getPlanType());
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
        data.setTotalPremium(dependantDto.getTotalPremium());
        data.setNewPremium(dependantDto.getNewPremium());
        data.setStopOrderFacility(dependantDto.getStopOrderFacility());
        data.setActionToTake(dependantDto.getActionToTake());
        data.setLinkName(dependantDto.getLinkName());
        data.setFirstname(dependantDto.getFirstname());
        data.setSurname(dependantDto.getSurname());
        data.setExistingPolicy(dependantDto.getExistingPolicy());

        return data;
    }
}
