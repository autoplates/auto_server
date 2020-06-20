package org.auto.plate.controller;

import org.auto.plate.entity.RespBean;
import org.auto.plate.service.AutoEleAddressService;
import org.auto.plate.service.AutoEventTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (AutoElement)表控制层
 *
 * @author makejava
 * @since 2020-04-17 00:39:57
 */
@RestController
@RequestMapping("api")
public class AutoEleAddressController {

    @Resource
    AutoEleAddressService autoEleAddressService;

    @GetMapping("/getAddressTypeList")
    public RespBean getAutoEleAddressList() {
        return autoEleAddressService.getAutoEleAddressList();
    }
}
