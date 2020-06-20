package org.auto.plate.service;

import org.auto.plate.entity.AutoCase;
import org.auto.plate.entity.RespBean;

import java.util.List;

/**
 * (AutoCase)表服务接口
 *
 * @author makejava
 * @since 2020-04-18 23:34:05
 */
public interface AutoCaseService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AutoCase queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AutoCase> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param autoCase 实例对象
     * @return 实例对象
     */
    AutoCase insert(AutoCase autoCase);

    /**
     * 修改数据
     *
     * @param autoCase 实例对象
     * @return 实例对象
     */
    AutoCase update(AutoCase autoCase);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    RespBean addAutoTestCase(AutoCase autoCase);
    // 按照type类型，userId获取case和template列表
    RespBean getAutoCaseOrTemplateListByUserId(String query, Integer userId, Integer pageNum, Integer pageSize, Integer type);

    RespBean getAutoCaseCountByProId(Integer projectId);

    RespBean getAutoTestCaseCountList(Integer id, Integer userId);

    RespBean getAutoCaseOrTemplateProId(Integer projectId, Integer type);

    boolean delete(List<AutoCase> autoCase);
    // 按照type类型，projectId获取case和template列表
    RespBean getAllAutoTestCaseByProjectId(String query, Integer projectId, Integer pageNum, Integer pageSize, Integer type);


}