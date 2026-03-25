package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.Impl;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.ExcelMemberData;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository.ExcelDataRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ExcelDataService {

    @Autowired
    private ExcelDataRepository excelDataRepository;

    public void saveExcelData(MultipartFile excelFile) throws IOException {
        Workbook workbook = WorkbookFactory.create(excelFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue; // Skip header row
            }

            ExcelMemberData excelData = new ExcelMemberData();
            excelData.setPolicyNumber(row.getCell(0).getStringCellValue());
            excelData.setPremiumPayerFirstName(row.getCell(1).getStringCellValue());
            excelData.setPremiumPayerSurname(row.getCell(2).getStringCellValue());
            excelData.setIDNumber(row.getCell(3).getStringCellValue());
            excelData.setSumAssuredPlanType(row.getCell(4).getStringCellValue());
            excelData.setProductType(row.getCell(5).getStringCellValue());
            excelData.setPhoneNumber(row.getCell(6).getStringCellValue());
            excelData.setGender(row.getCell(7).getStringCellValue());
            excelData.setDateOfBirth(row.getCell(8).getStringCellValue());
            excelData.setAddress(row.getCell(9).getStringCellValue());

            excelDataRepository.save(excelData);
        }
    }
}

