package org.auto.plate.service.impl;

import org.auto.plate.entity.AutoElement;
import org.auto.plate.entity.AutoPage;
import org.auto.plate.entity.AutoProject;
import org.auto.plate.entity.RespBean;
import org.auto.plate.mapper.AutoElementMapper;
import org.auto.plate.mapper.AutoPageMapper;
import org.auto.plate.service.AutoElementService;
import org.auto.plate.service.AutoPageService;
import org.auto.plate.service.AutoProjectService;
import org.auto.plate.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (AutoPage)表服务实现类
 *
 * @author makejava
 * @since 2020-04-15 23:58:10
 */
@Service("autoPageService")
public class AutoPageServiceImpl implements AutoPageService {
    @Resource
    AutoPageMapper autoPageMapper;
    @Resource
    AutoProjectService autoProjectService;
    @Resource
    UserService userService;
    @Resource
    AutoElementMapper autoElementMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AutoPage queryById(Integer id) {
        return this.autoPageMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<AutoPage> queryAllByLimit(int offset, int limit) {
        return this.autoPageMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param autoPage 实例对象
     * @return 实例对象
     */
    @Override
    public AutoPage insert(AutoPage autoPage) {
        this.autoPageMapper.insert(autoPage);
        return autoPage;
    }

    /**
     * 修改数据
     *
     * @param autoPage 实例对象
     * @return 实例对象
     */
    @Override
    public AutoPage update(AutoPage autoPage) {
        this.autoPageMapper.update(autoPage);
        return this.queryById(autoPage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.autoPageMapper.deleteById(id) > 0;
    }

    @Override
    public RespBean selectAllPageList(String query, Integer userId, Integer pageNum, Integer pageSize) {
        int firstNum = (pageNum-1) * pageSize;
        List<AutoPage> autoPageList = autoPageMapper.selectAllPageList(query,userId,firstNum,pageSize);
        if (autoPageList.size() == 0) {
            return RespBean.ok("暂无数据！！！");
        }
        return RespBean.ok("获取成功！！！",autoPageList);
    }

    @Override
    public RespBean addPage(AutoPage autoPage) {
        autoPage.setProjectname(autoProjectService.queryById(autoPage.getProjectid()).getProjectname());
        autoPage.setCreateby(userService.queryById(autoPage.getUserId()).getUsername());
        autoPage.setCreatedate(new Date());
        autoPage.setElecount(0);
        int count = autoPageMapper.insert(autoPage);
        if (count != 1) {
            return RespBean.error("添加失败");
        }
        return RespBean.ok("添加成功",count);
    }

    @Override
    public RespBean deleteByPageId(Integer id) {
        AutoElement element = autoElementMapper.queryByPageId(id);
        if (element != null) {
            return RespBean.error("该页面下存在元素！！！");
        }
        int num = this.autoPageMapper.deleteById(id);
        if (num != 1) {
            return RespBean.error("删除失败！！！");
        }
        return RespBean.ok("删除成功！！！",num);
    }

    /**
     * 根据projectId获取pageList
     * @param projectId
     * @return
     */
    @Override
    public RespBean getPageListByProId(String projectId) {
        return RespBean.ok("获取成功！！！",autoPageMapper.getPageListByProId(projectId));
    }


}