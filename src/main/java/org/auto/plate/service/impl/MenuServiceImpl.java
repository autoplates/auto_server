package org.auto.plate.service.impl;

import org.auto.plate.mapper.MenuMapper;
import org.auto.plate.entity.Menu;
import org.auto.plate.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuMapper menuMapper;

    @Override
    public Menu queryById(Integer id) {
        return null;
    }

    @Override
    public List<Menu> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public Menu insert(Menu menu) {
        return null;
    }

    @Override
    public Menu update(Menu menu) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    /**
     * 获取menuList
     * @return
     */
    @Override
    public  List<Menu> selectMenuList() {
        List<Menu> list = menuMapper.getMenuList();
        return list;
    }
}
