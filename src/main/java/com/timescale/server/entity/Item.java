package com.timescale.server.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.timescale.server.enums.Level1Category;
import com.timescale.server.enums.Level2Category;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("item")
public class Item {
    private Long id;

    @TableField("name")
    private String name;

    @TableField("item_category_level1")
    private Level1Category itemCategoryLevel1;

    @TableField("item_category_level2")
    private Level2Category itemCategoryLevel2;

    @TableField("purchase_price")
    private BigDecimal purchasePrice;

    @TableField("purchase_date")
    private LocalDate purchaseDate;
    @TableField("own_id")
    private String ownId;
    
    @TableLogic
    @TableField("deleted")
    private Boolean deleted;
    
    @TableField("mark_time")
    private LocalDateTime markTime;
    @TableField("update_time")
    private LocalDateTime updateTime;

    public Item() {}

    public Item(String name, Level1Category itemCategoryLevel1, Level2Category itemCategoryLevel2, BigDecimal purchasePrice, LocalDate purchaseDate) {
        this.name = name;
        this.itemCategoryLevel1 = itemCategoryLevel1;
        this.itemCategoryLevel2 = itemCategoryLevel2;
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
                ", itemCategoryLevel1=" + itemCategoryLevel1 +
                ", itemCategoryLevel2=" + itemCategoryLevel2 +
                ", purchasePrice=" + purchasePrice +
                ", purchaseDate=" + purchaseDate +
                ", ownId='" + ownId + '\'' +
                ", deleted=" + deleted +
                ", markTime=" + markTime +
                ", updateTime=" + updateTime +
                '}';
    }
}