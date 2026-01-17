package com.timescale.server.entity;

import com.timescale.server.enums.ItemCategory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Item {
    private Long id;
    private String name;
    private ItemCategory itemCategory;
    private BigDecimal purchasePrice;
    private LocalDate purchaseDate;
    private String ownId;
    private Boolean deleted;
    private LocalDateTime markTime;
    private LocalDateTime updateTime;

    public Item() {}

    public Item(String name, ItemCategory itemCategory, BigDecimal purchasePrice, LocalDate purchaseDate) {
        this.name = name;
        this.itemCategory = itemCategory;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
        this.deleted = false;
        this.markTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getMarkTime() {
        return markTime;
    }

    public void setMarkTime(LocalDateTime markTime) {
        this.markTime = markTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", itemCategory=" + itemCategory +
                ", purchasePrice=" + purchasePrice +
                ", purchaseDate=" + purchaseDate +
                ", ownId='" + ownId + '\'' +
                ", deleted=" + deleted +
                ", markTime=" + markTime +
                ", updateTime=" + updateTime +
                '}';
    }
}