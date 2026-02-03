package com.timescale.server.dto;

import com.timescale.server.enums.BaseCategory;
import com.timescale.server.enums.SubCategory;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemUpdateDTO {

    private String name;

    private BaseCategory baseCategory;

    private SubCategory subCategory;

    @Positive(message = "购买价格必须大于0")
    private BigDecimal purchasePrice;

    private LocalDate purchaseDate;

    private String ownId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaseCategory getItemCategoryLevel1() {
        return baseCategory;
    }

    public void setItemCategoryLevel1(BaseCategory baseCategory) {
        this.baseCategory = baseCategory;
    }

    public SubCategory getItemCategoryLevel2() {
        return subCategory;
    }

    public void setItemCategoryLevel2(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getOwnId() {
        return ownId;
    }

    public void setOwnId(String ownId) {
        this.ownId = ownId;
    }
}
