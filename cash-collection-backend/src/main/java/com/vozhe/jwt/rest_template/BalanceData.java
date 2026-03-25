package com.vozhe.jwt.rest_template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BalanceData {
    private String referenceNumber;
    private String accountType;
    private String currentProduct;
    private String currentBalance;
    private String entityReference;

}
