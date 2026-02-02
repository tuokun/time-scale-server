package com.timescale.server.dto;

import com.timescale.server.enums.Level1Category;
import com.timescale.server.enums.Level2Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemCreateDTO {

    @NotBlank(message = "商品名称不能为空")
    private String name;

    @NotNull(message = "商品一级分类不能为空")
    private Level1Category itemCategoryLevel1;

    private Level2Category itemCategoryLevel2;

    @NotNull(message = "购买价格不能为空")
    @Positive(message = "购买价格必须大于0")
    private BigDecimal purchasePrice;

    @NotNull(message = "购买日期不能为空")
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
