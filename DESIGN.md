# Time Scale Server - 设计文档

## 📚 文档导航

- [项目说明文档](../README.md) - 项目简介、快速开始、功能规划
- [项目设计文档](../DESIGN.md) - 系统架构、数据模型、API设计
- [后端服务文档](./README.md) - 后端项目说明、API接口、开发指南

> 💡 **项目位置**: 本文档是后端服务的技术设计文档。关于整体项目的设计和规划，请查看 [父项目设计文档](../DESIGN.md) 了解完整的系统架构和功能设计。

## 1. 系统设计

### 1.1 架构设计

#### 分层架构
```
┌─────────────────────────────────────────────────┐
│          Controller Layer (控制器层)              │
│       处理HTTP请求，参数验证，响应封装            │
└─────────────────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────┐
│            Service Layer (业务逻辑层)             │
│           业务逻辑处理，事务管理                   │
└─────────────────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────┐
│           Mapper Layer (数据访问层)               │
│         数据库操作，SQL映射                       │
└─────────────────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────┐
│             Database (数据库层)                   │
│              MariaDB数据存储                      │
└─────────────────────────────────────────────────┘
```

#### 技术架构图
```
┌─────────────────────────────────────────────────┐
│               Spring Boot 3.2.0                  │
│  ┌───────────────────────────────────────────┐  │
│  │  Spring Web MVC (RESTful API)             │  │
│  │  Spring Data JPA (ORM)                    │  │
│  │  MyBatis Plus (增强ORM)                    │  │
│  └───────────────────────────────────────────┘  │
│  ┌───────────────────────────────────────────┐  │
│  │  Spring Actuator (监控)                   │  │
│  │  Spring Validation (参数校验)              │  │
│  └───────────────────────────────────────────┘  │
└─────────────────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────┐
│              MariaDB 10.5+                        │
└─────────────────────────────────────────────────┘
```

### 1.2 模块设计

#### Controller层职责
- 接收HTTP请求
- 参数绑定和验证
- 调用Service层处理业务
- 封装响应结果

#### Service层职责
- 实现业务逻辑
- 数据转换和组装
- 事务管理
- 调用Mapper层访问数据

#### Mapper层职责
- 定义数据库操作接口
- 使用MyBatis Plus进行CRUD
- 复杂查询实现

#### Entity层职责
- 定义数据库表实体
- 字段映射关系
- 逻辑关系表达

#### DTO层职责
- 数据传输对象
- 查询条件封装
- 响应结果封装

## 2. 数据库设计

### 2.1 已实现表

#### item（商品表）

| 字段名 | 类型 | 长度 | 允许空 | 默认值 | 说明 |
|--------|------|------|--------|--------|------|
| id | BIGINT | - | NO | AUTO_INCREMENT | 主键 |
| name | VARCHAR | 255 | NO | - | 商品名称 |
| item_category | VARCHAR | 50 | YES | NULL | 商品分类 |
| purchase_price | DECIMAL | 10,2 | NO | - | 购买价格 |
| purchase_date | DATE | - | NO | - | 购买日期 |
| own_id | VARCHAR | 100 | YES | NULL | 所有者ID |
| deleted | TINYINT | 1 | NO | 0 | 软删除标记 |
| mark_time | DATETIME | - | NO | CURRENT_TIMESTAMP | 创建时间 |
| update_time | DATETIME | - | NO | CURRENT_TIMESTAMP ON UPDATE | 更新时间 |

**索引设计：**
```sql
PRIMARY KEY (id)
INDEX idx_item_category (item_category)
INDEX idx_purchase_date (purchase_date)
INDEX idx_own_id (own_id)
INDEX idx_mark_time (mark_time)
INDEX idx_deleted (deleted)
```

### 2.2 待扩展表

#### usage_record（使用记录表）

| 字段名 | 类型 | 长度 | 允许空 | 默认值 | 说明 |
|--------|------|------|--------|--------|------|
| id | BIGINT | - | NO | AUTO_INCREMENT | 主键 |
| item_id | BIGINT | - | NO | - | 商品ID |
| usage_date | DATE | - | NO | - | 使用日期 |
| duration | INT | - | YES | NULL | 使用时长（分钟） |
| notes | VARCHAR | 500 | YES | NULL | 备注 |
| deleted | TINYINT | 1 | NO | 0 | 软删除标记 |
| mark_time | DATETIME | - | NO | CURRENT_TIMESTAMP | 创建时间 |
| update_time | DATETIME | - | NO | CURRENT_TIMESTAMP ON UPDATE | 更新时间 |

