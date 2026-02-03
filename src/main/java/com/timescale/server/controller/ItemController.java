package com.timescale.server.controller;

import com.timescale.server.dto.ItemCreateDTO;
import com.timescale.server.dto.ItemQueryDTO;
import com.timescale.server.dto.ItemUpdateDTO;
import com.timescale.server.dto.PageResult;
import com.timescale.server.dto.ResponseDTO;
import com.timescale.server.entity.Item;
import com.timescale.server.enums.BaseCategory;
import com.timescale.server.enums.SubCategory;
import com.timescale.server.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public PageResult<Item> getItems(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) BaseCategory baseCategory,
            @RequestParam(required = false) SubCategory subCategory,
            @RequestParam(required = false) String nameKeyword,
            @RequestParam(defaultValue = "markTime") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate minPurchaseDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate maxPurchaseDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate minMarkDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate maxMarkDate
    ) {
        ItemQueryDTO queryDTO = new ItemQueryDTO();
        queryDTO.setPageNum(pageNum);
        queryDTO.setPageSize(pageSize);
        queryDTO.setItemCategoryLevel1(baseCategory);
        queryDTO.setItemCategoryLevel2(subCategory);
        queryDTO.setNameKeyword(nameKeyword);
        queryDTO.setSortBy(sortBy);
        queryDTO.setSortOrder(sortOrder);
        queryDTO.setMinPurchaseDate(minPurchaseDate);
        queryDTO.setMaxPurchaseDate(maxPurchaseDate);
        queryDTO.setMinMarkDate(minMarkDate);
        queryDTO.setMaxMarkDate(maxMarkDate);

        return itemService.getItemsByPage(queryDTO);
    }

    @GetMapping("/{id}")
    public ResponseDTO<Item> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        if (item == null) {
            return ResponseDTO.error("商品不存在");
        }
        return ResponseDTO.success(item);
    }

    @PostMapping
    public ResponseDTO<Item> createItem(@Valid @RequestBody ItemCreateDTO createDTO) {
        Item item = itemService.createItem(createDTO);
        return ResponseDTO.success("创建成功", item);
    }

    @PutMapping("/{id}")
    public ResponseDTO<Item> updateItem(@PathVariable Long id, @Valid @RequestBody ItemUpdateDTO updateDTO) {
        Item item = itemService.updateItem(id, updateDTO);
        return ResponseDTO.success("更新成功", item);
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseDTO.success("删除成功", null);
    }
}