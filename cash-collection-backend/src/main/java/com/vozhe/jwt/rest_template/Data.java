package com.vozhe.jwt.rest_template;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Data {
    private Product product;
    private List<Dependant> dependants;

}
