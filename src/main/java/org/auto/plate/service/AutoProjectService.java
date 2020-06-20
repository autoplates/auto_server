package org.auto.plate.service;

import org.auto.plate.entity.AutoProject;
import org.auto.plate.entity.RespBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (AutoProject)表服务接口
 *
 * @author makejava
 * @since 2020-04-13 01:51:10
 */
@Service
public interface AutoProjectService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AutoProject queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AutoProject> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param autoProject 实例对象
     * @return 实例对象
     */
    AutoProject insert(AutoProject autoProject);

    /**
     * 修改数据
     *
     * @param autoProject 实例对象
     * @return 实例对象
     */
    AutoProject update(AutoProject autoProject);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 根据项目类型获取分页数据
     * @param projectType
     * @param pageSize
     * @param pageNum
     * @return
     */
    RespBean getProjectList(String query, Integer projectType, Integer userId, Integer pageSize, Integer pageNum);

    /**
     * 添加项目
     * @param autoProject
     * @return
     */
    RespBean addProject(AutoProject autoProject);

    RespBean deleteProject(Integer projectId);

    RespBean addProjectUser(Integer projectId, Integer userId);

    RespBean updateProjectUser(AutoProject autoProject);

    RespBean getUserProList(Integer userId);

    RespBean getProjectNameList(Integer userId);

    List<AutoProject> findUserProList(Integer userId);
}