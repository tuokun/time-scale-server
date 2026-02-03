package com.timescale.server.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BaseCategory {
    DIGITAL("数字虚拟", "软件、会员、游戏、虚拟权益等数字商品"),
    ELECTRONICS("电子产品", "手机、电脑、相机等电子设备"),
    APPLIANCE("家用电器", "厨房电器、空调、冰箱等家电"),
    FURNITURE("家具", "床、沙发、桌子等家具"),
    CLOTHING("服饰", "衣服、鞋子、包包等"),
    CONSUMABLE("消耗品", "日用品、食品、药品等消耗品"),
    SERVICE("服务类", "维修、家政、旅游等服务"),
    OTHER("其他", "无法分类的其他商品");

    private final String displayName;
    private final String description;

    BaseCategory(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    @JsonValue
    @Override
    public String toString() {
        return name();
    }

    public static BaseCategory fromString(String value) {
        if (value == null) {
            return null;
        }
        try {
            return BaseCategory.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
