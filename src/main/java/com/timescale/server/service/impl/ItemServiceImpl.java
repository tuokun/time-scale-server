package com.timescale.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timescale.server.dto.ItemQueryDTO;
import com.timescale.server.dto.PageResult;
import com.timescale.server.entity.Item;
import com.timescale.server.mapper.ItemMapper;
import com.timescale.server.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ItemServiceImpl implements ItemService {
    
    private final ItemMapper itemMapper;
    
    public ItemServiceImpl(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }
    
    @Override
    public PageResult<Item> getItemsByPage(ItemQueryDTO queryDTO) {
        LambdaQueryWrapper<Item> queryWrapper = new LambdaQueryWrapper<>();
        
        if (queryDTO.getItemCategory() != null) {
            queryWrapper.eq(Item::getItemCategory, queryDTO.getItemCategory());
        }
        
        if (StringUtils.hasText(queryDTO.getNameKeyword())) {
            queryWrapper.like(Item::getName, queryDTO.getNameKeyword());
        }
        
        if (queryDTO.getMinPurchaseDate() != null) {
            queryWrapper.ge(Item::getPurchaseDate, queryDTO.getMinPurchaseDate());
        }
        
        if (queryDTO.getMaxPurchaseDate() != null) {
            queryWrapper.le(Item::getPurchaseDate, queryDTO.getMaxPurchaseDate());
        }
        
        if (queryDTO.getMinMarkDate() != null) {
            queryWrapper.apply("DATE(mark_time) >= {0}", queryDTO.getMinMarkDate());
        }
        
        if (queryDTO.getMaxMarkDate() != null) {
            queryWrapper.apply("DATE(mark_time) <= {0}", queryDTO.getMaxMarkDate());
        }
        
        switch (queryDTO.getSortBy()) {
            case "name":
                queryWrapper.orderBy(true, "asc".equals(queryDTO.getSortOrder()), Item::getName);
                break;
            case "purchasePrice":
                queryWrapper.orderBy(true, "asc".equals(queryDTO.getSortOrder()), Item::getPurchasePrice);
                break;
            case "purchaseDate":
                queryWrapper.orderBy(true, "asc".equals(queryDTO.getSortOrder()), Item::getPurchaseDate);
                break;
            case "markTime":
            default:
                queryWrapper.orderBy(true, "asc".equals(queryDTO.getSortOrder()), Item::getMarkTime);
                break;
        }
        
        Page<Item> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        IPage<Item> itemPage = itemMapper.selectPage(page, queryWrapper);
        
        return new PageResult<>(
            itemPage.getRecords(),
            itemPage.getTotal(),
            (int) itemPage.getCurrent(),
            (int) itemPage.getSize()
        );
    }
}