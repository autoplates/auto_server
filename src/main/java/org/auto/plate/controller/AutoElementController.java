package org.auto.plate.controller;

import org.auto.plate.entity.AutoElement;
import org.auto.plate.entity.AutoPage;
import org.auto.plate.entity.RespBean;
import org.auto.plate.mapper.AutoPageMapper;
import org.auto.plate.service.AutoElementService;
import org.auto.plate.service.AutoPageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (AutoElement)表控制层
 *
 * @author makejava
 * @since 2020-04-17 00:39:57
 */
@RestController
@RequestMapping("api")
public class AutoElementController {
    /**
     * 服务对象
     */
    @Resource
    AutoElementService autoElementService;
    @Resource
    AutoPageService autoPageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/getEleById")
    public RespBean selectOne(Integer id) {
        return RespBean.ok("获取成功！！！",this.autoElementService.queryById(id));
    }

    @GetMapping("/elelist")
    public RespBean getEleList(@RequestParam String query, Integer pageId, Integer pageNum, Integer pageSize) {
        return autoElementService.getElementList(query,pageId,pageNum,pageSize);
    }

    @GetMapping("/getElementListByPageId")
    public RespBean getElementListByPageId(@RequestParam Integer pageId) {
        return autoElementService.getElementListByPageId(pageId);
    }

    @GetMapping("/element")
    public RespBean getElement(@RequestParam Integer pageId) {
        return autoElementService.getElement(pageId);
    }

    @GetMapping("/getElementListByProId")
    public RespBean getElementListByProId(@RequestParam String query, Integer projectId, Integer pageNum, Integer pageSize){
        return autoElementService.getElementListByProId(query,projectId,pageNum,pageSize);
    }

    @RequestMapping(value = "/addElement", method = RequestMethod.POST)
    public RespBean addElement(@RequestBody AutoElement autoElement){
        return autoElementService.addElement(autoElement);
    }

    @RequestMapping(value = "/deleteElement", method = RequestMethod.DELETE)
    public RespBean deleteById(@RequestParam Integer id, Integer pageid) {
        boolean flag = autoElementService.deleteById(id);
        if (flag) {
            AutoPage autoPage = autoPageService.queryById(pageid);
            autoPage.setElecount(autoPage.getElecount() - 1);
            autoPageService.update(autoPage);
            return RespBean.ok("删除成功！！！");
        }
        return RespBean.error("删除失败！！！");
    }
}