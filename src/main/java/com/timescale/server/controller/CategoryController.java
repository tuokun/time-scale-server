package com.timescale.server.controller;

import com.timescale.server.dto.ResponseDTO;
import com.timescale.server.entity.IconMapping;
import com.timescale.server.enums.BaseCategory;
import com.timescale.server.enums.SubCategory;
import com.timescale.server.mapper.IconMappingMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private IconMappingMapper iconMappingMapper;

    @GetMapping("/level1")
    public ResponseDTO<List<Map<String, Object>>> getLevel1Categories() {
        List<Map<String, Object>> result = Arrays.stream(BaseCategory.values())
                .map(cat -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", cat.name());
                    map.put("displayName", cat.getDisplayName());
                    map.put("description", cat.getDescription());
                    return map;
                })
                .collect(Collectors.toList());
        return ResponseDTO.success(result);
    }

    @GetMapping("/level2")
    public ResponseDTO<List<Map<String, Object>>> getLevel2Categories(
            @RequestParam(required = false) String level1Code) {
        List<SubCategory> categories;
        if (level1Code != null && !level1Code.isEmpty()) {
            BaseCategory level1Category = BaseCategory.fromString(level1Code);
            if (level1Category == null) {
                return ResponseDTO.error("无效的一级分类代码");
            }
            categories = Arrays.asList(SubCategory.getByBase(level1Category));
        } else {
            categories = Arrays.asList(SubCategory.values());
        }

        List<Map<String, Object>> result = categories.stream()
                .map(cat -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", cat.name());
                    map.put("displayName", cat.getDisplayName());
                    map.put("level1Code", cat.getBaseCategory().name());
                    map.put("level1DisplayName", cat.getBaseCategory().getDisplayName());
                    return map;
                })
                .collect(Collectors.toList());
        return ResponseDTO.success(result);
    }

    @GetMapping("/icon-mapping")
    public ResponseDTO<List<IconMapping>> getIconMapping() {
        LambdaQueryWrapper<IconMapping> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(IconMapping::getSortOrder);
        List<IconMapping> mappings = iconMappingMapper.selectList(wrapper);
        return ResponseDTO.success(mappings);
    }

    @GetMapping("/icon/{level2Category}")
    public ResponseDTO<String> getIconByCategory(@PathVariable String level2Category) {
        LambdaQueryWrapper<IconMapping> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(IconMapping::getSubCategory, level2Category);
        IconMapping mapping = iconMappingMapper.selectOne(wrapper);
        if (mapping == null) {
            return ResponseDTO.error("未找到对应图标");
        }
        return ResponseDTO.success(mapping.getIcon());
    }

    @GetMapping("/all")
    public ResponseDTO<Map<String, Object>> getAllCategories() {
        Map<String, Object> result = new HashMap<>();

        List<Map<String, Object>> level1Categories = Arrays.stream(BaseCategory.values())
                .map(cat -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", cat.name());
                    map.put("displayName", cat.getDisplayName());
                    map.put("description", cat.getDescription());
                    return map;
                })
                .collect(Collectors.toList());
        result.put("level1Categories", level1Categories);

        List<Map<String, Object>> level2Categories = Arrays.stream(SubCategory.values())
                .map(cat -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", cat.name());
                    map.put("displayName", cat.getDisplayName());
                    map.put("level1Code", cat.getBaseCategory().name());
                    return map;
                })
                .collect(Collectors.toList());
        result.put("level2Categories", level2Categories);

        LambdaQueryWrapper<IconMapping> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(IconMapping::getSortOrder);
        List<IconMapping> iconMappings = iconMappingMapper.selectList(wrapper);
        result.put("iconMappings", iconMappings);

        return ResponseDTO.success(result);
    }
}
