package org.auto.plate.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.auto.plate.entity.AutoEventType;

import java.util.List;

/**
 * (AutoElement)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-17 00:39:56
 */
@Mapper
public interface AutoEventTypeMapper {

    /**获取元素事件列表 */
    List<AutoEventType> getAutoEventTypeList();
}
