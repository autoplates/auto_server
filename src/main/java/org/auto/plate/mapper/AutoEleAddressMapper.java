package org.auto.plate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.auto.plate.entity.AutoEleAddress;

import java.util.List;

@Mapper
public interface AutoEleAddressMapper {

    /**获取元素定位路径*/
    List<AutoEleAddress> getAutoEleAddressList();
}
