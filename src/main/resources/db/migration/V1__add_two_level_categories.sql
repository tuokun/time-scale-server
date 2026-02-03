-- V1__add_two_level_categories.sql
-- 迁移脚本：将单层分类改造为二层分类体系
-- 创建日期: 2026-02-02

-- =====================================================
-- 1. 修改item表结构
-- =====================================================

-- 将item_category改名为item_category_level1（一级分类）
ALTER TABLE item CHANGE COLUMN item_category item_category_level1 VARCHAR(50) COMMENT '商品一级分类';

-- 添加item_category_level2字段（二级分类）
ALTER TABLE item ADD COLUMN item_category_level2 VARCHAR(50) COMMENT '商品二级分类';

-- 为新字段添加索引
CREATE INDEX idx_item_category_level2 ON item(item_category_level2);

-- =====================================================
-- 2. 创建icon_mapping表（图标映射表）
-- =====================================================

CREATE TABLE icon_mapping (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    sub_category VARCHAR(50) NOT NULL COMMENT '二级分类名称',
    icon VARCHAR(100) NOT NULL COMMENT '图标（emoji或图标名称）',
    icon_type VARCHAR(20) DEFAULT 'emoji' COMMENT '图标类型：emoji/image',
    description VARCHAR(200) COMMENT '描述',
    sort_order INT DEFAULT 0 COMMENT '排序',
    deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '软删除标记',
    mark_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY uk_sub_category (sub_category),
    INDEX idx_sort_order (sort_order),
    INDEX idx_deleted (deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图标映射表';

-- =====================================================
-- 3. 创建category_level1表（一级分类表 - 可选扩展用）
-- =====================================================

CREATE TABLE category_level1 (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    category_code VARCHAR(50) NOT NULL COMMENT '分类代码（如DIGITAL）',
    category_name VARCHAR(50) NOT NULL COMMENT '分类名称（如数字产品）',
    description VARCHAR(200) COMMENT '描述',
    sort_order INT DEFAULT 0 COMMENT '排序',
    deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '软删除标记',
    mark_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY uk_category_code (category_code),
    INDEX idx_sort_order (sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='一级分类表';

-- =====================================================
-- 4. 创建category_level2表（二级分类表 - 可选扩展用）
-- =====================================================

CREATE TABLE category_level2 (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    base_category VARCHAR(50) NOT NULL COMMENT '一级分类代码',
    category_code VARCHAR(50) NOT NULL COMMENT '二级分类代码',
    category_name VARCHAR(50) NOT NULL COMMENT '分类名称',
    description VARCHAR(200) COMMENT '描述',
    sort_order INT DEFAULT 0 COMMENT '排序',
    deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '软删除标记',
    mark_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY uk_category_code (category_code),
    INDEX idx_base_category (base_category),
    INDEX idx_sort_order (sort_order),
    INDEX idx_deleted (deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='二级分类表';

-- =====================================================
-- 5. 插入一级分类数据（8个）
-- =====================================================

INSERT INTO category_level1 (category_code, category_name, description, sort_order) VALUES
('DIGITAL', '数字虚拟', '软件、会员、游戏、虚拟权益等数字商品', 1),
('ELECTRONICS', '电子产品', '手机、电脑、相机等电子设备', 2),
('APPLIANCE', '家用电器', '厨房电器、空调、冰箱等家电', 3),
('FURNITURE', '家具', '床、沙发、桌子等家具', 4),
('CLOTHING', '服饰', '衣服、鞋子、包包等', 5),
('CONSUMABLE', '消耗品', '日用品、食品、药品等消耗品', 6),
('SERVICE', '服务类', '维修、家政、旅游等服务', 7),
('OTHER', '其他', '无法分类的其他商品', 8);

-- =====================================================
-- 6. 插入二级分类数据（约70个）
-- =====================================================

-- DIGITAL (数字虚拟)
INSERT INTO category_level2 (base_category, category_code, category_name, description, sort_order) VALUES
('DIGITAL', 'SOFTWARE_APP', '软件应用', '各类应用程序', 1),
('DIGITAL', 'OFFICE_SOFTWARE', '办公软件', 'Word、Excel等办公软件', 2),
('DIGITAL', 'TOOL_SOFTWARE', '工具软件', '实用工具类软件', 3),
('DIGITAL', 'GAME_SOFTWARE', '游戏软件', 'PC、手机游戏', 4),
('DIGITAL', 'GAME_CONSOLE_SOFTWARE', '游戏机软件', '主机游戏光盘/数字版', 5),
('DIGITAL', 'ONLINE_COURSE', '在线课程', '在线教育课程', 6),
('DIGITAL', 'EBOOK', '电子书籍', '电子书、杂志', 7),
('DIGITAL', 'VIDEO_MEMBERSHIP', '视频会员', '视频网站会员', 8),
('DIGITAL', 'MUSIC_MEMBERSHIP', '音乐会员', '音乐平台会员', 9),
('DIGITAL', 'READING_MEMBERSHIP', '阅读会员', '阅读平台会员', 10),
('DIGITAL', 'OTHER_MEMBERSHIP', '其他会员', '其他订阅服务', 11),
('DIGITAL', 'GAME_POINTS', '游戏点数', '游戏点数', 12),
('DIGITAL', 'TOPUP_CARD', '充值卡', '充值卡', 13),
('DIGITAL', 'LIVE_GIFT', '直播打赏', '直播打赏', 14),
('DIGITAL', 'VIRTUAL_ITEM', '虚拟礼物', '虚拟礼物', 15),
('DIGITAL', 'GAME_SKIN', '游戏皮肤', '游戏皮肤', 16),
('DIGITAL', 'OTHER_DIGITAL', '其他数字产品', '无法分类的数字产品', 17);

-- ELECTRONICS (电子产品)
INSERT INTO category_level2 (base_category, category_code, category_name, description, sort_order) VALUES
('ELECTRONICS', 'MOBILE_PHONE', '手机', '智能手机', 1),
('ELECTRONICS', 'TABLET', '平板电脑', 'iPad等平板设备', 2),
('ELECTRONICS', 'LAPTOP', '笔记本电脑', '便携式电脑', 3),
('ELECTRONICS', 'DESKTOP', '台式电脑', '台式机主机', 4),
('ELECTRONICS', 'CAMERA', '相机', '数码相机', 5),
('ELECTRONICS', 'PHOTO_EQUIPMENT', '摄影器材', '镜头、三脚架等', 6),
('ELECTRONICS', 'SMART_BAND', '智能手环', '运动手环', 7),
('ELECTRONICS', 'SMART_WATCH', '智能手表', '手表式智能设备', 8),
('ELECTRONICS', 'GAME_CONSOLE', '游戏主机', 'PS、Xbox、Switch等', 9),
('ELECTRONICS', 'GAME_CONTROLLER', '游戏手柄', '游戏手柄', 10),
('ELECTRONICS', 'HEADPHONE', '耳机', '有线/无线耳机', 11),
('ELECTRONICS', 'SPEAKER', '音箱', '蓝牙音箱、桌面音箱', 12),
('ELECTRONICS', 'SMART_SPEAKER', '智能音箱', '小爱、天猫精灵等', 13),
('ELECTRONICS', 'SMART_HOME_DEVICE', '智能家居设备', '智能门锁、摄像头等', 14),
('ELECTRONICS', 'OTHER_ELECTRONICS', '其他电子产品', '无法分类的电子产品', 15);

-- APPLIANCE (家用电器)
INSERT INTO category_level2 (base_category, category_code, category_name, description, sort_order) VALUES
('APPLIANCE', 'RICE_COOKER', '电饭煲', '煮饭设备', 1),
('APPLIANCE', 'MICROWAVE', '微波炉', '微波加热设备', 2),
('APPLIANCE', 'SOY_MILK_MACHINE', '豆浆机', '豆浆制作设备', 3),
('APPLIANCE', 'OVEN', '烤箱', '烘焙烤箱', 4),
('APPLIANCE', 'AIR_FRYER', '空气炸锅', '空气炸锅', 5),
('APPLIANCE', 'VACUUM_CLEANER', '吸尘器', '手持吸尘器', 6),
('APPLIANCE', 'ROBOT_VACUUM', '扫地机器人', '自动扫地机器人', 7),
('APPLIANCE', 'AIR_CONDITIONER', '空调', '空调设备', 8),
('APPLIANCE', 'FAN', '风扇', '电风扇', 9),
('APPLIANCE', 'AIR_PURIFIER', '空气净化器', '空气净化设备', 10),
('APPLIANCE', 'REFRIGERATOR', '冰箱', '冷藏/冷冻设备', 11),
('APPLIANCE', 'WASHING_MACHINE', '洗衣机', '洗衣设备', 12),
('APPLIANCE', 'DRYER', '烘干机', '烘干设备', 13),
('APPLIANCE', 'TV', '电视', '电视机', 14),
('APPLIANCE', 'PROJECTOR', '投影仪', '投影设备', 15),
('APPLIANCE', 'OTHER_APPLIANCE', '其他家用电器', '无法分类的家电', 16);

-- FURNITURE (家具)
INSERT INTO category_level2 (base_category, category_code, category_name, description, sort_order) VALUES
('FURNITURE', 'BED', '床', '床铺', 1),
('FURNITURE', 'MATTRESS', '床垫', '床垫', 2),
('FURNITURE', 'NIGHTSTAND', '床头柜', '床头柜', 3),
('FURNITURE', 'SOFA', '沙发', '沙发', 4),
('FURNITURE', 'CHAIR', '椅子', '办公椅、餐椅等', 5),
('FURNITURE', 'STOOL', '凳子', '各类凳子', 6),
('FURNITURE', 'ROCKER', '摇椅', '摇椅', 7),
('FURNITURE', 'DINING_TABLE', '餐桌', '餐桌', 8),
('FURNITURE', 'COFFEE_TABLE', '茶几', '茶几', 9),
('FURNITURE', 'DESK', '书桌', '学习/工作桌', 10),
('FURNITURE', 'OFFICE_DESK', '办公桌', '办公桌', 11),
('FURNITURE', 'WARDROBE', '衣柜', '衣柜', 12),
('FURNITURE', 'STORAGE_CABINET', '储物柜', '储物柜', 13),
('FURNITURE', 'BOOKSHELF', '书架', '书架', 14),
('FURNITURE', 'SHELVING_UNIT', '置物架', '置物架', 15),
('FURNITURE', 'TABLE_LAMP', '台灯', '台灯', 16),
('FURNITURE', 'FLOOR_LAMP', '落地灯', '落地灯', 17),
('FURNITURE', 'OTHER_FURNITURE', '其他家具', '无法分类的家具', 18);

-- CLOTHING (服饰)
INSERT INTO category_level2 (base_category, category_code, category_name, description, sort_order) VALUES
('CLOTHING', 'T_SHIRT', 'T恤', 'T恤衫', 1),
('CLOTHING', 'SHIRT', '衬衫', '衬衫', 2),
('CLOTHING', 'JACKET', '外套', '夹克、外套', 3),
('CLOTHING', 'SWEATER', '毛衣', '针织衫', 4),
('CLOTHING', 'HOODIE', '卫衣', '连帽衫', 5),
('CLOTHING', 'PANTS', '裤子', '长裤、休闲裤', 6),
('CLOTHING', 'SKIRT', '裙子', '裙装', 7),
('CLOTHING', 'SHORTS', '短裤', '短裤', 8),
('CLOTHING', 'SNEAKER', '运动鞋', '运动鞋', 9),
('CLOTHING', 'LEATHER_SHOE', '皮鞋', '皮鞋', 10),
('CLOTHING', 'BOOT', '靴子', '靴子', 11),
('CLOTHING', 'SANDAL', '凉鞋', '凉鞋', 12),
('CLOTHING', 'HAT', '帽子', '帽子', 13),
('CLOTHING', 'SCARF', '围巾', '围巾', 14),
('CLOTHING', 'GLOVE', '手套', '手套', 15),
('CLOTHING', 'BACKPACK', '背包', '背包', 16),
('CLOTHING', 'HANDBAG', '手提包', '手提包', 17),
('CLOTHING', 'WALLET', '钱包', '钱包', 18),
('CLOTHING', 'SHOULDER_BAG', '挎包', '挎包', 19),
('CLOTHING', 'UNDERWEAR', '内衣', '内衣', 20),
('CLOTHING', 'SOCKS', '袜子', '袜子', 21),
('CLOTHING', 'PAJAMAS', '睡衣', '睡衣', 22),
('CLOTHING', 'OTHER_CLOTHING', '其他服饰', '无法分类的服饰', 23);

-- CONSUMABLE (消耗品)
INSERT INTO category_level2 (base_category, category_code, category_name, description, sort_order) VALUES
('CONSUMABLE', 'SHAMPOO', '洗发水', '洗发用品', 1),
('CONSUMABLE', 'CONDITIONER', '护发素', '护发用品', 2),
('CONSUMABLE', 'TOOTHPASTE', '牙膏', '牙膏', 3),
('CONSUMABLE', 'TOOTHBRUSH', '牙刷', '牙刷', 4),
('CONSUMABLE', 'COSMETICS', '化妆品', '彩妆', 5),
('CONSUMABLE', 'SKINCARE', '护肤品', '护肤用品', 6),
('CONSUMABLE', 'FOOD', '食品', '食品', 7),
('CONSUMABLE', 'BEVERAGE', '饮料', '饮料', 8),
('CONSUMABLE', 'SNACK', '零食', '零食', 9),
('CONSUMABLE', 'OFFICE_SUPPLIES', '办公用品', '办公文具', 10),
('CONSUMABLE', 'CLEANING_SUPPLIES', '清洁用品', '清洁用品', 11),
('CONSUMABLE', 'TISSUE', '纸巾', '纸巾', 12),
('CONSUMABLE', 'MEDICINE', '药品', '药品', 13),
('CONSUMABLE', 'SUPPLEMENT', '保健品', '保健品', 14),
('CONSUMABLE', 'PET_SUPPLIES', '宠物用品', '宠物用品', 15),
('CONSUMABLE', 'OTHER_CONSUMABLE', '其他消耗品', '无法分类的消耗品', 16);

-- SERVICE (服务类)
INSERT INTO category_level2 (base_category, category_code, category_name, description, sort_order) VALUES
('SERVICE', 'REPAIR_SERVICE', '维修服务', '维修服务', 1),
('SERVICE', 'HOUSEKEEPING', '家政服务', '家政服务', 2),
('SERVICE', 'BEAUTY_SERVICE', '美容服务', '美容服务', 3),
('SERVICE', 'FITNESS_SERVICE', '健身服务', '健身服务', 4),
('SERVICE', 'TRAVEL_SERVICE', '旅游服务', '旅游服务', 5),
('SERVICE', 'CONSULTING_SERVICE', '咨询服务', '咨询服务', 6),
('SERVICE', 'OTHER_SERVICE', '其他服务', '无法分类的服务', 7);

-- OTHER (其他)
INSERT INTO category_level2 (base_category, category_code, category_name, description, sort_order) VALUES
('OTHER', 'OTHER_ITEM', '其他商品', '无法分类的商品', 1),
('OTHER', 'GIFT', '礼品', '礼品', 2),
('OTHER', 'SOUVENIR', '纪念品', '纪念品', 3);

-- =====================================================
-- 7. 插入图标映射数据
-- =====================================================

INSERT INTO icon_mapping (sub_category, icon, icon_type, description, sort_order) VALUES
-- 电子产品
('MOBILE_PHONE', '📱', 'emoji', '手机', 1),
('TABLET', '📱', 'emoji', '平板电脑', 2),
('LAPTOP', '💻', 'emoji', '笔记本电脑', 3),
('DESKTOP', '💻', 'emoji', '台式电脑', 4),
('CAMERA', '📷', 'emoji', '相机', 5),
('PHOTO_EQUIPMENT', '📷', 'emoji', '摄影器材', 6),
('SMART_BAND', '⌚', 'emoji', '智能手环', 7),
('SMART_WATCH', '⌚', 'emoji', '智能手表', 8),
('GAME_CONSOLE', '🎮', 'emoji', '游戏主机', 9),
('GAME_CONTROLLER', '🎮', 'emoji', '游戏手柄', 10),
('HEADPHONE', '🎧', 'emoji', '耳机', 11),
('SPEAKER', '🔊', 'emoji', '音箱', 12),
('SMART_SPEAKER', '🔊', 'emoji', '智能音箱', 13),
('SMART_HOME_DEVICE', '🏠', 'emoji', '智能家居设备', 14),
('OTHER_ELECTRONICS', '📱', 'emoji', '其他电子产品', 15),

-- 家用电器
('RICE_COOKER', '🍳', 'emoji', '电饭煲', 16),
('MICROWAVE', '🍳', 'emoji', '微波炉', 17),
('SOY_MILK_MACHINE', '🍳', 'emoji', '豆浆机', 18),
('OVEN', '🍳', 'emoji', '烤箱', 19),
('AIR_FRYER', '🍳', 'emoji', '空气炸锅', 20),
('VACUUM_CLEANER', '🧹', 'emoji', '吸尘器', 21),
('ROBOT_VACUUM', '🤖', 'emoji', '扫地机器人', 22),
('AIR_CONDITIONER', '❄️', 'emoji', '空调', 23),
('FAN', '🌬️', 'emoji', '风扇', 24),
('AIR_PURIFIER', '🌬️', 'emoji', '空气净化器', 25),
('REFRIGERATOR', '🧊', 'emoji', '冰箱', 26),
('WASHING_MACHINE', '🧺', 'emoji', '洗衣机', 27),
('DRYER', '🧺', 'emoji', '烘干机', 28),
('TV', '📺', 'emoji', '电视', 29),
('PROJECTOR', '🎬', 'emoji', '投影仪', 30),
('OTHER_APPLIANCE', '🔌', 'emoji', '其他家用电器', 31),

-- 家具
('BED', '🛏️', 'emoji', '床', 32),
('MATTRESS', '🛏️', 'emoji', '床垫', 33),
('NIGHTSTAND', '🗄️', 'emoji', '床头柜', 34),
('SOFA', '🛋️', 'emoji', '沙发', 35),
('CHAIR', '🪑', 'emoji', '椅子', 36),
('STOOL', '🪑', 'emoji', '凳子', 37),
('ROCKER', '🪑', 'emoji', '摇椅', 38),
('DINING_TABLE', '🪵', 'emoji', '餐桌', 39),
('COFFEE_TABLE', '🪵', 'emoji', '茶几', 40),
('DESK', '🪵', 'emoji', '书桌', 41),
('OFFICE_DESK', '🪵', 'emoji', '办公桌', 42),
('WARDROBE', '🗄️', 'emoji', '衣柜', 43),
('STORAGE_CABINET', '🗄️', 'emoji', '储物柜', 44),
('BOOKSHELF', '📚', 'emoji', '书架', 45),
('SHELVING_UNIT', '📚', 'emoji', '置物架', 46),
('TABLE_LAMP', '💡', 'emoji', '台灯', 47),
('FLOOR_LAMP', '💡', 'emoji', '落地灯', 48),
('OTHER_FURNITURE', '🪑', 'emoji', '其他家具', 49),

-- 服饰
('T_SHIRT', '👕', 'emoji', 'T恤', 50),
('SHIRT', '👔', 'emoji', '衬衫', 51),
('JACKET', '🧥', 'emoji', '外套', 52),
('SWEATER', '🧶', 'emoji', '毛衣', 53),
('HOODIE', '🧥', 'emoji', '卫衣', 54),
('PANTS', '👖', 'emoji', '裤子', 55),
('SKIRT', '👗', 'emoji', '裙子', 56),
('SHORTS', '🩳', 'emoji', '短裤', 57),
('SNEAKER', '👟', 'emoji', '运动鞋', 58),
('LEATHER_SHOE', '👞', 'emoji', '皮鞋', 59),
('BOOT', '👢', 'emoji', '靴子', 60),
('SANDAL', '👡', 'emoji', '凉鞋', 61),
('HAT', '🧢', 'emoji', '帽子', 62),
('SCARF', '🧣', 'emoji', '围巾', 63),
('GLOVE', '🧤', 'emoji', '手套', 64),
('BACKPACK', '🎒', 'emoji', '背包', 65),
('HANDBAG', '👜', 'emoji', '手提包', 66),
('WALLET', '👛', 'emoji', '钱包', 67),
('SHOULDER_BAG', '👜', 'emoji', '挎包', 68),
('UNDERWEAR', '🩲', 'emoji', '内衣', 69),
('SOCKS', '🧦', 'emoji', '袜子', 70),
('PAJAMAS', '🛌', 'emoji', '睡衣', 71),
('OTHER_CLOTHING', '👕', 'emoji', '其他服饰', 72),

-- 数字虚拟
('SOFTWARE_APP', '📱', 'emoji', '软件应用', 73),
('OFFICE_SOFTWARE', '📄', 'emoji', '办公软件', 74),
('TOOL_SOFTWARE', '🔧', 'emoji', '工具软件', 75),
('GAME_SOFTWARE', '🎮', 'emoji', '游戏软件', 76),
('GAME_CONSOLE_SOFTWARE', '💿', 'emoji', '游戏机软件', 77),
('ONLINE_COURSE', '📚', 'emoji', '在线课程', 78),
('EBOOK', '📖', 'emoji', '电子书籍', 79),
('VIDEO_MEMBERSHIP', '📺', 'emoji', '视频会员', 80),
('MUSIC_MEMBERSHIP', '🎵', 'emoji', '音乐会员', 81),
('READING_MEMBERSHIP', '📚', 'emoji', '阅读会员', 82),
('OTHER_MEMBERSHIP', '🎫', 'emoji', '其他会员', 83),
('GAME_POINTS', '🎯', 'emoji', '游戏点数', 84),
('TOPUP_CARD', '💳', 'emoji', '充值卡', 85),
('LIVE_GIFT', '🎁', 'emoji', '直播打赏', 86),
('VIRTUAL_ITEM', '🎁', 'emoji', '虚拟礼物', 87),
('GAME_SKIN', '🎨', 'emoji', '游戏皮肤', 88),
('OTHER_DIGITAL', '💾', 'emoji', '其他数字产品', 89),

-- 消耗品
('SHAMPOO', '🧴', 'emoji', '洗发水', 84),
('CONDITIONER', '🧴', 'emoji', '护发素', 85),
('TOOTHPASTE', '🦷', 'emoji', '牙膏', 86),
('TOOTHBRUSH', '🦷', 'emoji', '牙刷', 87),
('COSMETICS', '💄', 'emoji', '化妆品', 88),
('SKINCARE', '🧴', 'emoji', '护肤品', 89),
('FOOD', '🍔', 'emoji', '食品', 90),
('BEVERAGE', '🥤', 'emoji', '饮料', 91),
('SNACK', '🍿', 'emoji', '零食', 92),
('OFFICE_SUPPLIES', '📝', 'emoji', '办公用品', 93),
('CLEANING_SUPPLIES', '🧹', 'emoji', '清洁用品', 94),
('TISSUE', '🧻', 'emoji', '纸巾', 95),
('MEDICINE', '💊', 'emoji', '药品', 96),
('SUPPLEMENT', '💊', 'emoji', '保健品', 97),
('PET_SUPPLIES', '🐾', 'emoji', '宠物用品', 98),
('OTHER_CONSUMABLE', '🛒', 'emoji', '其他消耗品', 99),

-- 服务类
('REPAIR_SERVICE', '🔧', 'emoji', '维修服务', 100),
('HOUSEKEEPING', '🧹', 'emoji', '家政服务', 101),
('BEAUTY_SERVICE', '💅', 'emoji', '美容服务', 102),
('FITNESS_SERVICE', '💪', 'emoji', '健身服务', 103),
('TRAVEL_SERVICE', '✈️', 'emoji', '旅游服务', 104),
('CONSULTING_SERVICE', '💬', 'emoji', '咨询服务', 105),
('OTHER_SERVICE', '🤝', 'emoji', '其他服务', 106),

-- 其他
('OTHER_ITEM', '📦', 'emoji', '其他商品', 107),
('GIFT', '🎁', 'emoji', '礼品', 108),
('SOUVENIR', '🏆', 'emoji', '纪念品', 109);

-- 其他
('OTHER_ITEM', '📦', 'emoji', '其他商品', 116),
('GIFT', '🎁', 'emoji', '礼品', 117),
('SOUVENIR', '🏆', 'emoji', '纪念品', 118);

-- =====================================================
-- 8. 迁移现有item数据（如果有的话）
-- =====================================================

-- 将现有item的item_category_level1的值设置正确
-- item_category_level2暂时设置为空，后续需要手动补充
-- UPDATE item SET item_category_level2 = NULL WHERE item_category_level2 IS NULL;
