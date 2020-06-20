package org.auto.plate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.auto.plate.entity.AutoPage;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (AutoPage)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-16 22:10:39
 */
@Mapper
public interface AutoPageMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AutoPage queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AutoPage> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param autoPage 实例对象
     * @return 对象列表
     */
    List<AutoPage> queryAll(AutoPage autoPage);

    /**
     * 新增数据
     *
     * @param autoPage 实例对象
     * @return 影响行数
     */
    int insert(AutoPage autoPage);

    /**
     * 修改数据
     *
     * @param autoPage 实例对象
     * @return 影响行数
     */
    int update(AutoPage autoPage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<AutoPage> selectAllPageList(@Param("query") String query, @Param("userId") Integer userId,
                                     @Param("firstNum") Integer firstNum, @Param("pageSize") Integer pageSize);

    AutoPage queryByProjectId(Integer projectId);

    List<AutoPage> getPageListByProId(String projectId);
}