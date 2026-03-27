package zw.co.lifewebcore.api;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.hcpwebcommons.api.ApiResponse;
import zw.co.lifewebcore.domain.dto.*;
import zw.co.lifewebcore.domain.model.*;
import zw.co.lifewebcore.domain.service.FormsService;
import zw.co.lifewebcore.request.MemberDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/form")
@CrossOrigin("*")
public class FormsController {

    private final FormsService formsService;
    @PostMapping("/save/newBusinessFuneralForEfmlandELP")
    @ApiOperation(value = "post")
    public ApiResponse newBusinessFuneralForEfmlandELP(@RequestBody MemberDto memberDto) {
        NewBusinessFuneralForEfmlandELP newBusinessFuneralForEfmlandELP = formsService.saveNewBusinessFuneralForEfmlandELPService(memberDto);
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), newBusinessFuneralForEfmlandELP );
    }

    @PostMapping("/save/alterationFormForfuneral")
    @ApiOperation(value = "post")
    public ApiResponse alterationFormForfuneral(@RequestBody GenericParentDto memberDto) {
        AlterationFormForfuneral alterationFormForfuneral = formsService.saveAlterationFormForfuneral(memberDto);
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), alterationFormForfuneral );
    }

    @PostMapping("/save/conversionForFuneralProducts")
    @ApiOperation(value = "post")
    public ApiResponse conversionForFuneralProducts(@RequestBody GenericParentDto memberDto) {
        ConversionForFuneralProducts conversionForFuneralProducts = formsService.saveConversionForFuneralProducts(memberDto);
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), conversionForFuneralProducts);
    }

    @PostMapping("/save/newbusinessForSavings")
    @ApiOperation(value = "post")
    public ApiResponse newbusinessForSavings(@RequestBody GenericParentDto memberDto) {
        NewbusinessForSavings newbusinessForSavings = formsService.saveNewbusinessForSavings(memberDto);
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),newbusinessForSavings);
    }

    @PostMapping("/save/savingsFormForAlteration")
    @ApiOperation(value = "post")
    public ApiResponse savingsFormForAlteration(@RequestBody GenericParentDto memberDto) {
        SavingsFormForAlteration savingsFormForAlteration =formsService.saveSavingsFormForAlteration(memberDto);
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),savingsFormForAlteration);
    }

    @PostMapping("/save/savingsFormForConversion")
    @ApiOperation(value = "post")
    public ApiResponse savingsFormForConversion(@RequestBody GenericParentDto memberDto) {
        SavingsFormForConversion savingsFormForConversion = formsService.saveSavingsFormForConversion(memberDto);

        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), savingsFormForConversion);
    }


    @GetMapping("/get/newBusinessFuneralForEfmlandELP")
    @ApiOperation(value = "get")
    public ResponseEntity<List<NewBusinessFuneralForEfmlandELP>> getNewBusinessFuneralForEfmlandELP(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        // Call the async service method
        CompletableFuture<List<NewBusinessFuneralForEfmlandELP>> dataFuture = formsService.getNewBusinessFuneralForEfmlandELP(startDate, endDate);
        // Use join() instead of get() to avoid checked exceptions
        List<NewBusinessFuneralForEfmlandELP> data = dataFuture.join();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @GetMapping("/get/groupedNewBusinessFuneralForEfmlandELP")
    @ApiOperation(value = "get grouped funeral data")
    public ResponseEntity<List<NewBusinessFuneralGroupedDto>> getGroupedNewBusinessFuneralForEfmlandELP(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        CompletableFuture<List<NewBusinessFuneralGroupedDto>> dataFuture = formsService.getGroupedNewBusinessFuneralForEfmlandELP(startDate, endDate);
        List<NewBusinessFuneralGroupedDto> data = dataFuture.join();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @GetMapping("/get/alterationFormForfuneral")
    @ApiOperation(value = "get")
    public ResponseEntity<List<AlterationFormForfuneral>> getAlterationFormForfuneral(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        // Call the async service method
        CompletableFuture<List<AlterationFormForfuneral>> dataFuture = formsService.getAlterationFormForfuneral(startDate, endDate);
        // Use join() instead of get() to avoid checked exceptions
        List<AlterationFormForfuneral> data = dataFuture.join();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @GetMapping("/get/groupedAlterationFormForfuneral")
    @ApiOperation(value = "get grouped alteration funeral data")
    public ResponseEntity<List<AlterationFuneralGroupedDto>> getGroupedAlterationFormForfuneral(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        CompletableFuture<List<AlterationFuneralGroupedDto>> dataFuture = formsService.getGroupedAlterationFormForfuneral(startDate, endDate);
        List<AlterationFuneralGroupedDto> data = dataFuture.join();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }


    @GetMapping("/get/savingsFormForConversion")
    @ApiOperation(value = "get")
    public ResponseEntity<List<SavingsFormForConversion>> getSavingsFormForConversion(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        // Call the async service method
        CompletableFuture<List<SavingsFormForConversion>> dataFuture = formsService.getSavingsFormForConversion(startDate, endDate);
        // Use join() instead of get() to avoid checked exceptions
        List<SavingsFormForConversion> data = dataFuture.join();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @GetMapping("/get/groupedSavingsFormForConversion")
    @ApiOperation(value = "get grouped savings conversion data")
    public ResponseEntity<List<SavingsGroupedDto<SavingsFormForConversion>>> getGroupedSavingsFormForConversion(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        CompletableFuture<List<SavingsGroupedDto<SavingsFormForConversion>>> dataFuture = formsService.getGroupedSavingsFormForConversion(startDate, endDate);
        List<SavingsGroupedDto<SavingsFormForConversion>> data = dataFuture.join();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @GetMapping("/get/savingsFormForAlteration")
    @ApiOperation(value = "get")
    public ResponseEntity<List<SavingsFormForAlteration>> getSavingsFormForAlteration(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        // Call the async service method
        CompletableFuture<List<SavingsFormForAlteration>> dataFuture = formsService.getSavingsFormForAlteration(startDate, endDate);
        // Use join() instead of get() to avoid checked exceptions
        List<SavingsFormForAlteration> data = dataFuture.join();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @GetMapping("/get/groupedSavingsFormForAlteration")
    @ApiOperation(value = "get grouped savings alteration data")
    public ResponseEntity<List<SavingsGroupedDto<SavingsFormForAlteration>>> getGroupedSavingsFormForAlteration(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        CompletableFuture<List<SavingsGroupedDto<SavingsFormForAlteration>>> dataFuture = formsService.getGroupedSavingsFormForAlteration(startDate, endDate);
        List<SavingsGroupedDto<SavingsFormForAlteration>> data = dataFuture.join();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @GetMapping("/get/newbusinessForSavings")
    @ApiOperation(value = "get")
    public ResponseEntity<List<NewbusinessForSavings>> getNewbusinessForSavings(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    )   {
        // Call the async service method
        CompletableFuture<List<NewbusinessForSavings>> dataFuture = formsService.getNewbusinessForSavings(startDate, endDate);
        // Use join() instead of get() to avoid checked exceptions
        List<NewbusinessForSavings> data = dataFuture.join();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @GetMapping("/get/groupedNewbusinessForSavings")
    @ApiOperation(value = "get grouped savings data")
    public ResponseEntity<List<SavingsGroupedDto<NewbusinessForSavings>>> getGroupedNewbusinessForSavings(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        CompletableFuture<List<SavingsGroupedDto<NewbusinessForSavings>>> dataFuture = formsService.getGroupedNewbusinessForSavings(startDate, endDate);
        List<SavingsGroupedDto<NewbusinessForSavings>> data = dataFuture.join();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }


    @GetMapping("/get/conversionForFuneralProducts")
    @ApiOperation(value = "get")
    public ResponseEntity<List<ConversionForFuneralProducts>> getConversionForFuneralProducts(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    )  {

        // Call the async service method
        CompletableFuture<List<ConversionForFuneralProducts>> dataFuture = formsService.getConversionForFuneralProducts(startDate, endDate);
        // Use join() instead of get() to avoid checked exceptions
        List<ConversionForFuneralProducts> data = dataFuture.join();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @GetMapping("/get/groupedConversionForFuneralProducts")
    @ApiOperation(value = "get grouped conversion funeral data")
    public ResponseEntity<List<ConversionFuneralGroupedDto>> getGroupedConversionForFuneralProducts(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        CompletableFuture<List<ConversionFuneralGroupedDto>> dataFuture = formsService.getGroupedConversionForFuneralProducts(startDate, endDate);
        List<ConversionFuneralGroupedDto> data = dataFuture.join();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }


    @PostMapping("/save/saveImbaProduct")
    @ApiOperation(value = "Saves new business data for Imba Product")
    public ApiResponse saveImbaProduct(@RequestBody GenericParentImbaDto genericParentImbaDto) {
        ImbaProduct imba = formsService.saveImba(genericParentImbaDto);
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), imba);
    }

    @PostMapping("/save/saveMombeProduct")
    @ApiOperation(value = "Saves new business data for Mombe Product")
    public ApiResponse saveMombeProduct(@RequestBody GenericParentMombeDto genericParentMombeDto) {
        MombeProduct imba = formsService.saveMombe(genericParentMombeDto);
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), imba);
    }





    @GetMapping("/get/imbaProduct")
    @ApiOperation(value = "get")
    public ResponseEntity<List<ImbaProduct>> imbaProduct(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    )  {

        // Call the async service method
        CompletableFuture<List<ImbaProduct>> dataFuture = formsService.getImbaProduct(startDate, endDate);
        // Use join() instead of get() to avoid checked exceptions
        List<ImbaProduct> data = dataFuture.join();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @GetMapping("/get/mombeProduct")
    @ApiOperation(value = "get")
    public ResponseEntity<List<MombeProduct>> mombeProduct(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    )  {

        // Call the async service method
        CompletableFuture<List<MombeProduct>> dataFuture = formsService.getMombeProduct(startDate, endDate);
        // Use join() instead of get() to avoid checked exceptions
        List<MombeProduct> data = dataFuture.join();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

}
