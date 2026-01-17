package com.timescale.server.dto;

import com.timescale.server.enums.ItemCategory;
import java.time.LocalDate;

public class ItemQueryDTO {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private ItemCategory itemCategory;
    private String nameKeyword;
    private String sortBy = "markTime";
    private String sortOrder = "desc";
    private LocalDate minPurchaseDate;
    private LocalDate maxPurchaseDate;
    private LocalDate minMarkDate;
    private LocalDate maxMarkDate;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getNameKeyword() {
        return nameKeyword;
    }

    public void setNameKeyword(String nameKeyword) {
        this.nameKeyword = nameKeyword;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public LocalDate getMinPurchaseDate() {
        return minPurchaseDate;
    }

    public void setMinPurchaseDate(LocalDate minPurchaseDate) {
        this.minPurchaseDate = minPurchaseDate;
    }

    public LocalDate getMaxPurchaseDate() {
        return maxPurchaseDate;
    }

    public void setMaxPurchaseDate(LocalDate maxPurchaseDate) {
        this.maxPurchaseDate = maxPurchaseDate;
    }

    public LocalDate getMinMarkDate() {
        return minMarkDate;
    }

    public void setMinMarkDate(LocalDate minMarkDate) {
        this.minMarkDate = minMarkDate;
    }

    public LocalDate getMaxMarkDate() {
        return maxMarkDate;
    }

    public void setMaxMarkDate(LocalDate maxMarkDate) {
        this.maxMarkDate = maxMarkDate;
    }
}