package com.timescale.server.dto;

import com.timescale.server.enums.Level1Category;
import com.timescale.server.enums.Level2Category;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemUpdateDTO {

    private String name;

    private Level1Category itemCategoryLevel1;

    private Level2Category itemCategoryLevel2;

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

    public Level1Category getItemCategoryLevel1() {
        return itemCategoryLevel1;
    }

    public void setItemCategoryLevel1(Level1Category itemCategoryLevel1) {
        this.itemCategoryLevel1 = itemCategoryLevel1;
    }

    public Level2Category getItemCategoryLevel2() {
        return itemCategoryLevel2;
    }

    public void setItemCategoryLevel2(Level2Category itemCategoryLevel2) {
        this.itemCategoryLevel2 = itemCategoryLevel2;
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
