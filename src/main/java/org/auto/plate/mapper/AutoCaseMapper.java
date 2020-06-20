package org.auto.plate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.auto.plate.entity.AutoCase;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * (AutoCase)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-18 23:34:04
 */
@Mapper
public interface AutoCaseMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AutoCase queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AutoCase> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param autoCase 实例对象
     * @return 对象列表
     */
    List<AutoCase> queryAll(AutoCase autoCase);

    /**
     * 新增数据
     *
     * @param autoCase 实例对象
     * @return 影响行数
     */
    int insert(AutoCase autoCase);

    /**
     * 修改数据
     *
     * @param autoCase 实例对象
     * @return 影响行数
     */
    int update(AutoCase autoCase);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<AutoCase> selectAllAutoCaseByUser(@Param("query") String query, @Param("userId") Integer userId, @Param("firstNum") Integer firstNum, @Param("pageSize") Integer pageSize, @Param("type") Integer type);

    int selectAllAutoCaseByProId(Integer projectId);

    int selectCaseCount(@Param("projectId") Integer projectId, @Param("type") Integer type);

    AutoCase selectAutoCaseByCreateDate(Date date);

    List<AutoCase> selectAllByProId(@Param("projectId") Integer projectId, @Param("type") Integer type);

    List<AutoCase> queryAllCase(@Param("query") String query, @Param("userId") Integer userId, @Param("type") Integer type);

    int deleteByIdList(List<Integer> autoCaseIdList);

    List<AutoCase> selectAllAutoCaseByProject(@Param("query") String query, @Param("projectId") Integer projectId, @Param("firstNum") Integer firstNum, @Param("pageSize") Integer pageSize, @Param("type") Integer type);

    Integer queryAllCaseCountByProject(@Param("projectId") Integer projectId,@Param("type") Integer type);
}