**索引设计：**
```sql
PRIMARY KEY (id)
INDEX idx_item_id (item_id)
INDEX idx_usage_date (usage_date)
FOREIGN KEY (item_id) REFERENCES item(id)
```

#### cost_efficiency（性价比表）

| 字段名 | 类型 | 长度 | 允许空 | 默认值 | 说明 |
|--------|------|------|--------|--------|------|
| id | BIGINT | - | NO | AUTO_INCREMENT | 主键 |
| item_id | BIGINT | - | NO | - | 商品ID |
| query_date | DATE | - | NO | - | 查询日期 |
| usage_days | INT | - | NO | - | 使用天数 |
| daily_cost | DECIMAL | 10,4 | NO | - | 日均成本 |
| efficiency_index | DECIMAL | 10,4 | NO | - | 性价比指数 |
| rank | INT | - | YES | NULL | 排名 |
| mark_time | DATETIME | - | NO | CURRENT_TIMESTAMP | 创建时间 |

**索引设计：**
```sql
PRIMARY KEY (id)
UNIQUE INDEX uk_item_date (item_id, query_date)
INDEX idx_query_date (query_date)
INDEX idx_rank (rank)
FOREIGN KEY (item_id) REFERENCES item(id)
```

### 2.3 ER图

```
┌─────────────────┐       ┌─────────────────────────┐
│     item        │       │    usage_record          │
├─────────────────┤       ├─────────────────────────┤
│ PK id           │◄──────│ FK item_id               │
│    name         │       │    PK id                │
│    item_category│       │    usage_date            │
│    purchase_price│      │    duration              │
│    purchase_date│       │    notes                 │
│    own_id       │       │    mark_time             │
│    mark_time    │       └─────────────────────────┘
│    update_time  │
└─────────────────┘
        │
        │
        │
        ▼
┌─────────────────────────┐
│   cost_efficiency       │
├─────────────────────────┤
│ PK id                   │
│ FK item_id              │
│    query_date           │
│    usage_days           │
│    daily_cost           │
│    efficiency_index     │
│    rank                 │
│    mark_time            │
└─────────────────────────┘
```

## 3. API设计

### 3.1 RESTful规范

| 方法 | 路径 | 说明 | 示例 |
|------|------|------|------|
| GET | /api/items | 查询商品列表 | GET /api/items?pageNum=1 |
| GET | /api/items/{id} | 获取商品详情 | GET /api/items/1 |
| POST | /api/items | 新增商品 | POST /api/items |
| PUT | /api/items/{id} | 更新商品 | PUT /api/items/1 |
| DELETE | /api/items/{id} | 删除商品 | DELETE /api/items/1 |

### 3.2 已实现API

#### GET /api/probe
健康检查接口

**响应：**
```
"OK"
```

#### GET /api/items
查询商品列表

**请求参数：**
```java
{
  pageNum: Integer,           // 页码，默认1
  pageSize: Integer,          // 每页数量，默认10
  itemCategory: ItemCategory, // 商品分类
  nameKeyword: String,        // 名称关键词
  sortBy: String,             // 排序字段
  sortOrder: String,          // 排序方向
  minPurchaseDate: LocalDate, // 最小购买日期
  maxPurchaseDate: LocalDate, // 最大购买日期
  minMarkDate: LocalDate,     // 最小创建日期
  maxMarkDate: LocalDate      // 最大创建日期
}
```

**响应：**
```json
{
  "total": 100,
  "list": [
    {
      "id": 1,
      "name": "手柄",
      "itemCategory": "ELECTRONICS",
      "purchasePrice": 100.00,
      "purchaseDate": "2024-01-01",
      "ownId": "user001",
      "markTime": "2024-01-01T10:00:00",
      "updateTime": "2024-01-01T10:00:00"
    }
  ]
}
```

### 3.3 待实现API

