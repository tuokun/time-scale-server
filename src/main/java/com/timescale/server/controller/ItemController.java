package com.timescale.server.controller;

import com.timescale.server.dto.ItemQueryDTO;
import com.timescale.server.dto.PageResult;
import com.timescale.server.entity.Item;
import com.timescale.server.enums.ItemCategory;
import com.timescale.server.service.ItemService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
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
            @RequestParam(required = false) ItemCategory itemCategory,
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
        queryDTO.setItemCategory(itemCategory);
        queryDTO.setNameKeyword(nameKeyword);
        queryDTO.setSortBy(sortBy);
        queryDTO.setSortOrder(sortOrder);
        queryDTO.setMinPurchaseDate(minPurchaseDate);
        queryDTO.setMaxPurchaseDate(maxPurchaseDate);
        queryDTO.setMinMarkDate(minMarkDate);
        queryDTO.setMaxMarkDate(maxMarkDate);
        
        return itemService.getItemsByPage(queryDTO);
    }
}