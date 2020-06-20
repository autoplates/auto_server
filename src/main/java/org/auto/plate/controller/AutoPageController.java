package org.auto.plate.controller;

import org.auto.plate.entity.AutoPage;
import org.auto.plate.entity.RespBean;
import org.auto.plate.service.AutoPageService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (AutoPage)表控制层
 *
 * @author makejava
 * @since 2020-04-15 23:58:10
 */
@RestController
@RequestMapping("api")
public class AutoPageController {
    /**
     * 服务对象
     */
    @Autowired
    AutoPageService autoPageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectByPageId")
    public AutoPage selectOne(Integer id) {
        return this.autoPageService.queryById(id);
    }

    @GetMapping("/getPageListByProId")
    public RespBean getPageListByProId(@RequestParam String projectId) {
        return autoPageService.getPageListByProId(projectId);
    }


    @GetMapping("/getAllPageList")
    public RespBean getAllElementList(@RequestParam String query, Integer userId, Integer pageNum, Integer pageSize) {
        return autoPageService.selectAllPageList(query,userId,pageNum,pageSize);
    }

    @RequestMapping(value = "/addPage",method = RequestMethod.POST)
    public RespBean addPage(@RequestBody AutoPage autoPage) {
        return autoPageService.addPage(autoPage);
    }

    @RequestMapping(value = "/deletePage",method = RequestMethod.DELETE)
    public RespBean deletePage(@RequestParam Integer id) {
        return autoPageService.deleteByPageId(id);
    }
}