#### 商品管理

##### POST /api/items
新增商品

**请求体：**
```json
{
  "name": "手柄",
  "itemCategory": "ELECTRONICS",
  "purchasePrice": 100.00,
  "purchaseDate": "2024-01-01",
  "ownId": "user001"
}
```

**响应：**
```json
{
  "id": 1,
  "name": "手柄",
  "itemCategory": "ELECTRONICS",
  "purchasePrice": 100.00,
  "purchaseDate": "2024-01-01",
  "ownId": "user001",
  "markTime": "2024-01-01T10:00:00",
  "updateTime": "2024-01-01T10:00:00"
}
```

##### PUT /api/items/{id}
更新商品

**请求体：**
```json
{
  "name": "游戏手柄",
  "itemCategory": "ELECTRONICS",
  "purchasePrice": 120.00
}
```

**响应：**同新增商品响应

##### DELETE /api/items/{id}
删除商品（软删除）

**响应：**
```json
{
  "success": true,
  "message": "删除成功"
}
```

##### GET /api/items/{id}
获取商品详情

**响应：**同新增商品响应

#### 性价比计算

##### GET /api/items/{id}/efficiency
获取商品性价比

**请求参数：**
- queryDate: 查询日期（可选，默认当前日期）

**响应：**
```json
{
  "itemId": 1,
  "itemName": "手柄",
  "queryDate": "2024-04-10",
  "purchaseDate": "2024-01-01",
  "purchasePrice": 100.00,
  "usageDays": 100,
  "dailyCost": 1.00,
  "efficiencyIndex": 1.0,
  "efficiencyLevel": "MEDIUM"
}
```

##### POST /api/items/efficiency/batch
批量计算性价比

**请求体：**
```json
{
  "queryDate": "2024-04-10",
  "itemIds": [1, 2, 3]
}
```

**响应：**
```json
{
  "results": [
    {
      "itemId": 1,
      "itemName": "手柄",
      "dailyCost": 1.00,
      "efficiencyIndex": 1.0,
      "rank": 1
    }
  ]
}
```

#### 使用记录

##### GET /api/items/{id}/usage-records
获取使用记录

**请求参数：**
- pageNum: 页码
- pageSize: 每页数量
- minDate: 最小日期
- maxDate: 最大日期

**响应：**
```json
{
  "total": 50,
  "list": [
    {
      "id": 1,
      "itemId": 1,
      "usageDate": "2024-01-01",
      "duration": 120,
      "notes": "打游戏",
      "markTime": "2024-01-01T20:00:00"
    }
  ]
}
```

##### POST /api/items/{id}/usage-records
新增使用记录

**请求体：**
```json
{
  "usageDate": "2024-01-01",
  "duration": 120,
  "notes": "打游戏"
}
```

**响应：**同获取使用记录响应

##### PUT /api/usage-records/{id}
更新使用记录

**请求体：**同新增使用记录请求体

**响应：**同获取使用记录响应

##### DELETE /api/usage-records/{id}
删除使用记录

**响应：**
```json
{
  "success": true,
  "message": "删除成功"
}
```

#### 统计分析

##### GET /api/statistics/summary
获取概览统计

**响应：**
```json
{
  "totalItems": 100,
  "totalPurchasePrice": 50000.00,
  "avgDailyCost": 10.50,
  "mostUsedItem": {
    "id": 1,
    "name": "手柄",
    "usageDays": 365
  },
  "categoryDistribution": [
    {
      "category": "ELECTRONICS",
      "count": 30,
      "percentage": 30.0
    }
  ]
}
```

##### GET /api/statistics/category
分类统计

**响应：**
```json
{
  "results": [
    {
      "category": "ELECTRONICS",
      "totalItems": 30,
      "totalPurchasePrice": 15000.00,
      "avgDailyCost": 8.50
    }
  ]
}
```

##### GET /api/statistics/ranking
性价比排行榜

**请求参数：**
- queryDate: 查询日期
- category: 商品分类（可选）
- limit: 数量限制（默认10）

**响应：**
```json
{
  "results": [
    {
      "rank": 1,
      "itemId": 1,
      "itemName": "手柄",
      "itemCategory": "ELECTRONICS",
      "purchasePrice": 100.00,
      "usageDays": 365,
      "dailyCost": 0.27,
      "efficiencyIndex": 3.65
    }
  ]
}
```

