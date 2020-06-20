package org.auto.plate.service.impl;

import org.auto.plate.entity.AutoEleAddress;
import org.auto.plate.entity.RespBean;
import org.auto.plate.mapper.AutoEleAddressMapper;
import org.auto.plate.service.AutoEleAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AutoEleAddressServiceImpl implements AutoEleAddressService {

    @Resource
    AutoEleAddressMapper autoEleAddressMapper;

    /**
     * 获取元素定位类型
     * @return
     */
    @Override
    public RespBean getAutoEleAddressList() {

        List<AutoEleAddress> list = autoEleAddressMapper.getAutoEleAddressList();
        if (list.size() == 0){
            return RespBean.error("获取失败！！！");
        }
        return RespBean.ok("获取成功！！！",list);
    }
}
