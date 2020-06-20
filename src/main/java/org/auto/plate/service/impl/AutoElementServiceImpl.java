package org.auto.plate.service.impl;

import org.auto.plate.entity.AutoElement;
import org.auto.plate.entity.AutoPage;
import org.auto.plate.entity.RespBean;
import org.auto.plate.mapper.AutoElementMapper;
import org.auto.plate.mapper.AutoPageMapper;
import org.auto.plate.service.AutoElementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (AutoElement)表服务实现类
 *
 * @author makejava
 * @since 2020-04-17 00:39:57
 */
@Service
public class AutoElementServiceImpl implements AutoElementService {
    @Resource
    AutoElementMapper autoElementMapper;

    @Resource
    AutoPageMapper autoPageMapper;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AutoElement queryById(Integer id) {
        return this.autoElementMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<AutoElement> queryAllByLimit(int offset, int limit) {
        return this.autoElementMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param autoElement 实例对象
     * @return 实例对象
     */
    @Override
    public AutoElement insert(AutoElement autoElement) {
        this.autoElementMapper.insert(autoElement);
        return autoElement;
    }

    /**
     * 修改数据
     *
     * @param autoElement 实例对象
     * @return 实例对象
     */
    @Override
    public AutoElement update(AutoElement autoElement) {
        this.autoElementMapper.update(autoElement);
        return this.queryById(autoElement.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        boolean falg = this.autoElementMapper.deleteById(id) > 0;

        return falg;
    }

    /**
     * 获取其中一个页面中的所有元素
     * @param query
     * @param pageId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public RespBean getElementList(String query, Integer pageId, Integer pageNum, Integer pageSize) {

        AutoElement autoElement = autoElementMapper.queryByPageId(pageId);
        if (autoElement == null){
            return RespBean.error("请先为页面添加元素！！！");
        }
        int firstNum = (pageNum-1) * pageSize;
        List<AutoElement> autoElementList = autoElementMapper.selectAllElementByPageId(query,pageId,firstNum,pageSize);
        if (autoElementList.size() == 0) {
            return RespBean.error("暂无数据！！！");
        }
        int count = autoElementMapper.selectElementByPageId(pageId).size();
        RespBean respBean = RespBean.ok("获取成功！！！", String.valueOf(count), autoElementList);
        return respBean;
    }

    @Override
    public RespBean getElement(Integer pageId) {
        AutoElement autoElement = autoElementMapper.queryByPageId(pageId);
        if (autoElement == null){
            return RespBean.error("请先为页面添加元素！！！");
        }
        return RespBean.ok("数据存在！！！",autoElement);
    }

    @Override
    public RespBean getElementListByProId(String query, Integer projectId, Integer pageNum, Integer pageSize) {
        int firstNum = (pageNum-1) * pageSize;
        List<AutoElement> autoElementList = autoElementMapper.findElementListByProId(query,projectId,firstNum,pageSize);
        if (autoElementList.size() == 0) {
            return RespBean.error("元素获取失败！！！");
        }
        return RespBean.ok("获取成功！！！",autoElementList);
    }

    @Override
    public RespBean addElement(AutoElement autoElement) {
        autoElement.setCreatedate(new Date());
        int count = autoElementMapper.insert(autoElement);
        if (count == 0){
            return RespBean.error("添加失败!!!");
        }
        AutoPage autoPage = autoPageMapper.queryById(autoElement.getPageid());
        autoPage.setElecount(autoPage.getElecount() + 1);
        autoPageMapper.update(autoPage);
        return RespBean.ok("添加成功！！！",autoElement);
    }

    @Override
    public RespBean getElementListByPageId(Integer pageId) {
        return RespBean.ok("获取成功！！！",autoElementMapper.selectElementByPageId(pageId));
    }
}