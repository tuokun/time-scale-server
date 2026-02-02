# Time Scale Server

时光秤后端服务 - 基于Spring Boot的商品性价比分析系统

## 📚 文档导航

- [项目说明文档](../README.md) - 项目简介、快速开始、功能规划
- [项目设计文档](../DESIGN.md) - 系统架构、数据模型、API设计
- [后端设计文档](./DESIGN.md) - 数据库设计、核心算法、技术细节

## 项目简介

Time Scale Server是时光秤项目的后端服务，提供商品管理、性价比计算、统计分析等核心功能的RESTful API。

> 💡 **项目位置**: 本项目位于 `time-scale/` 目录下的 `time-scale-server/` 子目录中，是时光秤整体项目的后端服务部分。关于整体项目的信息，请查看 [父项目说明文档](../README.md)。

## 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Java | 17 | 开发语言 |
| Spring Boot | 3.2.0 | 应用框架 |
| MyBatis Plus | 3.5.5 | ORM框架 |
| MariaDB | - | 关系型数据库 |
| Maven | - | 项目管理 |

## 项目结构

```
time-scale-server/
├── src/
│   ├── main/
│   │   ├── java/com/timescale/server/
│   │   │   ├── controller/      # 控制器层
│   │   │   │   ├── ItemController.java
│   │   │   │   └── ProbeController.java
│   │   │   ├── service/         # 服务层
│   │   │   │   ├── ItemService.java
│   │   │   │   └── impl/
│   │   │   │       └── ItemServiceImpl.java
│   │   │   ├── mapper/          # 数据访问层
│   │   │   │   └── ItemMapper.java
│   │   │   ├── entity/          # 实体类
│   │   │   │   └── Item.java
│   │   │   ├── dto/             # 数据传输对象
│   │   │   │   ├── ItemQueryDTO.java
│   │   │   │   └── PageResult.java
│   │   │   ├── enums/           # 枚举类
│   │   │   │   └── ItemCategory.java
│   │   │   └── TimeScaleServerApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── mapper/         # MyBatis映射文件
│   └── test/                   # 测试代码
├── pom.xml
└── README.md
```

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+
- MariaDB 10.5+

### 数据库准备

```bash
# 创建数据库
mysql -u root -p
CREATE DATABASE time_scale DEFAULT CHARACTER SET utf8mb4;
```

### 配置修改

编辑 `src/main/resources/application.properties`：

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/time_scale
spring.datasource.username=root
spring.datasource.password=your_password
```

### 启动服务

```bash
# 编译项目
mvn clean install

# 启动服务
mvn spring-boot:run
```

服务启动后，访问 `http://localhost:8080/api/probe` 检查服务状态。

### 打包部署

```bash
# 打包
mvn clean package

# 运行jar包
java -jar target/time-scale-server-1.0.0.0.jar
```

## API接口

### 健康检查

```
GET /api/probe
Response: "OK"
```

### 商品查询

```
GET /api/items
```

**参数说明：**

| 参数 | 类型 | 必填 | 默认值 | 说明 |
|------|------|------|--------|------|
| pageNum | Integer | 否 | 1 | 页码 |
| pageSize | Integer | 否 | 10 | 每页数量 |
| itemCategory | Enum | 否 | - | 商品分类 |
| nameKeyword | String | 否 | - | 名称关键词 |
| sortBy | String | 否 | markTime | 排序字段 |
| sortOrder | String | 否 | desc | 排序方向 |
| minPurchaseDate | LocalDate | 否 | - | 最小购买日期 |
| maxPurchaseDate | LocalDate | 否 | - | 最大购买日期 |
| minMarkDate | LocalDate | 否 | - | 最小创建日期 |
| maxMarkDate | LocalDate | 否 | - | 最大创建日期 |

**排序字段选项：**
- markTime: 创建时间
- name: 商品名称
- purchasePrice: 购买价格
- purchaseDate: 购买日期

**请求示例：**

```bash
curl "http://localhost:8080/api/items?pageNum=1&pageSize=10&itemCategory=ELECTRONICS&sortBy=purchasePrice&sortOrder=desc"
```

**响应示例：**

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

## 商品分类

- **DIGITAL**: 数字产品（软件、会员、游戏等）
- **ELECTRONICS**: 电子产品（手机、手柄、耳机等）
- **APPLIANCE**: 家用电器
- **FURNITURE**: 家具
- **CLOTHING**: 服饰
- **CONSUMABLE**: 消耗品（日用品、食品）
- **SERVICE**: 服务类（课程、维修）
- **VIRTUAL**: 虚拟权益（点数、皮肤）
- **OTHER**: 其他

## 开发指南

### 添加新实体

1. 在 `entity` 包下创建实体类
2. 使用 `@TableName` 指定表名
3. 使用 `@TableField` 映射字段
4. 使用 `@TableLogic` 标记软删除字段

### 添加新接口

1. 在 `controller` 包下创建控制器
2. 在 `service` 包下创建服务接口和实现
3. 在 `mapper` 包下创建Mapper接口
4. 在 `dto` 包下创建数据传输对象

### 编码规范

- 遵循阿里巴巴Java开发手册
- 使用Lombok简化代码
- 统一使用RESTful风格
- 方法命名清晰易懂

## 测试

```bash
# 运行所有测试
mvn test

# 运行指定测试类
mvn test -Dtest=ItemServiceTest

# 运行指定测试方法
mvn test -Dtest=ItemServiceTest#testGetItemsByPage
```

## 配置说明

### 应用配置

```properties
# 服务器端口
server.port=8080

# 数据库配置
spring.datasource.url=jdbc:mariadb://localhost:3306/time_scale
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

# JPA配置
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect

# 日志级别
logging.level.com.timescale.server=INFO
```

### 数据库配置

```properties
# 生产环境建议使用连接池
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
```

## 常见问题

### Q: 启动时数据库连接失败？
A: 检查MariaDB服务是否启动，确认数据库配置是否正确。

### Q: 如何修改数据库表结构？
A: 修改实体类后，重启服务，JPA会自动更新表结构（ddl-auto=update）。

### Q: 如何查看SQL日志？
A: 在application.properties中设置 `spring.jpa.show-sql=true`

## 待开发功能

- [ ] 商品新增API
- [ ] 商品编辑API
- [ ] 商品删除API
- [ ] 商品详情API
- [ ] 性价比计算API
- [ ] 使用记录管理
- [ ] 统计分析API
- [ ] Swagger API文档
- [ ] 数据验证
- [ ] 异常处理
- [ ] 日志增强
- [ ] 单元测试

## 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 许可证

本项目采用 MIT 许可证

## 联系方式

- 项目主页: https://github.com/your-username/time-scale
- 问题反馈: https://github.com/your-username/time-scale/issues
