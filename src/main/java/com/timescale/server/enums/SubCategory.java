package com.timescale.server.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SubCategory {
    // DIGITAL (数字产品)
    SOFTWARE_APP(BaseCategory.DIGITAL, "软件应用"),
    OFFICE_SOFTWARE(BaseCategory.DIGITAL, "办公软件"),
    TOOL_SOFTWARE(BaseCategory.DIGITAL, "工具软件"),
    GAME_SOFTWARE(BaseCategory.DIGITAL, "游戏软件"),
    GAME_CONSOLE_SOFTWARE(BaseCategory.DIGITAL, "游戏机软件"),
    ONLINE_COURSE(BaseCategory.DIGITAL, "在线课程"),
    EBOOK(BaseCategory.DIGITAL, "电子书籍"),
    VIDEO_MEMBERSHIP(BaseCategory.DIGITAL, "视频会员"),
    MUSIC_MEMBERSHIP(BaseCategory.DIGITAL, "音乐会员"),
    OTHER_MEMBERSHIP(BaseCategory.DIGITAL, "其他会员"),
    OTHER_DIGITAL(BaseCategory.DIGITAL, "其他数字产品"),

    // ELECTRONICS (电子产品)
    MOBILE_PHONE(BaseCategory.ELECTRONICS, "手机"),
    TABLET(BaseCategory.ELECTRONICS, "平板电脑"),
    LAPTOP(BaseCategory.ELECTRONICS, "笔记本电脑"),
    DESKTOP(BaseCategory.ELECTRONICS, "台式电脑"),
    CAMERA(BaseCategory.ELECTRONICS, "相机"),
    PHOTO_EQUIPMENT(BaseCategory.ELECTRONICS, "摄影器材"),
    SMART_BAND(BaseCategory.ELECTRONICS, "智能手环"),
    SMART_WATCH(BaseCategory.ELECTRONICS, "智能手表"),
    GAME_CONSOLE(BaseCategory.ELECTRONICS, "游戏主机"),
    GAME_CONTROLLER(BaseCategory.ELECTRONICS, "游戏手柄"),
    HEADPHONE(BaseCategory.ELECTRONICS, "耳机"),
    SPEAKER(BaseCategory.ELECTRONICS, "音箱"),
    SMART_SPEAKER(BaseCategory.ELECTRONICS, "智能音箱"),
    SMART_HOME_DEVICE(BaseCategory.ELECTRONICS, "智能家居设备"),
    OTHER_ELECTRONICS(BaseCategory.ELECTRONICS, "其他电子产品"),

    // APPLIANCE (家用电器)
    RICE_COOKER(BaseCategory.APPLIANCE, "电饭煲"),
    MICROWAVE(BaseCategory.APPLIANCE, "微波炉"),
    SOY_MILK_MACHINE(BaseCategory.APPLIANCE, "豆浆机"),
    OVEN(BaseCategory.APPLIANCE, "烤箱"),
    AIR_FRYER(BaseCategory.APPLIANCE, "空气炸锅"),
    VACUUM_CLEANER(BaseCategory.APPLIANCE, "吸尘器"),
    ROBOT_VACUUM(BaseCategory.APPLIANCE, "扫地机器人"),
    AIR_CONDITIONER(BaseCategory.APPLIANCE, "空调"),
    FAN(BaseCategory.APPLIANCE, "风扇"),
    AIR_PURIFIER(BaseCategory.APPLIANCE, "空气净化器"),
    REFRIGERATOR(BaseCategory.APPLIANCE, "冰箱"),
    WASHING_MACHINE(BaseCategory.APPLIANCE, "洗衣机"),
    DRYER(BaseCategory.APPLIANCE, "烘干机"),
    TV(BaseCategory.APPLIANCE, "电视"),
    PROJECTOR(BaseCategory.APPLIANCE, "投影仪"),
    OTHER_APPLIANCE(BaseCategory.APPLIANCE, "其他家用电器"),

    // FURNITURE (家具)
    BED(BaseCategory.FURNITURE, "床"),
    MATTRESS(BaseCategory.FURNITURE, "床垫"),
    NIGHTSTAND(BaseCategory.FURNITURE, "床头柜"),
    SOFA(BaseCategory.FURNITURE, "沙发"),
    CHAIR(BaseCategory.FURNITURE, "椅子"),
    STOOL(BaseCategory.FURNITURE, "凳子"),
    ROCKER(BaseCategory.FURNITURE, "摇椅"),
    DINING_TABLE(BaseCategory.FURNITURE, "餐桌"),
    COFFEE_TABLE(BaseCategory.FURNITURE, "茶几"),
    DESK(BaseCategory.FURNITURE, "书桌"),
    OFFICE_DESK(BaseCategory.FURNITURE, "办公桌"),
    WARDROBE(BaseCategory.FURNITURE, "衣柜"),
    STORAGE_CABINET(BaseCategory.FURNITURE, "储物柜"),
    BOOKSHELF(BaseCategory.FURNITURE, "书架"),
    SHELVING_UNIT(BaseCategory.FURNITURE, "置物架"),
    TABLE_LAMP(BaseCategory.FURNITURE, "台灯"),
    FLOOR_LAMP(BaseCategory.FURNITURE, "落地灯"),
    OTHER_FURNITURE(BaseCategory.FURNITURE, "其他家具"),

    // CLOTHING (服饰)
    T_SHIRT(BaseCategory.CLOTHING, "T恤"),
    SHIRT(BaseCategory.CLOTHING, "衬衫"),
    JACKET(BaseCategory.CLOTHING, "外套"),
    SWEATER(BaseCategory.CLOTHING, "毛衣"),
    HOODIE(BaseCategory.CLOTHING, "卫衣"),
    PANTS(BaseCategory.CLOTHING, "裤子"),
    SKIRT(BaseCategory.CLOTHING, "裙子"),
    SHORTS(BaseCategory.CLOTHING, "短裤"),
    SNEAKER(BaseCategory.CLOTHING, "运动鞋"),
    LEATHER_SHOE(BaseCategory.CLOTHING, "皮鞋"),
    BOOT(BaseCategory.CLOTHING, "靴子"),
    SANDAL(BaseCategory.CLOTHING, "凉鞋"),
    HAT(BaseCategory.CLOTHING, "帽子"),
    SCARF(BaseCategory.CLOTHING, "围巾"),
    GLOVE(BaseCategory.CLOTHING, "手套"),
    BACKPACK(BaseCategory.CLOTHING, "背包"),
    HANDBAG(BaseCategory.CLOTHING, "手提包"),
    WALLET(BaseCategory.CLOTHING, "钱包"),
    SHOULDER_BAG(BaseCategory.CLOTHING, "挎包"),
    UNDERWEAR(BaseCategory.CLOTHING, "内衣"),
    SOCKS(BaseCategory.CLOTHING, "袜子"),
    PAJAMAS(BaseCategory.CLOTHING, "睡衣"),
    OTHER_CLOTHING(BaseCategory.CLOTHING, "其他服饰"),

    // CONSUMABLE (消耗品)
    SHAMPOO(BaseCategory.CONSUMABLE, "洗发水"),
    CONDITIONER(BaseCategory.CONSUMABLE, "护发素"),
    TOOTHPASTE(BaseCategory.CONSUMABLE, "牙膏"),
    TOOTHBRUSH(BaseCategory.CONSUMABLE, "牙刷"),
    COSMETICS(BaseCategory.CONSUMABLE, "化妆品"),
    SKINCARE(BaseCategory.CONSUMABLE, "护肤品"),
    FOOD(BaseCategory.CONSUMABLE, "食品"),
    BEVERAGE(BaseCategory.CONSUMABLE, "饮料"),
    SNACK(BaseCategory.CONSUMABLE, "零食"),
    OFFICE_SUPPLIES(BaseCategory.CONSUMABLE, "办公用品"),
    CLEANING_SUPPLIES(BaseCategory.CONSUMABLE, "清洁用品"),
    TISSUE(BaseCategory.CONSUMABLE, "纸巾"),
    MEDICINE(BaseCategory.CONSUMABLE, "药品"),
    SUPPLEMENT(BaseCategory.CONSUMABLE, "保健品"),
    PET_SUPPLIES(BaseCategory.CONSUMABLE, "宠物用品"),
    OTHER_CONSUMABLE(BaseCategory.CONSUMABLE, "其他消耗品"),

    // SERVICE (服务类)
    REPAIR_SERVICE(BaseCategory.SERVICE, "维修服务"),
    HOUSEKEEPING(BaseCategory.SERVICE, "家政服务"),
    BEAUTY_SERVICE(BaseCategory.SERVICE, "美容服务"),
    FITNESS_SERVICE(BaseCategory.SERVICE, "健身服务"),
    TRAVEL_SERVICE(BaseCategory.SERVICE, "旅游服务"),
    CONSULTING_SERVICE(BaseCategory.SERVICE, "咨询服务"),
    OTHER_SERVICE(BaseCategory.SERVICE, "其他服务"),

    // VIRTUAL (虚拟权益)
    GAME_POINTS(BaseCategory.VIRTUAL, "游戏点数"),
    TOPUP_CARD(BaseCategory.VIRTUAL, "充值卡"),
    VIDEO_MEMBERSHIP(BaseCategory.VIRTUAL, "视频会员"),
    MUSIC_MEMBERSHIP(BaseCategory.VIRTUAL, "音乐会员"),
    READING_MEMBERSHIP(BaseCategory.VIRTUAL, "阅读会员"),
    LIVE_GIFT(BaseCategory.VIRTUAL, "直播打赏"),
    VIRTUAL_ITEM(BaseCategory.VIRTUAL, "虚拟礼物"),
    GAME_SKIN(BaseCategory.VIRTUAL, "游戏皮肤"),
    OTHER_VIRTUAL(BaseCategory.VIRTUAL, "其他虚拟权益"),

    // OTHER (其他)
    OTHER_ITEM(BaseCategory.OTHER, "其他商品"),
    GIFT(BaseCategory.OTHER, "礼品"),
    SOUVENIR(BaseCategory.OTHER, "纪念品");

    private final BaseCategory baseCategory;
    private final String displayName;

    SubCategory(BaseCategory baseCategory, String displayName) {
        this.baseCategory = baseCategory;
        this.displayName = displayName;
    }

    public BaseCategory getBaseCategory() {
        return baseCategory;
    }

    public String getDisplayName() {
        return displayName;
    }

    @JsonValue
    @Override
    public String toString() {
        return name();
    }

    public static SubCategory fromString(String value) {
        if (value == null) {
            return null;
        }
        try {
            return SubCategory.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static SubCategory[] getByBase(BaseCategory baseCategory) {
        return java.util.Arrays.stream(SubCategory.values())
                .filter(cat -> cat.getBaseCategory() == baseCategory)
                .toArray(SubCategory[]::new);
    }
}
