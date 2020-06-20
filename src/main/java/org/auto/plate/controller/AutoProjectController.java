package org.auto.plate.controller;

import org.apache.ibatis.annotations.Delete;
import org.auto.plate.entity.AutoProject;
import org.auto.plate.entity.RespBean;
import org.auto.plate.service.AutoProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AutoProject)表控制层
 *
 * @author makejava
 * @since 2020-04-13 01:51:18
 */
@RestController
@RequestMapping("api")
public class AutoProjectController {
    /**
     * 服务对象
     */
    @Resource
    private AutoProjectService autoProjectService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("project")
    public AutoProject selectOne(Integer id) {
        return this.autoProjectService.queryById(id);
    }

    @GetMapping("projectlist")
    public RespBean getProjectList(@RequestParam String query, Integer projectType, Integer userId, Integer pageNum, Integer pageSize) {
        return autoProjectService.getProjectList(query, projectType, userId, pageSize, pageNum);
    }

    @PostMapping("insertProject")
    public RespBean addProject(@RequestBody AutoProject autoProject) {
        return autoProjectService.addProject(autoProject);
    }

    @RequestMapping(value = "/deleteProjectById", method = RequestMethod.DELETE)
    public RespBean deleteProject(@RequestParam Integer projectId) {
        return autoProjectService.deleteProject(projectId);
    }

    @RequestMapping(value = "/addprojectuser", method = RequestMethod.POST)
    public RespBean addProjectUser(@RequestParam Integer projectId, Integer userId) {
        return autoProjectService.addProjectUser(projectId,userId);
    }

    @RequestMapping(value = "/updateproject",method = RequestMethod.POST)
    public RespBean updateProject(@RequestBody AutoProject autoProject) {
        return autoProjectService.updateProjectUser(autoProject);
    }

    @GetMapping("/prolist")
    public RespBean getUserProList(@RequestParam Integer userId) {
        return autoProjectService.getUserProList(userId);
    }

    @GetMapping("/getProjectName")
    public RespBean getProjectNameList(@RequestParam Integer userId) {
       return autoProjectService.getProjectNameList(userId);
    }
}