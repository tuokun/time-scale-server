package com.timescale.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timescale.server.dto.ItemCreateDTO;
import com.timescale.server.dto.ItemQueryDTO;
import com.timescale.server.dto.ItemUpdateDTO;
import com.timescale.server.dto.PageResult;
import com.timescale.server.entity.Item;
import com.timescale.server.enums.BaseCategory;
import com.timescale.server.exception.BusinessException;
import com.timescale.server.mapper.ItemMapper;
import com.timescale.server.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;

    public ItemServiceImpl(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public PageResult<Item> getItemsByPage(ItemQueryDTO queryDTO) {
        LambdaQueryWrapper<Item> queryWrapper = new LambdaQueryWrapper<>();

        if (queryDTO.getItemCategoryLevel1() != null) {
            queryWrapper.eq(Item::getItemCategoryLevel1, queryDTO.getItemCategoryLevel1());
        }

        if (queryDTO.getItemCategoryLevel2() != null) {
            queryWrapper.eq(Item::getItemCategoryLevel2, queryDTO.getItemCategoryLevel2());
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

    @Override
    public Item getItemById(Long id) {
        return itemMapper.selectById(id);
    }

    @Override
    public Item createItem(ItemCreateDTO createDTO) {
        LambdaQueryWrapper<Item> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Item::getName, createDTO.getName());
        if (itemMapper.selectCount(wrapper) > 0) {
            throw new BusinessException("商品名称已存在");
        }

        validatePurchasePrice(createDTO.getItemCategoryLevel1(), createDTO.getPurchasePrice());

        Item item = new Item();
        item.setName(createDTO.getName());
        item.setItemCategoryLevel1(createDTO.getItemCategoryLevel1());
        item.setItemCategoryLevel2(createDTO.getItemCategoryLevel2());
        item.setPurchasePrice(createDTO.getPurchasePrice());
        item.setPurchaseDate(createDTO.getPurchaseDate());
        item.setOwnId(createDTO.getOwnId());
        item.setDeleted(false);
        item.setMarkTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());

        itemMapper.insert(item);
        return item;
    }

    @Override
    public Item updateItem(Long id, ItemUpdateDTO updateDTO) {
        Item existingItem = itemMapper.selectById(id);
        if (existingItem == null) {
            throw new BusinessException("商品不存在");
        }

        if (StringUtils.hasText(updateDTO.getName())) {
            LambdaQueryWrapper<Item> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Item::getName, updateDTO.getName());
            wrapper.ne(Item::getId, id);
            if (itemMapper.selectCount(wrapper) > 0) {
                throw new BusinessException("商品名称已存在");
            }
            existingItem.setName(updateDTO.getName());
        }

        if (updateDTO.getItemCategoryLevel1() != null || updateDTO.getPurchasePrice() != null) {
            BaseCategory category = updateDTO.getItemCategoryLevel1() != null
                ? updateDTO.getItemCategoryLevel1()
                : existingItem.getItemCategoryLevel1();
            var price = updateDTO.getPurchasePrice() != null
                ? updateDTO.getPurchasePrice()
                : existingItem.getPurchasePrice();
            validatePurchasePrice(category, price);
        }

        if (updateDTO.getItemCategoryLevel1() != null) {
            existingItem.setItemCategoryLevel1(updateDTO.getItemCategoryLevel1());
        }
        if (updateDTO.getItemCategoryLevel2() != null) {
            existingItem.setItemCategoryLevel2(updateDTO.getItemCategoryLevel2());
        }
        if (updateDTO.getPurchasePrice() != null) {
            existingItem.setPurchasePrice(updateDTO.getPurchasePrice());
        }
        if (updateDTO.getPurchaseDate() != null) {
            existingItem.setPurchaseDate(updateDTO.getPurchaseDate());
        }
        if (StringUtils.hasText(updateDTO.getOwnId())) {
            existingItem.setOwnId(updateDTO.getOwnId());
        }

        existingItem.setUpdateTime(LocalDateTime.now());
        itemMapper.updateById(existingItem);
        return existingItem;
    }

    @Override
    public void deleteItem(Long id) {
        Item item = itemMapper.selectById(id);
        if (item == null) {
            throw new BusinessException("商品不存在");
        }
        item.setDeleted(true);
        item.setUpdateTime(LocalDateTime.now());
        itemMapper.updateById(item);
    }

    private void validatePurchasePrice(BaseCategory category, java.math.BigDecimal price) {
        if (category == BaseCategory.CONSUMABLE && price.compareTo(java.math.BigDecimal.valueOf(1000)) > 0) {
            throw new BusinessException("消耗品价格不能超过1000元");
        }
    }
}