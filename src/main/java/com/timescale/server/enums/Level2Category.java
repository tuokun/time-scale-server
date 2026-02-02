package com.timescale.server.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Level2Category {
    // DIGITAL (数字产品)
    SOFTWARE_APP(Level1Category.DIGITAL, "软件应用"),
    OFFICE_SOFTWARE(Level1Category.DIGITAL, "办公软件"),
    TOOL_SOFTWARE(Level1Category.DIGITAL, "工具软件"),
    GAME_SOFTWARE(Level1Category.DIGITAL, "游戏软件"),
    GAME_CONSOLE_SOFTWARE(Level1Category.DIGITAL, "游戏机软件"),
    ONLINE_COURSE(Level1Category.DIGITAL, "在线课程"),
    EBOOK(Level1Category.DIGITAL, "电子书籍"),
    VIDEO_MEMBERSHIP(Level1Category.DIGITAL, "视频会员"),
    MUSIC_MEMBERSHIP(Level1Category.DIGITAL, "音乐会员"),
    OTHER_MEMBERSHIP(Level1Category.DIGITAL, "其他会员"),
    OTHER_DIGITAL(Level1Category.DIGITAL, "其他数字产品"),

    // ELECTRONICS (电子产品)
    MOBILE_PHONE(Level1Category.ELECTRONICS, "手机"),
    TABLET(Level1Category.ELECTRONICS, "平板电脑"),
    LAPTOP(Level1Category.ELECTRONICS, "笔记本电脑"),
    DESKTOP(Level1Category.ELECTRONICS, "台式电脑"),
    CAMERA(Level1Category.ELECTRONICS, "相机"),
    PHOTO_EQUIPMENT(Level1Category.ELECTRONICS, "摄影器材"),
    SMART_BAND(Level1Category.ELECTRONICS, "智能手环"),
    SMART_WATCH(Level1Category.ELECTRONICS, "智能手表"),
    GAME_CONSOLE(Level1Category.ELECTRONICS, "游戏主机"),
    GAME_CONTROLLER(Level1Category.ELECTRONICS, "游戏手柄"),
    HEADPHONE(Level1Category.ELECTRONICS, "耳机"),
    SPEAKER(Level1Category.ELECTRONICS, "音箱"),
    SMART_SPEAKER(Level1Category.ELECTRONICS, "智能音箱"),
    SMART_HOME_DEVICE(Level1Category.ELECTRONICS, "智能家居设备"),
    OTHER_ELECTRONICS(Level1Category.ELECTRONICS, "其他电子产品"),

    // APPLIANCE (家用电器)
    RICE_COOKER(Level1Category.APPLIANCE, "电饭煲"),
    MICROWAVE(Level1Category.APPLIANCE, "微波炉"),
    SOY_MILK_MACHINE(Level1Category.APPLIANCE, "豆浆机"),
    OVEN(Level1Category.APPLIANCE, "烤箱"),
    AIR_FRYER(Level1Category.APPLIANCE, "空气炸锅"),
    VACUUM_CLEANER(Level1Category.APPLIANCE, "吸尘器"),
    ROBOT_VACUUM(Level1Category.APPLIANCE, "扫地机器人"),
    AIR_CONDITIONER(Level1Category.APPLIANCE, "空调"),
    FAN(Level1Category.APPLIANCE, "风扇"),
    AIR_PURIFIER(Level1Category.APPLIANCE, "空气净化器"),
    REFRIGERATOR(Level1Category.APPLIANCE, "冰箱"),
    WASHING_MACHINE(Level1Category.APPLIANCE, "洗衣机"),
    DRYER(Level1Category.APPLIANCE, "烘干机"),
    TV(Level1Category.APPLIANCE, "电视"),
    PROJECTOR(Level1Category.APPLIANCE, "投影仪"),
    OTHER_APPLIANCE(Level1Category.APPLIANCE, "其他家用电器"),

    // FURNITURE (家具)
    BED(Level1Category.FURNITURE, "床"),
    MATTRESS(Level1Category.FURNITURE, "床垫"),
    NIGHTSTAND(Level1Category.FURNITURE, "床头柜"),
    SOFA(Level1Category.FURNITURE, "沙发"),
    CHAIR(Level1Category.FURNITURE, "椅子"),
    STOOL(Level1Category.FURNITURE, "凳子"),
    ROCKER(Level1Category.FURNITURE, "摇椅"),
    DINING_TABLE(Level1Category.FURNITURE, "餐桌"),
    COFFEE_TABLE(Level1Category.FURNITURE, "茶几"),
    DESK(Level1Category.FURNITURE, "书桌"),
    OFFICE_DESK(Level1Category.FURNITURE, "办公桌"),
    WARDROBE(Level1Category.FURNITURE, "衣柜"),
    STORAGE_CABINET(Level1Category.FURNITURE, "储物柜"),
    BOOKSHELF(Level1Category.FURNITURE, "书架"),
    SHELVING_UNIT(Level1Category.FURNITURE, "置物架"),
    TABLE_LAMP(Level1Category.FURNITURE, "台灯"),
    FLOOR_LAMP(Level1Category.FURNITURE, "落地灯"),
    OTHER_FURNITURE(Level1Category.FURNITURE, "其他家具"),

    // CLOTHING (服饰)
    T_SHIRT(Level1Category.CLOTHING, "T恤"),
    SHIRT(Level1Category.CLOTHING, "衬衫"),
    JACKET(Level1Category.CLOTHING, "外套"),
    SWEATER(Level1Category.CLOTHING, "毛衣"),
    HOODIE(Level1Category.CLOTHING, "卫衣"),
    PANTS(Level1Category.CLOTHING, "裤子"),
    SKIRT(Level1Category.CLOTHING, "裙子"),
    SHORTS(Level1Category.CLOTHING, "短裤"),
    SNEAKER(Level1Category.CLOTHING, "运动鞋"),
    LEATHER_SHOE(Level1Category.CLOTHING, "皮鞋"),
    BOOT(Level1Category.CLOTHING, "靴子"),
    SANDAL(Level1Category.CLOTHING, "凉鞋"),
    HAT(Level1Category.CLOTHING, "帽子"),
    SCARF(Level1Category.CLOTHING, "围巾"),
    GLOVE(Level1Category.CLOTHING, "手套"),
    BACKPACK(Level1Category.CLOTHING, "背包"),
    HANDBAG(Level1Category.CLOTHING, "手提包"),
    WALLET(Level1Category.CLOTHING, "钱包"),
    SHOULDER_BAG(Level1Category.CLOTHING, "挎包"),
    UNDERWEAR(Level1Category.CLOTHING, "内衣"),
    SOCKS(Level1Category.CLOTHING, "袜子"),
    PAJAMAS(Level1Category.CLOTHING, "睡衣"),
    OTHER_CLOTHING(Level1Category.CLOTHING, "其他服饰"),

    // CONSUMABLE (消耗品)
    SHAMPOO(Level1Category.CONSUMABLE, "洗发水"),
    CONDITIONER(Level1Category.CONSUMABLE, "护发素"),
    TOOTHPASTE(Level1Category.CONSUMABLE, "牙膏"),
    TOOTHBRUSH(Level1Category.CONSUMABLE, "牙刷"),
    COSMETICS(Level1Category.CONSUMABLE, "化妆品"),
    SKINCARE(Level1Category.CONSUMABLE, "护肤品"),
    FOOD(Level1Category.CONSUMABLE, "食品"),
    BEVERAGE(Level1Category.CONSUMABLE, "饮料"),
    SNACK(Level1Category.CONSUMABLE, "零食"),
    OFFICE_SUPPLIES(Level1Category.CONSUMABLE, "办公用品"),
    CLEANING_SUPPLIES(Level1Category.CONSUMABLE, "清洁用品"),
    TISSUE(Level1Category.CONSUMABLE, "纸巾"),
    MEDICINE(Level1Category.CONSUMABLE, "药品"),
    SUPPLEMENT(Level1Category.CONSUMABLE, "保健品"),
    PET_SUPPLIES(Level1Category.CONSUMABLE, "宠物用品"),
    OTHER_CONSUMABLE(Level1Category.CONSUMABLE, "其他消耗品"),

    // SERVICE (服务类)
    REPAIR_SERVICE(Level1Category.SERVICE, "维修服务"),
    HOUSEKEEPING(Level1Category.SERVICE, "家政服务"),
    BEAUTY_SERVICE(Level1Category.SERVICE, "美容服务"),
    FITNESS_SERVICE(Level1Category.SERVICE, "健身服务"),
    TRAVEL_SERVICE(Level1Category.SERVICE, "旅游服务"),
    CONSULTING_SERVICE(Level1Category.SERVICE, "咨询服务"),
    OTHER_SERVICE(Level1Category.SERVICE, "其他服务"),

    // VIRTUAL (虚拟权益)
    GAME_POINTS(Level1Category.VIRTUAL, "游戏点数"),
    TOPUP_CARD(Level1Category.VIRTUAL, "充值卡"),
    VIDEO_MEMBERSHIP(Level1Category.VIRTUAL, "视频会员"),
    MUSIC_MEMBERSHIP(Level1Category.VIRTUAL, "音乐会员"),
    READING_MEMBERSHIP(Level1Category.VIRTUAL, "阅读会员"),
    LIVE_GIFT(Level1Category.VIRTUAL, "直播打赏"),
    VIRTUAL_ITEM(Level1Category.VIRTUAL, "虚拟礼物"),
    GAME_SKIN(Level1Category.VIRTUAL, "游戏皮肤"),
    OTHER_VIRTUAL(Level1Category.VIRTUAL, "其他虚拟权益"),

    // OTHER (其他)
    OTHER_ITEM(Level1Category.OTHER, "其他商品"),
    GIFT(Level1Category.OTHER, "礼品"),
    SOUVENIR(Level1Category.OTHER, "纪念品");

    private final Level1Category level1Category;
    private final String displayName;

    Level2Category(Level1Category level1Category, String displayName) {
        this.level1Category = level1Category;
        this.displayName = displayName;
    }

    public Level1Category getLevel1Category() {
        return level1Category;
    }

    public String getDisplayName() {
        return displayName;
    }

    @JsonValue
    @Override
    public String toString() {
        return name();
    }

    public static Level2Category fromString(String value) {
        if (value == null) {
            return null;
        }
        try {
            return Level2Category.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static Level2Category[] getByLevel1(Level1Category level1Category) {
        return java.util.Arrays.stream(Level2Category.values())
                .filter(cat -> cat.getLevel1Category() == level1Category)
                .toArray(Level2Category[]::new);
    }
}
