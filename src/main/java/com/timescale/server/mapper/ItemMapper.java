package com.timescale.server.mapper;

import com.timescale.server.dto.ItemQueryDTO;
import com.timescale.server.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ItemMapper {
    
    List<Item> findByCondition(ItemQueryDTO queryDTO);
}