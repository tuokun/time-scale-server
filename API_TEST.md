# API 测试文档

## 商品管理 API

### 1. 新增商品

**请求:**
```bash
POST http://localhost:8080/api/items
Content-Type: application/json

{
  "name": "游戏手柄",
  "itemCategory": "ELECTRONICS",
  "purchasePrice": 100.00,
  "purchaseDate": "2024-01-01",
  "ownId": "user001"
}
```

**响应:**
```json
{
  "code": 200,
  "message": "创建成功",
  "data": {
    "id": 1,
    "name": "游戏手柄",
    "itemCategory": "ELECTRONICS",
    "purchasePrice": 100.00,
    "purchaseDate": "2024-01-01",
    "ownId": "user001",
    "deleted": false,
    "markTime": "2024-02-02T22:16:00",
    "updateTime": "2024-02-02T22:16:00"
  }
}
```

**验证规则:**
- `name`: 必填，商品名称不能为空
- `itemCategory`: 必填，商品分类不能为空
- `purchasePrice`: 必填，购买价格必须大于0
- `purchaseDate`: 必填，购买日期不能为空
- `ownId`: 可选，所有者ID

---

### 2. 获取商品详情

**请求:**
```bash
GET http://localhost:8080/api/items/1
```

**响应:**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "name": "游戏手柄",
    "itemCategory": "ELECTRONICS",
    "purchasePrice": 100.00,
    "purchaseDate": "2024-01-01",
    "ownId": "user001",
    "deleted": false,
    "markTime": "2024-02-02T22:16:00",
    "updateTime": "2024-02-02T22:16:00"
  }
}
```

**商品不存在时:**
```json
{
  "code": 500,
  "message": "商品不存在",
  "data": null
}
```

---

### 3. 编辑商品

**请求:**
```bash
PUT http://localhost:8080/api/items/1
Content-Type: application/json

{
  "name": "无线游戏手柄",
  "purchasePrice": 120.00
}
```

**响应:**
```json
{
  "code": 200,
  "message": "更新成功",
  "data": {
    "id": 1,
    "name": "无线游戏手柄",
    "itemCategory": "ELECTRONICS",
    "purchasePrice": 120.00,
    "purchaseDate": "2024-01-01",
    "ownId": "user001",
    "deleted": false,
    "markTime": "2024-02-02T22:16:00",
    "updateTime": "2024-02-02T22:18:00"
  }
}
```

**说明:**
- 所有字段都是可选的，只更新提供的字段
- `purchasePrice` 如果提供必须大于0

---

### 4. 删除商品

**请求:**
```bash
DELETE http://localhost:8080/api/items/1
```

**响应:**
```json
{
  "code": 200,
  "message": "删除成功",
  "data": null
}
```

**说明:**
- 使用软删除，数据不会从数据库中物理删除
- 商品不存在时会抛出异常

---

### 5. 查询商品列表

**请求:**
```bash
GET http://localhost:8080/api/items?pageNum=1&pageSize=10&itemCategory=ELECTRONICS
```

**响应:**
```json
{
  "total": 100,
  "list": [
    {
      "id": 1,
      "name": "无线游戏手柄",
      "itemCategory": "ELECTRONICS",
      "purchasePrice": 120.00,
      "purchaseDate": "2024-01-01",
      "ownId": "user001",
      "deleted": false,
      "markTime": "2024-02-02T22:16:00",
      "updateTime": "2024-02-02T22:18:00"
    }
  ],
  "pageNum": 1,
  "pageSize": 10,
  "pages": 10
}
```

**参数说明:**

| 参数 | 类型 | 必填 | 默认值 | 说明 |
|------|------|------|--------|------|
| pageNum | Integer | 否 | 1 | 页码 |
| pageSize | Integer | 否 | 10 | 每页数量 |
| itemCategory | Enum | 否 | - | 商品分类 |
| nameKeyword | String | 否 | - | 名称关键词（模糊查询） |
| sortBy | String | 否 | markTime | 排序字段 |
| sortOrder | String | 否 | desc | 排序方向（asc/desc） |
| minPurchaseDate | LocalDate | 否 | - | 最小购买日期 |
| maxPurchaseDate | LocalDate | 否 | - | 最大购买日期 |
| minMarkDate | LocalDate | 否 | - | 最小创建日期 |
| maxMarkDate | LocalDate | 否 | - | 最大创建日期 |

**排序字段选项:**
- `markTime`: 创建时间
- `name`: 商品名称
- `purchasePrice`: 购买价格
- `purchaseDate`: 购买日期

---

## 商品分类枚举

| 分类值 | 说明 |
|--------|------|
| DIGITAL | 数字产品（软件、会员、游戏等） |
| ELECTRONICS | 电子产品（手机、手柄、耳机等） |
| APPLIANCE | 家用电器 |
| FURNITURE | 家具 |
| CLOTHING | 服饰 |
| CONSUMABLE | 消耗品（日用品、食品） |
| SERVICE | 服务类（课程、维修） |
| VIRTUAL | 虚拟权益（点数、皮肤） |
| OTHER | 其他 |

---

## 使用 cURL 测试

### 新增商品
```bash
curl -X POST http://localhost:8080/api/items \
  -H "Content-Type: application/json" \
  -d '{
    "name": "游戏手柄",
    "itemCategory": "ELECTRONICS",
    "purchasePrice": 100.00,
    "purchaseDate": "2024-01-01"
  }'
```

### 获取商品详情
```bash
curl http://localhost:8080/api/items/1
```

### 编辑商品
```bash
curl -X PUT http://localhost:8080/api/items/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "无线游戏手柄",
    "purchasePrice": 120.00
  }'
```

### 删除商品
```bash
curl -X DELETE http://localhost:8080/api/items/1
```

### 查询商品列表
```bash
curl "http://localhost:8080/api/items?pageNum=1&pageSize=10&itemCategory=ELECTRONICS"
```

---

## 错误处理

### 参数验证失败（400）
```json
{
  "code": 400,
  "message": "商品名称不能为空; 购买价格必须大于0",
  "data": null
}
```

### 业务逻辑错误（500）
```json
{
  "code": 500,
  "message": "商品名称已存在",
  "data": null
}
```

### 商品不存在
```json
{
  "code": 500,
  "message": "商品不存在",
  "data": null
}
```

### 价格合理性验证
```json
{
  "code": 500,
  "message": "消耗品价格不能超过1000元",
  "data": null
}
```

---

## 响应格式说明

所有接口统一使用以下响应格式：

```json
{
  "code": 200,
  "message": "操作成功",
  "data": { ... }
}
```

| 字段 | 类型 | 说明 |
|------|------|------|
| code | Integer | 状态码，200表示成功 |
| message | String | 响应消息 |
| data | Object | 响应数据，根据接口不同返回不同内容 |
