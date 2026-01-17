package com.timescale.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.timescale.server.dto.ItemQueryDTO;
import com.timescale.server.dto.PageResult;
import com.timescale.server.entity.Item;
import com.timescale.server.mapper.ItemMapper;
import com.timescale.server.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    
    private final ItemMapper itemMapper;
    
    public ItemServiceImpl(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }
    
    @Override
    public PageResult<Item> getItemsByPage(ItemQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<Item> items = itemMapper.findByCondition(queryDTO);
        PageInfo<Item> pageInfo = new PageInfo<>(items);
        
        return new PageResult<>(
            pageInfo.getList(),
            pageInfo.getTotal(),
            pageInfo.getPageNum(),
            pageInfo.getPageSize()
        );
    }
}