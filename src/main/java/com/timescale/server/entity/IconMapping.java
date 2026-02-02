package com.timescale.server.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

@TableName("icon_mapping")
public class IconMapping {
    private Long id;

    @TableField("level2_category")
    private String level2Category;

    @TableField("icon")
    private String icon;

    @TableField("icon_type")
    private String iconType;

    @TableField("description")
    private String description;

    @TableField("sort_order")
    private Integer sortOrder;

    @TableLogic
    @TableField("deleted")
    private Boolean deleted;

    @TableField("mark_time")
    private LocalDateTime markTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    public IconMapping() {}

    public IconMapping(String level2Category, String icon) {
        this.level2Category = level2Category;
        this.icon = icon;
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

    public String getLevel2Category() {
        return level2Category;
    }

    public void setLevel2Category(String level2Category) {
        this.level2Category = level2Category;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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
        return "IconMapping{" +
                "id=" + id +
                ", level2Category='" + level2Category + '\'' +
                ", icon='" + icon + '\'' +
                ", iconType='" + iconType + '\'' +
                ", description='" + description + '\'' +
                ", sortOrder=" + sortOrder +
                ", deleted=" + deleted +
                ", markTime=" + markTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
