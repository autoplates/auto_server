package org.auto.plate.utils.impl;

import org.auto.plate.entity.AutoEmail;
import org.auto.plate.entity.AutoTask;
import org.auto.plate.mapper.AutoEmailMapper;
import org.auto.plate.mapper.AutoTaskMapper;
import org.auto.plate.service.ExecuteService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ScheduledTaskUtils {


    @Resource
    AutoTaskMapper autoTaskMapper;

    @Resource
    ExecuteService executeService;

    @Resource
    AutoEmailMapper autoEmailMapper;

    public void executeScheduleTask() {
        List<AutoTask> autoTaskList = autoTaskMapper.selectTaskList();
        for (int i = 0; i < autoTaskList.size(); i++) {
            //  判断是否为定时执行的task
            if (autoTaskList.get(i).getIsScheduled() == 1) {
                executeService.executeTask(autoTaskList.get(i).getId(),1,"CHROME");
            }
        }
        this.sendEmail();
    }

     public void sendEmail() {
        String from = SendMailUtil.username;
        AutoEmail autoEmail = autoEmailMapper.selectAutoEmail();
        String[] to = autoEmail.getSendto().split(",");
        String[] copyto = autoEmail.getCopyto().split(",");

        String subject = "【UI自动化测试报告】";
        String content = "这是邮件内容，仅仅是测试，不需要回复.";
        String[] fileList = new String[1];
        fileList[0] = "C:/xxx.jpg";
        SendMailUtil.getInstance().sendMail(from, to, copyto, subject, content, fileList);
    }

}
