package zw.co.lifewebcore.mapper;

import org.springframework.stereotype.Component;
import zw.co.lifewebcore.domain.dto.GenericChildrenDto;
import zw.co.lifewebcore.domain.dto.GenericParentDto;
import zw.co.lifewebcore.domain.model.AlterationFormForfuneral;

@Component
public class AlterationFormForfuneralMapper {

    public AlterationFormForfuneral mapToAlterationFormForfuneral(GenericParentDto memberDto, GenericChildrenDto childDto) {
        AlterationFormForfuneral data = new AlterationFormForfuneral();

        // Common fields from memberDto
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

        // Fields from GenericChildrenDto
        data.setBurialSpace(childDto.getBurialSpace());
        data.setCashBenefit(childDto.getCashBenefit());
        data.setAnnually(childDto.getAnnually());
        data.setEcoCash(childDto.getEcoCash());
        data.setHalfYearly(childDto.getHalfYearly());
        data.setQuarterly(childDto.getQuarterly());
        data.setOneMoney(childDto.getOneMoney());
        data.setMonthly(childDto.getMonthly());
        data.setDob(childDto.getDob());
        data.setEcNumber(childDto.getEcNumber());
        data.setFullName(childDto.getFullName());
        data.setEmail(childDto.getEmail());
        data.setTitle(childDto.getTitle());
        data.setGender(childDto.getGender());
        data.setMobile(childDto.getMobile());
        data.setNationalId(childDto.getNationalId());
        data.setRelationShip(childDto.getRelationShip());
        data.setSelectMaritalStatus(childDto.getSelectMaritalStatus());
        data.setTombStone(childDto.getTombStone());
        data.setTotalPremium(childDto.getTotalPremium());
        data.setStopOrderFacility(childDto.getStopOrderFacility());
        data.setActionToTake(childDto.getActionToTake());
        data.setFirstname(childDto.getFirstname());
        data.setSurname(childDto.getSurname());

        return data;
    }
}
