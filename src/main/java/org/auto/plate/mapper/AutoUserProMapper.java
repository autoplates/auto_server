package org.auto.plate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.auto.plate.entity.AutoUserPro;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (AutoUserPro)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-15 20:59:58
 */
@Mapper
public interface AutoUserProMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AutoUserPro queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AutoUserPro> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param autoUserPro 实例对象
     * @return 对象列表
     */
    List<AutoUserPro> queryAll(AutoUserPro autoUserPro);

    /**
     * 新增数据
     *
     * @param autoUserPro 实例对象
     * @return 影响行数
     */
    int insert(AutoUserPro autoUserPro);

    /**
     * 修改数据
     *
     * @param autoUserPro 实例对象
     * @return 影响行数
     */
    int update(AutoUserPro autoUserPro);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    int deleteByProjectId(Integer projectId);

    int addProjectUser(@Param("projectId") Integer projectId, @Param("userId") Integer userId);

    AutoUserPro selectObject(@Param("projectId") Integer projectId, @Param("userId") Integer userId);
}