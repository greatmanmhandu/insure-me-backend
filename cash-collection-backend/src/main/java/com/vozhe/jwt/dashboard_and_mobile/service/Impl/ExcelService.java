package com.vozhe.jwt.dashboard_and_mobile.service.Impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.vozhe.jwt.dashboard_and_mobile.models.PolicyHolder;
import com.vozhe.jwt.dashboard_and_mobile.repository.PolicyHolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final PolicyHolderRepository policyHolderRepository;

    public void processCSVFile() {
        int batchSize = 10; // Set the batch size according to your requirements
        List<PolicyHolder> batch = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader("C:\\actual-fml-projects\\tafadzwa\\policy_holder_life.csv"))) {
            String[] nextLine;
            // Skip header row if present
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                PolicyHolder policyHolder = new PolicyHolder();
                // Set policyHolder properties based on CSV columns
                policyHolder.setPolicyNumber(nextLine[0]);
                policyHolder.setFirstName(nextLine[1]);
                policyHolder.setLastName(nextLine[2]);
                policyHolder.setIdNumber(nextLine[3]);
                policyHolder.setSumAssured(nextLine[4]);
                policyHolder.setContractPremium(nextLine[5]);
                policyHolder.setProductType(nextLine[6]);
                policyHolder.setPhoneNumber(nextLine[7]);
                policyHolder.setGender(nextLine[8]);
                policyHolder.setDob(nextLine[9]);
                policyHolder.setAddress(nextLine[10]);

                batch.add(policyHolder);

                if (batch.size() >= batchSize) {
                    policyHolderRepository.saveAll(batch);
                    batch.clear();
                }
            }

            if (!batch.isEmpty()) {
                policyHolderRepository.saveAll(batch);
            }
        } catch (IOException | CsvValidationException e) {
            // Handle file reading or processing exceptions
        }
    }

    public int processUploadedFile(MultipartFile file) {
        int batchSize = 10; // Set the batch size according to your requirements
        List<PolicyHolder> batch = new ArrayList<>();
        int batchesProcessed = 0;

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] nextLine;
            // Skip header row if present
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                PolicyHolder policyHolder = new PolicyHolder();
                // Set policyHolder properties based on CSV columns
                policyHolder.setPolicyNumber(nextLine[0]);
                policyHolder.setFirstName(nextLine[1]);
                policyHolder.setLastName(nextLine[2]);
                policyHolder.setIdNumber(nextLine[3]);
                policyHolder.setSumAssured(nextLine[4]);
                policyHolder.setContractPremium(nextLine[5]);
                policyHolder.setProductType(nextLine[6]);
                policyHolder.setPhoneNumber(nextLine[7]);
                policyHolder.setGender(nextLine[8]);
                policyHolder.setDob(nextLine[9]);
                policyHolder.setAddress(nextLine[10]);

                batch.add(policyHolder);

                if (batch.size() >= batchSize) {
                    policyHolderRepository.saveAll(batch);
                    batch.clear();
                    batchesProcessed++;
                }
            }

            if (!batch.isEmpty()) {
                policyHolderRepository.saveAll(batch);
                batchesProcessed++;
            }
        } catch (IOException | CsvValidationException e) {
            // Handle file reading or processing exceptions
        }

        return batchesProcessed;
    }
}