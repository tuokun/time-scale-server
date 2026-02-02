package com.timescale.server.service;

import com.timescale.server.dto.ItemCreateDTO;
import com.timescale.server.dto.ItemQueryDTO;
import com.timescale.server.dto.ItemUpdateDTO;
import com.timescale.server.dto.PageResult;
import com.timescale.server.entity.Item;

public interface ItemService {
    
    PageResult<Item> getItemsByPage(ItemQueryDTO queryDTO);
    
    Item getItemById(Long id);
    
    Item createItem(ItemCreateDTO createDTO);
    
    Item updateItem(Long id, ItemUpdateDTO updateDTO);
    
    void deleteItem(Long id);
}