package com.ijse.database.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class ProductCountsDTO {
    private Long countOfTotalProducts;
    private Long countOutOfStock;
    private Long countQtyLessThan10;

}
