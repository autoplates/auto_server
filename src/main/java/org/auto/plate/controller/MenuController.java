package org.auto.plate.controller;

import org.auto.plate.entity.Menu;
import org.auto.plate.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Menu)表控制层
 *
 * @author makejava
 * @since 2020-04-12 22:18:13
 */
@RestController
@RequestMapping("/api")
public class MenuController {
    /**
     * 服务对象
     */
    @Resource
    private MenuService menuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/menulist")
    public List<Menu> selectMenuList() {
        return this.menuService.selectMenuList();
    }

}