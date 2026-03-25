package zw.co.lifewebcore.mapper;

import org.springframework.stereotype.Component;
import zw.co.lifewebcore.domain.dto.GenericChildrenDto;
import zw.co.lifewebcore.domain.dto.GenericParentDto;
import zw.co.lifewebcore.domain.model.NewbusinessForSavings;

@Component
public class NewbusinessForSavingsMapper {

    public NewbusinessForSavings mapToNewbusinessForSavings(GenericParentDto memberDto, GenericChildrenDto dependantDto) {
        NewbusinessForSavings data = new NewbusinessForSavings();

        data.setProductType(memberDto.getProductType());
        data.setActualProd(memberDto.getActualProd());
        data.setAgentName(memberDto.getAgentName());
        data.setAgentNumber(memberDto.getAgentNumber());
        data.setAgentRegion(memberDto.getAgentRegion());
        data.setAgentUnit(memberDto.getAgentUnit());
        data.setDateSigned(memberDto.getDateSigned());
        data.setProcessTypeVal(memberDto.getProcessTypeVal());
        data.setCurrencyVal(memberDto.getCurrencyVal());
        data.setBurialSpace(dependantDto.getBurialSpace());
        data.setCashBenefit(dependantDto.getCashBenefit());
        data.setAddress(memberDto.getAddress());
        data.setAnnually(dependantDto.getAnnually());
        data.setEcoCash(dependantDto.getEcoCash());
        data.setHalfYearly(dependantDto.getHalfYearly());
        data.setQuarterly(dependantDto.getQuarterly());
        data.setOneMoney(dependantDto.getOneMoney());
        data.setMonthly(dependantDto.getMonthly());
        data.setDob(dependantDto.getDob());
        data.setEcNumber(dependantDto.getEcNumber());
        data.setFullName(dependantDto.getFullName());
        data.setEmail(dependantDto.getEmail());
        data.setTitle(dependantDto.getTitle());
        data.setGender(dependantDto.getGender());
        data.setMobile(dependantDto.getMobile());
        data.setPlanType(dependantDto.getPlanType());
        data.setNationalId(dependantDto.getNationalId());
        data.setRelationShip(dependantDto.getRelationShip());
        data.setSelectMaritalStatus(dependantDto.getSelectMaritalStatus());
        data.setTombStone(dependantDto.getTombStone());
        data.setTotalPremium(dependantDto.getTotalPremium());
        data.setStopOrderFacility(dependantDto.getStopOrderFacility());
        data.setActionToTake(dependantDto.getActionToTake());
        data.setLinkName(dependantDto.getLinkName());
        data.setFirstname(dependantDto.getFirstname());
        data.setSurname(dependantDto.getSurname());

        return data;
    }
}