##### GET /api/statistics/trend
趋势分析

**请求参数：**
- itemId: 商品ID（可选）
- startDate: 开始日期
- endDate: 结束日期
- interval: 时间间隔（day/week/month）

**响应：**
```json
{
  "trend": [
    {
      "date": "2024-01-01",
      "dailyCost": 100.00,
      "efficiencyIndex": 0.01
    },
    {
      "date": "2024-01-02",
      "dailyCost": 50.00,
      "efficiencyIndex": 0.02
    }
  ]
}
```

## 4. 核心算法设计

### 4.1 性价比计算

```java
public CostEfficiencyDTO calculateEfficiency(Long itemId, LocalDate queryDate) {
    Item item = itemMapper.selectById(itemId);
    
    // 计算使用天数
    long usageDays = ChronoUnit.DAYS.between(item.getPurchaseDate(), queryDate);
    usageDays = Math.max(1, usageDays); // 避免除零
    
    // 计算日均成本
    BigDecimal dailyCost = item.getPurchasePrice()
        .divide(BigDecimal.valueOf(usageDays), 2, RoundingMode.HALF_UP);
    
    // 计算性价比指数
    BigDecimal efficiencyIndex = BigDecimal.valueOf(usageDays)
        .divide(item.getPurchasePrice(), 4, RoundingMode.HALF_UP);
    
    // 评估性价比等级
    EfficiencyLevel level = evaluateEfficiencyLevel(efficiencyIndex);
    
    return CostEfficiencyDTO.builder()
        .itemId(itemId)
        .itemName(item.getName())
        .queryDate(queryDate)
        .purchaseDate(item.getPurchaseDate())
        .purchasePrice(item.getPurchasePrice())
        .usageDays((int) usageDays)
        .dailyCost(dailyCost)
        .efficiencyIndex(efficiencyIndex)
        .efficiencyLevel(level)
        .build();
}

public enum EfficiencyLevel {
    VERY_LOW("极低", 0, 0.01),
    LOW("低", 0.01, 0.05),
    MEDIUM("中等", 0.05, 0.1),
    HIGH("高", 0.1, 0.5),
    VERY_HIGH("极高", 0.5, Double.MAX_VALUE);
}
```

### 4.2 排名计算

```java
public List<CostEfficiencyRankDTO> calculateRanking(LocalDate queryDate, 
                                                    ItemCategory category, 
                                                    int limit) {
    // 查询所有商品
    LambdaQueryWrapper<Item> wrapper = new LambdaQueryWrapper<>();
    if (category != null) {
        wrapper.eq(Item::getItemCategory, category);
    }
    List<Item> items = itemMapper.selectList(wrapper);
    
    // 计算性价比
    List<CostEfficiencyDTO> efficiencies = items.stream()
        .map(item -> calculateEfficiency(item.getId(), queryDate))
        .collect(Collectors.toList());
    
    // 按性价比指数排序
    efficiencies.sort(Comparator
        .comparing(CostEfficiencyDTO::getEfficiencyIndex)
        .reversed());
    
    // 生成排名
    List<CostEfficiencyRankDTO> ranks = new ArrayList<>();
    for (int i = 0; i < Math.min(limit, efficiencies.size()); i++) {
        CostEfficiencyDTO dto = efficiencies.get(i);
        ranks.add(CostEfficiencyRankDTO.builder()
            .rank(i + 1)
            .itemId(dto.getItemId())
            .itemName(dto.getItemName())
            .itemCategory(category)
            .purchasePrice(dto.getPurchasePrice())
            .usageDays(dto.getUsageDays())
            .dailyCost(dto.getDailyCost())
            .efficiencyIndex(dto.getEfficiencyIndex())
            .build());
    }
    
    return ranks;
}
```

## 5. 安全设计

### 5.1 数据验证

#### 输入验证
- 使用 `@Valid` 和 `@Validated` 进行参数校验
- 自定义验证注解
- 统一异常处理

#### 数据安全
- 敏感数据加密
- SQL注入防护（MyBatis Plus预编译）
- XSS防护

