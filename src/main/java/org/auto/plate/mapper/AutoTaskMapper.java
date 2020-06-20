package org.auto.plate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.auto.plate.entity.AutoTask;

import java.util.List;

/**
 * (AutoUserPro)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-15 20:59:58
 */
@Mapper
public interface AutoTaskMapper {

    /**
     * 分页查询tasklist
     * @param query
     * @param userId
     * @param firstNum
     * @param pageSize
     * @return
     */
    List<AutoTask> selectAllTaskList(@Param("query") String query, @Param("userId") Integer userId, @Param("firstNum") Integer firstNum, @Param("pageSize") Integer pageSize);

    AutoTask selectTaskById(@Param("taskId") Integer taskId);

    int update(AutoTask autoTask);

    int getAllTaskCount(@Param("userId") Integer userId);

    int insert(AutoTask autoTask);

    List<AutoTask> selectTaskList();

    List<AutoTask> findTaskList(@Param("userId") Integer userId);
}
