package org.auto.plate.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AutoCronMapper {

    String getCron();
}
