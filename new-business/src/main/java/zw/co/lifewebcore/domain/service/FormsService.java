package zw.co.lifewebcore.domain.service;

import zw.co.lifewebcore.domain.dto.GenericParentDto;
import zw.co.lifewebcore.domain.dto.GenericParentImbaDto;
import zw.co.lifewebcore.domain.dto.ConversionFuneralGroupedDto;
import zw.co.lifewebcore.domain.dto.GenericParentMombeDto;
import zw.co.lifewebcore.domain.dto.NewBusinessFuneralGroupedDto;
import zw.co.lifewebcore.domain.model.*;
import zw.co.lifewebcore.request.MemberDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface FormsService {

//    NewBusinessFuneralForEfmlandELP saveNewBusinessFuneralForEfmlandELPService(GenericParentDto memberDto);
    NewBusinessFuneralForEfmlandELP saveNewBusinessFuneralForEfmlandELPService(MemberDto memberDto);
    AlterationFormForfuneral saveAlterationFormForfuneral(GenericParentDto memberDto);

    ConversionForFuneralProducts saveConversionForFuneralProducts(GenericParentDto memberDto);

    NewbusinessForSavings saveNewbusinessForSavings(GenericParentDto memberDto);

    SavingsFormForAlteration saveSavingsFormForAlteration(GenericParentDto memberDto);

    SavingsFormForConversion saveSavingsFormForConversion(GenericParentDto memberDto);

    CompletableFuture<List<NewBusinessFuneralForEfmlandELP>> getNewBusinessFuneralForEfmlandELP(String startDate, String endDate);

    CompletableFuture<List<NewBusinessFuneralGroupedDto>> getGroupedNewBusinessFuneralForEfmlandELP(String startDate, String endDate);

    CompletableFuture<List<AlterationFormForfuneral>> getAlterationFormForfuneral(String startDate,String endDate);

    CompletableFuture<List<SavingsFormForConversion>> getSavingsFormForConversion(String startDate,String endDate);

    CompletableFuture<List<SavingsFormForAlteration>> getSavingsFormForAlteration(String startDate,String endDate);

    CompletableFuture<List<NewbusinessForSavings>> getNewbusinessForSavings(String startDate,String endDate);

    CompletableFuture<List<ConversionForFuneralProducts>> getConversionForFuneralProducts(String startDate,String endDate);

    CompletableFuture<List<ConversionFuneralGroupedDto>> getGroupedConversionForFuneralProducts(String startDate, String endDate);

    ImbaProduct saveImba(GenericParentImbaDto genericParentImbaDto);

    MombeProduct saveMombe(GenericParentMombeDto genericParentMombeDto);

    CompletableFuture<List<ImbaProduct>> getImbaProduct(String startDate, String endDate);

    CompletableFuture<List<MombeProduct>> getMombeProduct(String startDate, String endDate);
}
