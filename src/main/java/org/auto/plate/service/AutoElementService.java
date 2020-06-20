package org.auto.plate.service;

import org.auto.plate.entity.AutoElement;
import org.auto.plate.entity.RespBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (AutoElement)表服务接口
 *
 * @author makejava
 * @since 2020-04-17 00:39:57
 */
@Service
public interface AutoElementService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AutoElement queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AutoElement> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param autoElement 实例对象
     * @return 实例对象
     */
    AutoElement insert(AutoElement autoElement);

    /**
     * 修改数据
     *
     * @param autoElement 实例对象
     * @return 实例对象
     */
    AutoElement update(AutoElement autoElement);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    RespBean getElementList(String query, Integer pageId, Integer pageNum, Integer pageSize);

    RespBean getElement(Integer pageId);

    RespBean getElementListByProId(String query, Integer projectId, Integer pageNum, Integer pageSize);

    RespBean addElement(AutoElement autoElement);

    RespBean getElementListByPageId(Integer pageId);
}