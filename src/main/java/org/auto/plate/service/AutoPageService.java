package org.auto.plate.service;

import org.auto.plate.entity.AutoPage;
import org.auto.plate.entity.RespBean;

import java.util.List;

/**
 * (AutoPage)表服务接口
 *
 * @author makejava
 * @since 2020-04-15 23:58:10
 */
public interface AutoPageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AutoPage queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AutoPage> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param autoPage 实例对象
     * @return 实例对象
     */
    AutoPage insert(AutoPage autoPage);

    /**
     * 修改数据
     *
     * @param autoPage 实例对象
     * @return 实例对象
     */
    AutoPage update(AutoPage autoPage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    RespBean selectAllPageList(String query, Integer userId, Integer pageNum, Integer pageSize);

    RespBean addPage(AutoPage autoPage);

    RespBean deleteByPageId(Integer id);

    RespBean getPageListByProId(String projectId);
}