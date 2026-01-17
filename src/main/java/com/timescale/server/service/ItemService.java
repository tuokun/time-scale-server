package com.timescale.server.service;

import com.timescale.server.dto.ItemQueryDTO;
import com.timescale.server.dto.PageResult;
import com.timescale.server.entity.Item;

public interface ItemService {
    
    PageResult<Item> getItemsByPage(ItemQueryDTO queryDTO);
}