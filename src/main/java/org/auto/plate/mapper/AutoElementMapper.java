package org.auto.plate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.auto.plate.entity.AutoElement;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (AutoElement)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-17 00:39:56
 */
@Mapper
public interface AutoElementMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AutoElement queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AutoElement> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param autoElement 实例对象
     * @return 对象列表
     */
    List<AutoElement> queryAll(AutoElement autoElement);

    /**
     * 新增数据
     *
     * @param autoElement 实例对象
     * @return 影响行数
     */
    int insert(AutoElement autoElement);

    /**
     * 修改数据
     *
     * @param autoElement 实例对象
     * @return 影响行数
     */
    int update(AutoElement autoElement);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<AutoElement> selectAllElementByPageId(@Param("query") String query, @Param("pageId") Integer pageId,
                                               @Param("firstNum") Integer firstNum, @Param("pageSize") Integer pageSize);

    AutoElement queryByPageId(Integer pageId);

    List<AutoElement> findElementListByProId(@Param("query") String query, @Param("projectId") Integer projectId,
                                             @Param("firstNum") Integer firstNum, @Param("pageSize") Integer pageSize);

    List<AutoElement> selectElementByPageId(Integer pageId);

}