### 5.2 访问控制（待实现）

#### 认证机制
- JWT Token认证
- Session管理
- 权限控制

#### 数据隔离
- 基于ownId的数据隔离
- 多租户支持

## 6. 性能优化

### 6.1 数据库优化

#### 索引优化
```sql
-- 商品表索引
CREATE INDEX idx_item_category ON item(item_category);
CREATE INDEX idx_purchase_date ON item(purchase_date);
CREATE INDEX idx_own_id ON item(own_id);
CREATE INDEX idx_mark_time ON item(mark_time);

-- 使用记录表索引
CREATE INDEX idx_usage_record_item_id ON usage_record(item_id);
CREATE INDEX idx_usage_record_date ON usage_record(usage_date);

-- 性价比表索引
CREATE UNIQUE INDEX idx_cost_efficiency_item_date ON cost_efficiency(item_id, query_date);
CREATE INDEX idx_cost_efficiency_rank ON cost_efficiency(rank);
```

#### 查询优化
- 使用分页查询避免全表扫描
- 合理使用索引
- 避免N+1查询问题

### 6.2 缓存策略（待实现）

#### Redis缓存
- 商品列表缓存
- 性价比计算结果缓存
- 统计数据缓存

### 6.3 连接池配置

```properties
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.idle-timeout=600000
spring.datasource.hikari.max-lifetime=1800000
```

## 7. 监控与日志

### 7.1 健康检查

Spring Boot Actuator端点：
- `/actuator/health` - 健康状态
- `/actuator/info` - 应用信息
- `/actuator/metrics` - 指标数据

### 7.2 日志配置

```properties
# 日志级别
logging.level.com.timescale.server=INFO
logging.level.org.springframework.web=DEBUG

# 日志格式
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n

# 日志文件
logging.file.name=logs/time-scale-server.log
logging.file.max-size=10MB
logging.file.max-history=30
```

### 7.3 性能监控（待实现）

- 慢查询监控
- 接口响应时间监控
- 异常告警

## 8. 测试设计

### 8.1 单元测试

#### 测试范围
- Service层业务逻辑测试
- Utility工具类测试
- 算法计算测试

#### 测试框架
- JUnit 5
- Mockito
- AssertJ

### 8.2 集成测试

#### 测试范围
- Controller层接口测试
- 数据库操作测试
- 完整业务流程测试

#### 测试工具
- Spring Boot Test
- TestContainers（数据库集成测试）

### 8.3 测试覆盖率

目标覆盖率：
- Service层: > 90%
- Mapper层: > 80%
- Controller层: > 70%

## 9. 部署设计

### 9.1 开发环境

```bash
# 本地运行
mvn spring-boot:run

# 使用H2内存数据库测试
spring.datasource.url=jdbc:h2:mem:testdb
```

### 9.2 生产环境

#### Docker部署
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/time-scale-server-1.0.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

#### Docker Compose
```yaml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - db
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mariadb://db:3306/time_scale
      
  db:
    image: mariadb:10.5
    environment:
      - MYSQL_ROOT_PASSWORD=password
      - MYSQL_DATABASE=time_scale
    volumes:
      - db_data:/var/lib/mysql
      
volumes:
  db_data:
```

### 9.3 配置管理

#### 配置文件
- `application.properties` - 默认配置
- `application-dev.properties` - 开发环境
- `application-prod.properties` - 生产环境

#### 环境变量
```bash
SPRING_PROFILES_ACTIVE=prod
SPRING_DATASOURCE_URL=jdbc:mariadb://localhost:3306/time_scale
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=secret
```

## 10. 迭代计划

### Phase 1: 基础功能（当前）
- [x] 商品查询
- [ ] 商品CRUD
- [ ] 数据验证
- [ ] 异常处理
- [ ] 单元测试

### Phase 2: 核心功能
- [ ] 性价比计算
- [ ] 使用记录
- [ ] 统计分析
- [ ] API文档

### Phase 3: 优化增强
- [ ] 缓存优化
- [ ] 性能优化
- [ ] 日志增强
- [ ] 监控告警

### Phase 4: 安全完善
- [ ] 认证授权
- [ ] 数据隔离
- [ ] 安全加固
