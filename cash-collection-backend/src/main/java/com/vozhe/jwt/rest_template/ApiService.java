package com.vozhe.jwt.rest_template;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;
import java.util.List;

@Service
public class ApiService {
    private final RestTemplate restTemplate;

    @Autowired
    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    public MemberData getMemberData(String memberId) {
//        WebClient webClient = WebClient.create();
//        String url = "http://10.65.0.4:9003/api/health/member/" + memberId;
//
//        String responseBody = webClient.get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//
//// Create an instance of ObjectMapper from Jackson library
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            // Convert the response body to a JSON object
//            Object json = objectMapper.readValue(responseBody, Object.class);
//            System.out.println(json);
//            // Perform further processing with the JSON object
//            // ...
//
//            // If needed, you can also convert the JSON back to a string
//            String jsonString = objectMapper.writeValueAsString(json);
//
//            // ...
//        } catch (Exception e) {
//            e.printStackTrace();
//        }       // System.out.println("hfhfh"+responseBody);
//        MemberData memberData = new MemberData();
////        if (response != null && response.getData() != null) {
////            Data data = response.getData();
////            if (data.getProduct() != null) {
////                memberData.setProductName(data.getProduct().getName());
////            }
////            List<Dependant> dependants = data.getDependants();
////            if (dependants != null && !dependants.isEmpty()) {
////                Dependant firstDependant = dependants.get(0);
////                memberData.setLastname(firstDependant.getSurname());
////                memberData.setFirstName(firstDependant.getFirstNames());
////            }
////        }
//
//    }

        public Object getMemberDataJson(String memberId) {
            WebClient webClient = WebClient.create();
//            String url = "http://10.65.0.4:9003/api/health/member/" + memberId;
            String url = "http://10.65.0.4:9022/api/health/member/" + memberId;

            String responseBody = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

// Create an instance of ObjectMapper from Jackson library
            ObjectMapper objectMapper = new ObjectMapper();
            Object json = new Object();
            try {
                // Convert the response body to a JSON object
                 json = objectMapper.readValue(responseBody, Object.class);
//                System.out.println(json);

            } catch (Exception e) {
                e.printStackTrace();
            }       // System.out.println("hfhfh"+responseBody);
            MemberData memberData = new MemberData();

        return json;
    }


    public Object getStudent(String regNumber) {
        WebClient webClient = WebClient.create();
        String url = "http://web.msu.ac.zw/firstmutual/student.php"; // Update with your URL

        // Create the request body
        String requestBody = "{\"regNumber\": \"" + regNumber + "\"}";

        // Basic authentication credentials
        String username = "msu400";
        String password = "msu321";
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        // Make the POST request
        String responseBody = webClient.post()
                .uri(url)
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuth)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Create an instance of ObjectMapper from Jackson library
        ObjectMapper objectMapper = new ObjectMapper();
        Object json = new Object();
        try {
            // Convert the response body to a JSON object
            json = objectMapper.readValue(responseBody, Object.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }
    public Object getMemberBalanceDataJson(String memberId) {
        WebClient webClient = WebClient.create();
//            String url = "http://10.65.0.4:9003/api/health/member/balance" + memberId;
        String url = "http://10.65.0.4:9022/api/health/member/balance/" + memberId;

        String responseBody = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

// Create an instance of ObjectMapper from Jackson library
        ObjectMapper objectMapper = new ObjectMapper();
        Object json = new Object();
        try {
            // Convert the response body to a JSON object
            json = objectMapper.readValue(responseBody, Object.class);
//                System.out.println(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
        BalanceData memberData = new BalanceData();

        return json;
    }
}