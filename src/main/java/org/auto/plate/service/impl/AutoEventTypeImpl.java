package org.auto.plate.service.impl;

import org.auto.plate.entity.AutoEventType;
import org.auto.plate.entity.RespBean;
import org.auto.plate.mapper.AutoEventTypeMapper;
import org.auto.plate.service.AutoEventTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AutoEventTypeImpl implements AutoEventTypeService {

    @Resource
    AutoEventTypeMapper autoEventTypeMapper;

    /**
     * 获取元素事件列表
     * @return
     */
    @Override
    public RespBean getAutoEventTypeList() {
        List<AutoEventType> list = autoEventTypeMapper.getAutoEventTypeList();
        if (list.size() == 0) {
            return RespBean.error("获取元素事件失败！！！");
        }
        return RespBean.ok("获取成功！！！！",list);
    }
}
