package org.auto.plate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.auto.plate.entity.AutoEmail;

@Mapper
public interface AutoEmailMapper {
    AutoEmail selectAutoEmail();
}
