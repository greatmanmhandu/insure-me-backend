package zw.co.lifewebcore.domain.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import zw.co.lifewebcore.domain.dto.*;
import zw.co.lifewebcore.domain.model.*;
import zw.co.lifewebcore.domain.repository.*;
import zw.co.lifewebcore.domain.service.FormsService;
import zw.co.lifewebcore.mapper.*;
import zw.co.lifewebcore.request.MemberDto;


import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormsServiceImpl implements FormsService {

    private final NewBusinessFuneralForEfmlandELPRepository newBusinessFuneralForEfmlandELPRepository;

    private final AlterationFormForfuneralRepository alterationFormForfuneralRepository;
    private final SavingsFormForConversionRepository savingsFormForConversionRepository;
    private final SavingsFormForAlterationRepository savingsFormForAlterationRepository;
    private final NewbusinessForSavingsRepository newbusinessForSavingsRepository;
    private final ImbaProductRepository imbaProductRepository;
    private final MombeProductRepository mombeProductRepository;
    private final ConversionForFuneralProductsRepository conversionForFuneralProductsRepository;
    private final ObjectMapper objectMapper; // Jackson ObjectMapper for JSON conversion
    private final SavingsFormMapper savingsFormMapper;
    private final SavingsFormForAlterationMapper savingsFormForAlterationMapper;
    private final NewbusinessForSavingsMapper newbusinessForSavingsMapper;
    private final NewBusinessFuneralForEfmlandELPMapper newBusinessFuneralForEfmlandELPMapper;
    private final AlterationFormForfuneralMapper alterationFormForfuneralMapper;
//    public NewBusinessFuneralForEfmlandELP saveNewBusinessFuneralForEfmlandELPService(GenericParentDto memberDto) {
//        List<GenericChildrenDto> main = memberDto.getMainListData();
//        String linkName = "";
//        for (GenericChildrenDto dependant : main) {
//            NewBusinessFuneralForEfmlandELP data = newBusinessFuneralForEfmlandELPMapper.mapToNewBusinessFuneralForEfmlandELP(memberDto, dependant, false);
//            linkName = dependant.getSurname() + "-" + dependant.getMobile();
//            data.setLinkName(linkName);
//            if (data.getActualProd().equals("E FML")) {
//                data.setTotalPremium(memberDto.getMainTotalPremium());
//            } else {
//                data.setTotalPremium(dependant.getTotalPremium());
//            }
//
//            newBusinessFuneralForEfmlandELPRepository.save(data);
//        }
//
//        // Process dependants
//        List<GenericChildrenDto> dependantDto = memberDto.getDependantDto();
//        for (GenericChildrenDto dependant : dependantDto) {
//            NewBusinessFuneralForEfmlandELP data = newBusinessFuneralForEfmlandELPMapper.mapToNewBusinessFuneralForEfmlandELP(memberDto, dependant, true);
//            data.setLinkName(linkName);
//
//            if (data.getActualProd().equals("E FML")) {
//                data.setTotalPremium(BigDecimal.ZERO);
//            }
//
//            newBusinessFuneralForEfmlandELPRepository.save(data);
//        }
//
//        return new NewBusinessFuneralForEfmlandELP();
//    }
public NewBusinessFuneralForEfmlandELP saveNewBusinessFuneralForEfmlandELPService(MemberDto memberDto) {
    NewBusinessFuneralForEfmlandELP data = new NewBusinessFuneralForEfmlandELP();
    List<DependantDto> main = memberDto.getMainListData();
    String annually ="";
    String ecoCash="";
    String halfYearly ="";
    String quarterly ="";
    String oneMoney  ="";
    String monthly ="";
    String linkName="";
    String actualProd = "";
    for(int i =0; i < main.size(); i++ ){
        data.setProductType(memberDto.getProductType());
        data.setActualProd(memberDto.getActualProd());
        data.setAgentName(memberDto.getAgentName());
        data.setAgentNumber(memberDto.getAgentNumber());
        data.setAgentRegion(memberDto.getAgentRegion());
        data.setAgentUnit(memberDto.getAgentUnit());
        data.setDateSigned(memberDto.getDateSigned());
        data.setProcessTypeVal(memberDto.getProcessTypeVal());
        data.setCurrencyVal(memberDto.getCurrencyVal());
        data.setBurialSpace (main.get(i).getBurialSpace());
        data.setCashBenefit (main.get(i).getCashBenefit());
        data.setPlanTypeMain(main.get(i).getPlanType());
        data.setCashBenefit (main.get(i).getCashBenefit());
        data.setAnnually(main.get(i).getAnnually());
        data.setEcoCash (main.get(i).getEcoCash());
        data.setHalfYearly (main.get(i).getHalfYearly());
        data.setQuarterly(main.get(i).getQuarterly());
        data.setOneMoney (main.get(i).getOneMoney());
        data.setMonthly (main.get(i).getMonthly());
        linkName = main.get(i).getSurname() + "-" + main.get(i).getMobile();
        annually = main.get(i).getAnnually();
        ecoCash = main.get(i).getEcoCash();
        halfYearly = main.get(i).getHalfYearly();
        quarterly = main.get(i).getQuarterly();
        oneMoney  = main.get(i).getOneMoney();
        monthly = main.get(i).getMonthly();
        data.setAddress(memberDto.getAddress());
        data.setDob (main.get(i).getDob());
        data.setEcNumber (main.get(i).getEcNumber());
        data.setFullName (main.get(i).getFullName());
        data.setEmail (main.get(i).getEmail());
        data.setTitle (main.get(i).getTitle());
        data.setGender (main.get(i).getGender());
        data.setMobile (main.get(i).getMobile());
        data.setNationalId (main.get(i).getNationalId());
        data.setRelationShip(main.get(i).getRelationShip());
        data.setSelectMaritalStatus(main.get(i).getSelectMaritalStatus());
        data.setTombStone(main.get(i).getTombStone());

        actualProd = data.getActualProd();
        if(data.getActualProd().equals("E FML")){
            data.setTotalPremium(memberDto.getMainTotalPremium());
        } else{
            data.setTotalPremium(main.get(i).getTotalPremium());
        }
        data.setStopOrderFacility(main.get(i).getStopOrderFacility());
        data.setActionToTake(main.get(i).getActionToTake());
        data.setLinkName(linkName);
        data.setFirstname(main.get(i).getFirstname());
        data.setSurname(main.get(i).getSurname());
        data.setExistingPolicy(main.get(i).getExistingPolicy());
        newBusinessFuneralForEfmlandELPRepository.save(data);
    }

    List<DependantDto> dependantDto = memberDto.getDependantDto();
    for (DependantDto dependant : dependantDto) {
        data.setProductType(memberDto.getProductType());
        data.setActualProd(memberDto.getActualProd());
        data.setAgentName(memberDto.getAgentName());
        data.setAgentNumber(memberDto.getAgentNumber());
        data.setAgentRegion(memberDto.getAgentRegion());
        data.setAgentUnit(memberDto.getAgentUnit());
        data.setDateSigned(memberDto.getDateSigned());
        data.setProcessTypeVal(memberDto.getProcessTypeVal());
        data.setCurrencyVal(memberDto.getCurrencyVal());
        data.setPlanTypeDepandance(dependant.getPlanType());
        data.setBurialSpace (dependant.getBurialSpace());
        data.setCashBenefit (dependant.getCashBenefit());

        data.setAnnually(annually);
        data.setEcoCash (ecoCash);
        data.setHalfYearly (halfYearly);
        data.setQuarterly(quarterly);
        data.setOneMoney (oneMoney);
        data.setMonthly (monthly);

        data.setDob (dependant.getDob());
        data.setEcNumber (dependant.getEcNumber());
        data.setFullName (dependant.getFullName());
        data.setEmail (dependant.getEmail());
        data.setTitle (dependant.getTitle());
        data.setGender (dependant.getGender());
        data.setMobile (dependant.getMobile());
        data.setNationalId (dependant.getNationalId());
        data.setRelationShip(dependant.getRelationShip());
        data.setSelectMaritalStatus(dependant.getSelectMaritalStatus());
        data.setTombStone(dependant.getTombStone());

        if(actualProd.equals("E FML")){
            data.setTotalPremium(BigDecimal.valueOf(0));
        } else{
            data.setTotalPremium(dependant.getTotalPremium());
        }
//
        data.setStopOrderFacility(dependant.getStopOrderFacility());
        data.setActionToTake(dependant.getActionToTake());
        data.setLinkName(linkName);
        data.setFirstname(dependant.getFirstname());
        data.setSurname(dependant.getSurname());
        newBusinessFuneralForEfmlandELPRepository.save(data);
    }

    return new NewBusinessFuneralForEfmlandELP();
}



    public AlterationFormForfuneral saveAlterationFormForfuneral(GenericParentDto memberDto) {
        AlterationFormForfuneral data = new AlterationFormForfuneral();

        String annually ="";
        String ecoCash="";
        String halfYearly ="";
        String quarterly ="";
        String oneMoney  ="";
        String monthly ="";
        String linkName="";
        List<GenericChildrenDto> main = memberDto.getMainListData();
        for(int i =0; i < main.size(); i++ ){
            data.setProductType(memberDto.getProductType());
            data.setActualProd(memberDto.getActualProd());
            data.setAgentName(memberDto.getAgentName());
            data.setAgentNumber(memberDto.getAgentNumber());
            data.setAgentRegion(memberDto.getAgentRegion());
            data.setAgentUnit(memberDto.getAgentUnit());
            data.setDateSigned(memberDto.getDateSigned());
            data.setProcessTypeVal(memberDto.getProcessTypeVal());
            data.setCurrencyVal(memberDto.getCurrencyVal());
            data.setBurialSpace (main.get(i).getBurialSpace());
            data.setCashBenefit (main.get(i).getCashBenefit());
            data.setAddress(memberDto.getAddress());
            data.setAnnually(main.get(i).getAnnually());
            data.setEcoCash (main.get(i).getEcoCash());
            data.setHalfYearly (main.get(i).getHalfYearly());
            data.setQuarterly(main.get(i).getQuarterly());
            linkName = main.get(i).getSurname() + "-" + main.get(i).getMobile();
            annually = main.get(i).getAnnually();
            ecoCash = main.get(i).getEcoCash();
            halfYearly = main.get(i).getHalfYearly();
            quarterly = main.get(i).getQuarterly();
            oneMoney  = main.get(i).getOneMoney();
            monthly = main.get(i).getMonthly();


            data.setCashBenefit (main.get(i).getCashBenefit());
            data.setOneMoney (main.get(i).getOneMoney());
            data.setMonthly (main.get(i).getMonthly());
            data.setPlanTypeMain(main.get(i).getPlanType());
            data.setDob (main.get(i).getDob());
            data.setEcNumber (main.get(i).getEcNumber());
            data.setFullName (main.get(i).getFullName());
            data.setEmail (main.get(i).getEmail());
            data.setTitle (main.get(i).getTitle());
            data.setGender (main.get(i).getGender());
            data.setMobile (main.get(i).getMobile());
            data.setNationalId (main.get(i).getNationalId());
            data.setRelationShip(main.get(i).getRelationShip());
            data.setSelectMaritalStatus(main.get(i).getSelectMaritalStatus());
            data.setTombStone(main.get(i).getTombStone());
            data.setTotalPremium(main.get(i).getTotalPremium());
            data.setStopOrderFacility(main.get(i).getStopOrderFacility());
            data.setActionToTake(main.get(i).getActionToTake());
            data.setLinkName(linkName);
            data.setFirstname(main.get(i).getFirstname());
            data.setSurname(main.get(i).getSurname());
            data.setExistingPolicy(main.get(i).getExistingPolicy());
            alterationFormForfuneralRepository.save(data);
        }
        List<GenericChildrenDto> dependantDto = memberDto.getDependantDto();
        for (GenericChildrenDto dependant : dependantDto) {
            data.setProductType(memberDto.getProductType());
            data.setActualProd(memberDto.getActualProd());
            data.setAgentName(memberDto.getAgentName());
            data.setAgentNumber(memberDto.getAgentNumber());
            data.setAgentRegion(memberDto.getAgentRegion());
            data.setDateSigned(memberDto.getDateSigned());
            data.setProcessTypeVal(memberDto.getProcessTypeVal());
            data.setCurrencyVal(memberDto.getCurrencyVal());

            data.setBurialSpace (dependant.getBurialSpace());
            data.setCashBenefit (dependant.getCashBenefit());

            data.setAnnually(annually);
            data.setEcoCash (ecoCash);
            data.setHalfYearly (halfYearly);
            data.setQuarterly(quarterly);
            data.setOneMoney (oneMoney);
            data.setMonthly (monthly);


            data.setCashBenefit (dependant.getCashBenefit());
            data.setDob (dependant.getDob());
            data.setEcNumber (dependant.getEcNumber());
            data.setFullName (dependant.getFullName());
            data.setFirstname (dependant.getFirstname());
            data.setSurname (dependant.getSurname());
            data.setEmail (dependant.getEmail());
            data.setTitle (dependant.getTitle());
            data.setGender (dependant.getGender());
            data.setMobile (dependant.getMobile());
            data.setPlanTypeDepandance(dependant.getPlanType());
            data.setNationalId (dependant.getNationalId());
            data.setRelationShip(dependant.getRelationShip());
            data.setSelectMaritalStatus(dependant.getSelectMaritalStatus());
            data.setTombStone(dependant.getTombStone());
            data.setPlanType(dependant.getPlanType());
            data.setTotalPremium(dependant.getTotalPremium());
            data.setStopOrderFacility(dependant.getStopOrderFacility());
            data.setActionToTake(dependant.getActionToTake());
            data.setLinkName(linkName);
            data.setFirstname(dependant.getFirstname());
            data.setSurname(dependant.getSurname());
            alterationFormForfuneralRepository.save(data);
        }

        return new AlterationFormForfuneral();
    }

    public ConversionForFuneralProducts saveConversionForFuneralProducts(GenericParentDto memberDto) {
        ConversionForFuneralProducts data = new ConversionForFuneralProducts();
        String annually ="";
        String ecoCash="";
        String halfYearly ="";
        String quarterly ="";
        String oneMoney  ="";
        String monthly ="";
        String linkName="";
        List<GenericChildrenDto> main = memberDto.getMainListData();
        for(int i =0; i < main.size(); i++ ){
            data.setProductType(memberDto.getProductType());
            data.setActualProd(memberDto.getActualProd());
            data.setAgentName(memberDto.getAgentName());
            data.setAgentNumber(memberDto.getAgentNumber());
            data.setAgentRegion(memberDto.getAgentRegion());
            data.setAgentUnit(memberDto.getAgentUnit());
            data.setDateSigned(memberDto.getDateSigned());
            data.setProcessTypeVal(memberDto.getProcessTypeVal());
            data.setCurrencyVal(memberDto.getCurrencyVal());

            data.setBurialSpace (main.get(i).getBurialSpace());
            data.setCashBenefit (main.get(i).getCashBenefit());
            data.setAddress(memberDto.getAddress());
            data.setAnnually(main.get(i).getAnnually());
            data.setEcoCash (main.get(i).getEcoCash());
            data.setHalfYearly (main.get(i).getHalfYearly());
            data.setQuarterly(main.get(i).getQuarterly());
            linkName = main.get(i).getSurname() + "-" + main.get(i).getMobile();
            annually = main.get(i).getAnnually();
            ecoCash = main.get(i).getEcoCash();
            halfYearly = main.get(i).getHalfYearly();
            quarterly = main.get(i).getQuarterly();
            oneMoney  = main.get(i).getOneMoney();
            monthly = main.get(i).getMonthly();


            data.setCashBenefit (main.get(i).getCashBenefit());
            data.setOneMoney (main.get(i).getOneMoney());
            data.setMonthly (main.get(i).getMonthly());
            data.setPlanType(main.get(i).getPlanType());
            data.setDob (main.get(i).getDob());
            data.setEcNumber (main.get(i).getEcNumber());
            data.setFullName (main.get(i).getFullName());
            data.setEmail (main.get(i).getEmail());
            data.setTitle (main.get(i).getTitle());
            data.setGender (main.get(i).getGender());
            data.setMobile (main.get(i).getMobile());
            data.setNationalId (main.get(i).getNationalId());
            data.setRelationShip(main.get(i).getRelationShip());
            data.setSelectMaritalStatus(main.get(i).getSelectMaritalStatus());
            data.setTombStone(main.get(i).getTombStone());
            data.setTotalPremium(main.get(i).getTotalPremium());
            data.setActionToTake(main.get(i).getActionToTake());
            data.setLinkName(linkName);
            data.setFirstname(main.get(i).getFirstname());
            data.setSurname(main.get(i).getSurname());
            data.setExistingPolicy(main.get(i).getExistingPolicy());
            conversionForFuneralProductsRepository.save(data);
        }
        List<GenericChildrenDto> dependantDto = memberDto.getDependantDto();
            for (GenericChildrenDto dependant : dependantDto) {
            data.setProductType(memberDto.getProductType());
            data.setActualProd(memberDto.getActualProd());
            data.setAgentName(memberDto.getAgentName());
            data.setAgentNumber(memberDto.getAgentNumber());
            data.setDateSigned(memberDto.getDateSigned());
            data.setProcessTypeVal(memberDto.getProcessTypeVal());
            data.setCurrencyVal(memberDto.getCurrencyVal());

            data.setBurialSpace (dependant.getBurialSpace());
            data.setCashBenefit (dependant.getCashBenefit());

                data.setAnnually(annually);
                data.setEcoCash (ecoCash);
                data.setHalfYearly (halfYearly);
                data.setQuarterly(quarterly);
                data.setOneMoney (oneMoney);
                data.setMonthly (monthly);

            data.setDob (dependant.getDob());
            data.setEcNumber (dependant.getEcNumber());
            data.setFullName (dependant.getFullName());
            data.setEmail (dependant.getEmail());
            data.setTitle (dependant.getTitle());
            data.setGender (dependant.getGender());
            data.setMobile (dependant.getMobile());
            data.setNationalId (dependant.getNationalId());
            data.setRelationShip(dependant.getRelationShip());
            data.setPlanType(dependant.getPlanType());
            data.setSelectMaritalStatus(dependant.getSelectMaritalStatus());
            data.setTombStone(dependant.getTombStone());
            data.setPlanType(dependant.getPlanType());
            data.setTotalPremium(dependant.getTotalPremium());
            data.setActionToTake(dependant.getActionToTake());
            data.setLinkName(linkName);
            data.setFirstname(dependant.getFirstname());
            data.setSurname(dependant.getSurname());
            conversionForFuneralProductsRepository.save(data);
        }

        return new ConversionForFuneralProducts();
    }

    public NewbusinessForSavings saveNewbusinessForSavings(GenericParentDto memberDto) {
        List<GenericChildrenDto> main = memberDto.getMainListData();
        for (GenericChildrenDto dependant : main) {
            NewbusinessForSavings data = newbusinessForSavingsMapper.mapToNewbusinessForSavings(memberDto, dependant);
                newbusinessForSavingsRepository.save(data);
        }
        return new NewbusinessForSavings();
    }

    public SavingsFormForAlteration saveSavingsFormForAlteration(GenericParentDto memberDto) {
        List<GenericChildrenDto> main = memberDto.getMainListData();
        for (GenericChildrenDto dependant : main) {
            SavingsFormForAlteration data = savingsFormForAlterationMapper.mapToSavingsForm(memberDto, dependant);
            savingsFormForAlterationRepository.save(data);
        }
        return new SavingsFormForAlteration();
    }

    public SavingsFormForConversion saveSavingsFormForConversion(GenericParentDto memberDto) {
        List<GenericChildrenDto> genericChildrenDtoList = memberDto.getMainListData();
        for (GenericChildrenDto dependant : genericChildrenDtoList) {
            SavingsFormForConversion data = savingsFormMapper.mapToSavingsForm(memberDto, dependant);
            savingsFormForConversionRepository.save(data);
        }
        return  new SavingsFormForConversion();
    }


    @Async
    public CompletableFuture<List<NewBusinessFuneralForEfmlandELP>> getNewBusinessFuneralForEfmlandELP(String startDateStr, String endDateStr) {
        Instant startDate = parseToInstant(startDateStr, true);
        Instant endDate = parseToInstant(endDateStr, false);
        List<NewBusinessFuneralForEfmlandELP> data =
                newBusinessFuneralForEfmlandELPRepository.findByCreatedDateBetweenOrderByIdAsc(startDate, endDate);
        return CompletableFuture.completedFuture(data);
    }

    @Async
    public CompletableFuture<List<AlterationFormForfuneral>> getAlterationFormForfuneral(String startDateStr,String endDateStr)  {
        Instant startDate = parseToInstant(startDateStr, true);
        Instant endDate = parseToInstant(endDateStr, false);
        List<AlterationFormForfuneral> data =
                alterationFormForfuneralRepository.findByCreatedDateBetweenOrderByIdAsc(startDate, endDate);
        return CompletableFuture.completedFuture(data);
    }


    public CompletableFuture<List<SavingsFormForConversion>> getSavingsFormForConversion(String startDateStr,String endDateStr) {
        Instant startDate = parseToInstant(startDateStr, true);
        Instant endDate = parseToInstant(endDateStr, false);
        List<SavingsFormForConversion> data =
                savingsFormForConversionRepository.findByCreatedDateBetweenOrderByIdAsc(startDate, endDate);
        return CompletableFuture.completedFuture(data);

    }
    public CompletableFuture<List<SavingsFormForAlteration>> getSavingsFormForAlteration(String startDateStr,String endDateStr){
        Instant startDate = parseToInstant(startDateStr, true);
        Instant endDate = parseToInstant(endDateStr, false);
        List<SavingsFormForAlteration> data =
                savingsFormForAlterationRepository.findByCreatedDateBetweenOrderByIdAsc(startDate, endDate);
        return CompletableFuture.completedFuture(data);
    }
    public CompletableFuture<List<NewbusinessForSavings>> getNewbusinessForSavings(String startDateStr,String endDateStr)  {
        Instant startDate = parseToInstant(startDateStr, true);
        Instant endDate = parseToInstant(endDateStr, false);
        List<NewbusinessForSavings> data =
                newbusinessForSavingsRepository.findByCreatedDateBetweenOrderByIdAsc(startDate, endDate);
        return CompletableFuture.completedFuture(data);
    }
    public CompletableFuture<List<ConversionForFuneralProducts>> getConversionForFuneralProducts(String startDateStr,String endDateStr) {
        Instant startDate = parseToInstant(startDateStr, true);
        Instant endDate = parseToInstant(endDateStr, false);
        List<ConversionForFuneralProducts> data =
                conversionForFuneralProductsRepository.findByCreatedDateBetweenOrderByIdAsc(startDate, endDate);
        return CompletableFuture.completedFuture(data);
    }

    @Override
    public ImbaProduct saveImba(GenericParentImbaDto genericParentImbaDto) {
        List<GenericChildrenImbaDto> main = genericParentImbaDto.getMainListData();
        ImbaProduct savedEntity = null; // To return the last saved entity

        for (GenericChildrenImbaDto mainMember : main) { // Renamed 'dependant' to 'mainMember' for clarity
            ImbaProduct data = new ImbaProduct(); // Initialize new entity for each entry

            // Map fields from GenericParentDto
            data.setAgentName(genericParentImbaDto.getAgentName());
            data.setAgentNumber(genericParentImbaDto.getAgentNumber());
            data.setAgentUnit(genericParentImbaDto.getAgentUnit());
            data.setAgentRegion(genericParentImbaDto.getAgentRegion());
            data.setAddress(genericParentImbaDto.getAddress()); // Main address for parent
            data.setDateSigned(genericParentImbaDto.getDateSigned());
            data.setActualProd(genericParentImbaDto.getActualProd());
            data.setCurrencyVal(genericParentImbaDto.getCurrencyVal());
            data.setProcessTypeVal(genericParentImbaDto.getProcessTypeVal());
            data.setProductType(genericParentImbaDto.getProductType());
            // Map mainTotalPremium if it represents a sum for the parent, or take from child if only one mainListData entry
            // For this payload, mainTotalPremium seems to match the child's totalPremium
            data.setTotalPremium(genericParentImbaDto.getMainTotalPremium());


            // Map fields from GenericChildrenDto (mainListData entry)
            data.setFullName(mainMember.getFullName());
            data.setFirstname(mainMember.getFirstname());
            data.setSurname(mainMember.getSurname());
            data.setAddress(mainMember.getAddress()); // Child-specific address, if different from parent
            data.setDob(mainMember.getDob());
            data.setGender(mainMember.getGender());
            data.setRelationShip(mainMember.getRelationShip());
            // data.setTotalPremium(mainMember.getTotalPremium()); // This is already set from mainTotalPremium if it's the same
            data.setPlanType(mainMember.getPlanType());
            data.setTitle(mainMember.getTitle());
            data.setMobile(mainMember.getMobile());
            data.setEcNumber(mainMember.getEcNumber());
            data.setSelectMaritalStatus(mainMember.getSelectMaritalStatus());
            data.setEmail(mainMember.getEmail());
            data.setNationalId(mainMember.getNationalId());
            data.setStopOrderFacility(mainMember.getStopOrderFacility());
            data.setLinkName(mainMember.getLinkName());

            // Map new fields from GenericChildrenDto
            data.setOccupation(mainMember.getOccupation());
            data.setEmployerName(mainMember.getEmployerName());
            data.setMonthlyIncome(mainMember.getMonthlyIncome());
            data.setSumAssured(mainMember.getSumAssured());
            data.setDeposit(mainMember.getDeposit());
            data.setSourceOfFunds(mainMember.getSourceOfFunds());
            data.setTerm(mainMember.getTerm());
            data.setSelectedPaymentMethod(mainMember.getSelectedPaymentMethod());
            data.setSpecifyForOther(mainMember.getSpecifyForOther());
            data.setCash(mainMember.getCash());
            data.setCashBenefit(mainMember.getCashBenefit());
            data.setPaymentFrequency(mainMember.getPaymentFrequency());
            data.setHerdName(mainMember.getHerdName());


            // Convert medical answers and details to JSON strings
            try {
                if (genericParentImbaDto.getMedicalAnswers() != null) {
                    data.setMedicalAnswersJson(objectMapper.writeValueAsString(genericParentImbaDto.getMedicalAnswers()));
                }
                if (genericParentImbaDto.getMedicalDetailEntries() != null && !genericParentImbaDto.getMedicalDetailEntries().isEmpty()) {
                    data.setMedicalDetailEntriesJson(objectMapper.writeValueAsString(genericParentImbaDto.getMedicalDetailEntries()));
                }
            } catch (JsonProcessingException e) {
                System.out.println("Error converting medical data to JSON string: {}"+ e.getMessage());
                // Handle the exception, e.g., throw a custom exception or return an error response
                throw new RuntimeException("Failed to process medical data.", e);
            }

            savedEntity = imbaProductRepository.save(data);
        }
        return savedEntity; // Returns the last saved entity from the loop
    }

    @Override
    public MombeProduct saveMombe(GenericParentMombeDto genericParentMombeDto) {
        List<GenericChildrenImbaDto> main = genericParentMombeDto.getMainListData();
        MombeProduct savedEntity = null; // To return the last saved entity

        for (GenericChildrenImbaDto mainMember : main) { // Renamed 'dependant' to 'mainMember' for clarity
            MombeProduct data = new MombeProduct(); // Initialize new entity for each entry

            // Map fields from GenericParentDto
            data.setAgentName(genericParentMombeDto.getAgentName());
            data.setAgentNumber(genericParentMombeDto.getAgentNumber());
            data.setAgentUnit(genericParentMombeDto.getAgentUnit());
            data.setAgentRegion(genericParentMombeDto.getAgentRegion());
            data.setAddress(genericParentMombeDto.getAddress()); // Main address for parent
            data.setDateSigned(genericParentMombeDto.getDateSigned());
            data.setActualProd(genericParentMombeDto.getActualProd());
            data.setCurrencyVal(genericParentMombeDto.getCurrencyVal());
            data.setProcessTypeVal(genericParentMombeDto.getProcessTypeVal());
            data.setProductType(genericParentMombeDto.getProductType());
            data.setTotalPremium(genericParentMombeDto.getMainTotalPremium());


            // Map fields from GenericChildrenDto (mainListData entry)
            data.setFullName(mainMember.getFullName());
            data.setFirstname(mainMember.getFirstname());
            data.setSurname(mainMember.getSurname());
            data.setAddress(mainMember.getAddress()); // Child-specific address, if different from parent
            data.setDob(mainMember.getDob());
            data.setGender(mainMember.getGender());
            data.setRelationShip(mainMember.getRelationShip());
            // data.setTotalPremium(mainMember.getTotalPremium()); // This is already set from mainTotalPremium if it's the same
            data.setPlanType(mainMember.getPlanType());
            data.setTitle(mainMember.getTitle());
            data.setMobile(mainMember.getMobile());
            data.setEcNumber(mainMember.getEcNumber());
            data.setSelectMaritalStatus(mainMember.getSelectMaritalStatus());
            data.setEmail(mainMember.getEmail());
            data.setNationalId(mainMember.getNationalId());
            data.setStopOrderFacility(mainMember.getStopOrderFacility());
            data.setLinkName(mainMember.getLinkName());

            // Map new fields from GenericChildrenDto
            data.setOccupation(mainMember.getOccupation());
            data.setEmployerName(mainMember.getEmployerName());
            data.setMonthlyIncome(mainMember.getMonthlyIncome());
            data.setSumAssured(mainMember.getSumAssured());
            data.setDeposit(mainMember.getDeposit());
            data.setSourceOfFunds(mainMember.getSourceOfFunds());
            data.setTerm(mainMember.getTerm());
            data.setSelectedPaymentMethod(mainMember.getSelectedPaymentMethod());
            data.setSpecifyForOther(mainMember.getSpecifyForOther());
            data.setCash(mainMember.getCash());
            data.setCashBenefit(mainMember.getCashBenefit());
            data.setPaymentFrequency(mainMember.getPaymentFrequency());
            data.setHerdName(mainMember.getHerdName());


            // Convert medical answers and details to JSON strings
            try {
                if (genericParentMombeDto.getMedicalAnswers() != null) {
                    data.setMedicalAnswersJson(objectMapper.writeValueAsString(genericParentMombeDto.getMedicalAnswers()));
                }
                if (genericParentMombeDto.getMedicalDetailEntries() != null && !genericParentMombeDto.getMedicalDetailEntries().isEmpty()) {
                    data.setMedicalDetailEntriesJson(objectMapper.writeValueAsString(genericParentMombeDto.getMedicalDetailEntries()));
                }
            } catch (JsonProcessingException e) {
                System.out.println("Error converting medical data to JSON string: {}"+ e.getMessage());
                // Handle the exception, e.g., throw a custom exception or return an error response
                throw new RuntimeException("Failed to process medical data.", e);
            }

            savedEntity = mombeProductRepository.save(data);
        }
        return savedEntity; // Returns the last saved entity from the loop
    }

    @Override
    public CompletableFuture<List<ImbaProduct>> getImbaProduct(String startDateStr, String endDateStr) {
        Instant startDate = parseToInstant(startDateStr, true);
        Instant endDate = parseToInstant(endDateStr, false);
        List<ImbaProduct> data =
                imbaProductRepository.findByCreatedDateBetweenOrderByIdAsc(startDate, endDate);
        return CompletableFuture.completedFuture(data);
    }

    @Override
    public CompletableFuture<List<MombeProduct>> getMombeProduct(String startDateStr, String endDateStr) {
        Instant startDate = parseToInstant(startDateStr, true);
        Instant endDate = parseToInstant(endDateStr, false);
        List<MombeProduct> data =
                mombeProductRepository.findByCreatedDateBetweenOrderByIdAsc(startDate, endDate);
        return CompletableFuture.completedFuture(data);
    }

    @Async
    @Override
    public CompletableFuture<List<NewBusinessFuneralGroupedDto>> getGroupedNewBusinessFuneralForEfmlandELP(String startDateStr, String endDateStr) {
        Instant startDate = parseToInstant(startDateStr, true);
        Instant endDate = parseToInstant(endDateStr, false);
        List<NewBusinessFuneralForEfmlandELP> allData =
                newBusinessFuneralForEfmlandELPRepository.findByCreatedDateBetweenOrderByIdAsc(startDate, endDate);

        // Group by linkName AND createdDate (truncated to seconds to ensure they belong to the same submission)
        Map<String, List<NewBusinessFuneralForEfmlandELP>> groupedData = allData.stream()
                .filter(record -> record.getLinkName() != null && !record.getLinkName().isEmpty())
                .collect(Collectors.groupingBy(record -> {
                    // Create a composite key: linkName + timestamp (truncated to seconds)
                    long epochSecond = record.getCreatedDate().getEpochSecond();
                    return record.getLinkName() + "_" + epochSecond;
                }));

        List<NewBusinessFuneralGroupedDto> result = new ArrayList<>();
        Set<Long> processedIds = new HashSet<>();

        // Handle grouped records
        groupedData.forEach((key, records) -> {
            // Find main record (the one with mobile is usually the main member)
            NewBusinessFuneralForEfmlandELP mainRecord = records.stream()
                    .filter(r -> r.getMobile() != null && !r.getMobile().isEmpty())
                    .filter(r -> "SELF".equalsIgnoreCase(r.getRelationShip()))
                    .findFirst()
                    .orElse(records.stream()
                            .filter(r -> r.getMobile() != null && !r.getMobile().isEmpty())
                            .findFirst()
                            .orElse(records.get(0)));

            List<NewBusinessFuneralForEfmlandELP> dependants = records.stream()
                    .filter(r -> r.getId() != mainRecord.getId())
                    .collect(Collectors.toList());

            result.add(NewBusinessFuneralGroupedDto.builder()
                    .mainRecord(mainRecord)
                    .dependants(dependants)
                    .build());
            
            records.forEach(r -> processedIds.add(r.getId()));
        });

        // Handle records WITHOUT mobile or not processed (treat as main records with no dependants)
        allData.stream()
                .filter(record -> !processedIds.contains(record.getId()))
                .forEach(record -> {
                    result.add(NewBusinessFuneralGroupedDto.builder()
                            .mainRecord(record)
                            .dependants(new ArrayList<>())
                            .build());
                });

        return CompletableFuture.completedFuture(result);
    }

    @Async
    @Override
    public CompletableFuture<List<ConversionFuneralGroupedDto>> getGroupedConversionForFuneralProducts(String startDateStr, String endDateStr) {
        Instant startDate = parseToInstant(startDateStr, true);
        Instant endDate = parseToInstant(endDateStr, false);
        List<ConversionForFuneralProducts> allData =
                conversionForFuneralProductsRepository.findByCreatedDateBetweenOrderByIdAsc(startDate, endDate);

        // Group by linkName AND createdDate (truncated to seconds)
        Map<String, List<ConversionForFuneralProducts>> groupedData = allData.stream()
                .filter(record -> record.getLinkName() != null && !record.getLinkName().isEmpty())
                .collect(Collectors.groupingBy(record -> {
                    long epochSecond = record.getCreatedDate().getEpochSecond();
                    return record.getLinkName() + "_" + epochSecond;
                }));

        List<ConversionFuneralGroupedDto> result = new ArrayList<>();
        Set<Long> processedIds = new HashSet<>();

        groupedData.forEach((key, records) -> {
            // Find main record
            ConversionForFuneralProducts mainRecord = records.stream()
                    .filter(r -> r.getMobile() != null && !r.getMobile().isEmpty())
                    .filter(r -> "SELF".equalsIgnoreCase(r.getRelationShip()))
                    .findFirst()
                    .orElse(records.stream()
                            .filter(r -> r.getMobile() != null && !r.getMobile().isEmpty())
                            .findFirst()
                            .orElse(records.get(0)));

            List<ConversionForFuneralProducts> dependants = records.stream()
                    .filter(r -> r.getId() != mainRecord.getId())
                    .collect(Collectors.toList());

            result.add(ConversionFuneralGroupedDto.builder()
                    .mainRecord(mainRecord)
                    .dependants(dependants)
                    .build());
            
            records.forEach(r -> processedIds.add(r.getId()));
        });

        // Handle records without linkName or not processed
        allData.stream()
                .filter(record -> !processedIds.contains(record.getId()))
                .forEach(record -> {
                    result.add(ConversionFuneralGroupedDto.builder()
                            .mainRecord(record)
                            .dependants(new ArrayList<>())
                            .build());
                });

        return CompletableFuture.completedFuture(result);
    }

    @Async
    @Override
    public CompletableFuture<List<AlterationFuneralGroupedDto>> getGroupedAlterationFormForfuneral(String startDateStr, String endDateStr) {
        Instant startDate = parseToInstant(startDateStr, true);
        Instant endDate = parseToInstant(endDateStr, false);
        List<AlterationFormForfuneral> allData =
                alterationFormForfuneralRepository.findByCreatedDateBetweenOrderByIdAsc(startDate, endDate);

        // Group by linkName AND createdDate (truncated to seconds)
        Map<String, List<AlterationFormForfuneral>> groupedData = allData.stream()
                .filter(record -> record.getLinkName() != null && !record.getLinkName().isEmpty())
                .collect(Collectors.groupingBy(record -> {
                    long epochSecond = record.getCreatedDate().getEpochSecond();
                    return record.getLinkName() + "_" + epochSecond;
                }));

        List<AlterationFuneralGroupedDto> result = new ArrayList<>();
        Set<Long> processedIds = new HashSet<>();

        groupedData.forEach((key, records) -> {
            // Find main record
            AlterationFormForfuneral mainRecord = records.stream()
                    .filter(r -> r.getMobile() != null && !r.getMobile().isEmpty())
                    .filter(r -> "SELF".equalsIgnoreCase(r.getRelationShip()))
                    .findFirst()
                    .orElse(records.stream()
                            .filter(r -> r.getMobile() != null && !r.getMobile().isEmpty())
                            .findFirst()
                            .orElse(records.get(0)));

            List<AlterationFormForfuneral> dependants = records.stream()
                    .filter(r -> r.getId() != mainRecord.getId())
                    .collect(Collectors.toList());

            result.add(AlterationFuneralGroupedDto.builder()
                    .mainRecord(mainRecord)
                    .dependants(dependants)
                    .build());
            
            records.forEach(r -> processedIds.add(r.getId()));
        });

        // Handle records without linkName or not processed
        allData.stream()
                .filter(record -> !processedIds.contains(record.getId()))
                .forEach(record -> {
                    result.add(AlterationFuneralGroupedDto.builder()
                            .mainRecord(record)
                            .dependants(new ArrayList<>())
                            .build());
                });

        return CompletableFuture.completedFuture(result);
    }

    @Async
    @Override
    public CompletableFuture<List<SavingsGroupedDto<NewbusinessForSavings>>> getGroupedNewbusinessForSavings(String startDateStr, String endDateStr) {
        Instant startDate = parseToInstant(startDateStr, true);
        Instant endDate = parseToInstant(endDateStr, false);
        List<NewbusinessForSavings> allData =
                newbusinessForSavingsRepository.findByCreatedDateBetweenOrderByIdAsc(startDate, endDate);

        Map<String, List<NewbusinessForSavings>> groupedData = allData.stream()
                .filter(record -> record.getLinkName() != null && !record.getLinkName().isEmpty())
                .collect(Collectors.groupingBy(record -> {
                    long epochSecond = record.getCreatedDate().getEpochSecond();
                    return record.getLinkName() + "_" + epochSecond;
                }));

        List<SavingsGroupedDto<NewbusinessForSavings>> result = new ArrayList<>();
        Set<Long> processedIds = new HashSet<>();

        groupedData.forEach((key, records) -> {
            NewbusinessForSavings mainRecord = records.stream()
                    .filter(r -> r.getMobile() != null && !r.getMobile().isEmpty())
                    .filter(r -> "SELF".equalsIgnoreCase(r.getRelationShip()))
                    .findFirst()
                    .orElse(records.stream()
                            .filter(r -> r.getMobile() != null && !r.getMobile().isEmpty())
                            .findFirst()
                            .orElse(records.get(0)));

            List<NewbusinessForSavings> dependants = records.stream()
                    .filter(r -> r.getId() != mainRecord.getId())
                    .collect(Collectors.toList());

            result.add(SavingsGroupedDto.<NewbusinessForSavings>builder()
                    .mainRecord(mainRecord)
                    .dependants(dependants)
                    .build());
            
            records.forEach(r -> processedIds.add(r.getId()));
        });

        allData.stream()
                .filter(record -> !processedIds.contains(record.getId()))
                .forEach(record -> {
                    result.add(SavingsGroupedDto.<NewbusinessForSavings>builder()
                            .mainRecord(record)
                            .dependants(new ArrayList<>())
                            .build());
                });

        return CompletableFuture.completedFuture(result);
    }

    @Async
    @Override
    public CompletableFuture<List<SavingsGroupedDto<SavingsFormForConversion>>> getGroupedSavingsFormForConversion(String startDateStr, String endDateStr) {
        Instant startDate = parseToInstant(startDateStr, true);
        Instant endDate = parseToInstant(endDateStr, false);
        List<SavingsFormForConversion> allData =
                savingsFormForConversionRepository.findByCreatedDateBetweenOrderByIdAsc(startDate, endDate);

        Map<String, List<SavingsFormForConversion>> groupedData = allData.stream()
                .filter(record -> record.getLinkName() != null && !record.getLinkName().isEmpty())
                .collect(Collectors.groupingBy(record -> {
                    long epochSecond = record.getCreatedDate().getEpochSecond();
                    return record.getLinkName() + "_" + epochSecond;
                }));

        List<SavingsGroupedDto<SavingsFormForConversion>> result = new ArrayList<>();
        Set<Long> processedIds = new HashSet<>();

        groupedData.forEach((key, records) -> {
            SavingsFormForConversion mainRecord = records.stream()
                    .filter(r -> r.getMobile() != null && !r.getMobile().isEmpty())
                    .filter(r -> "SELF".equalsIgnoreCase(r.getRelationShip()))
                    .findFirst()
                    .orElse(records.stream()
                            .filter(r -> r.getMobile() != null && !r.getMobile().isEmpty())
                            .findFirst()
                            .orElse(records.get(0)));

            List<SavingsFormForConversion> dependants = records.stream()
                    .filter(r -> r.getId() != mainRecord.getId())
                    .collect(Collectors.toList());

            result.add(SavingsGroupedDto.<SavingsFormForConversion>builder()
                    .mainRecord(mainRecord)
                    .dependants(dependants)
                    .build());
            
            records.forEach(r -> processedIds.add(r.getId()));
        });

        allData.stream()
                .filter(record -> !processedIds.contains(record.getId()))
                .forEach(record -> {
                    result.add(SavingsGroupedDto.<SavingsFormForConversion>builder()
                            .mainRecord(record)
                            .dependants(new ArrayList<>())
                            .build());
                });

        return CompletableFuture.completedFuture(result);
    }

    @Async
    @Override
    public CompletableFuture<List<SavingsGroupedDto<SavingsFormForAlteration>>> getGroupedSavingsFormForAlteration(String startDateStr, String endDateStr) {
        Instant startDate = parseToInstant(startDateStr, true);
        Instant endDate = parseToInstant(endDateStr, false);
        List<SavingsFormForAlteration> allData =
                savingsFormForAlterationRepository.findByCreatedDateBetweenOrderByIdAsc(startDate, endDate);

        Map<String, List<SavingsFormForAlteration>> groupedData = allData.stream()
                .filter(record -> record.getLinkName() != null && !record.getLinkName().isEmpty())
                .collect(Collectors.groupingBy(record -> {
                    long epochSecond = record.getCreatedDate().getEpochSecond();
                    return record.getLinkName() + "_" + epochSecond;
                }));

        List<SavingsGroupedDto<SavingsFormForAlteration>> result = new ArrayList<>();
        Set<Long> processedIds = new HashSet<>();

        groupedData.forEach((key, records) -> {
            SavingsFormForAlteration mainRecord = records.stream()
                    .filter(r -> r.getMobile() != null && !r.getMobile().isEmpty())
                    .filter(r -> "SELF".equalsIgnoreCase(r.getRelationShip()))
                    .findFirst()
                    .orElse(records.stream()
                            .filter(r -> r.getMobile() != null && !r.getMobile().isEmpty())
                            .findFirst()
                            .orElse(records.get(0)));

            List<SavingsFormForAlteration> dependants = records.stream()
                    .filter(r -> r.getId() != mainRecord.getId())
                    .collect(Collectors.toList());

            result.add(SavingsGroupedDto.<SavingsFormForAlteration>builder()
                    .mainRecord(mainRecord)
                    .dependants(dependants)
                    .build());
            
            records.forEach(r -> processedIds.add(r.getId()));
        });

        allData.stream()
                .filter(record -> !processedIds.contains(record.getId()))
                .forEach(record -> {
                    result.add(SavingsGroupedDto.<SavingsFormForAlteration>builder()
                            .mainRecord(record)
                            .dependants(new ArrayList<>())
                            .build());
                });

        return CompletableFuture.completedFuture(result);
    }

    public Instant parseToInstant(String dateStr, boolean startOfDay) {
        try {
            // Try to parse as LocalDate
            LocalDate localDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            // Convert to Instant at the start or end of the day
            return startOfDay ?
                    localDate.atStartOfDay(ZoneId.of("UTC")).toInstant() :
                    localDate.atTime(23, 59, 59).atZone(ZoneId.of("UTC")).toInstant();
        } catch (DateTimeParseException e) {
            // Handle parsing error (you might want to log or rethrow)
            throw new RuntimeException("Invalid date format: " + dateStr, e);
        }
    }

}
