package com.jeeplus.sys.service;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jeeplus.sys.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;
/**
 * 定时器
 */


/**
 * <p>Description: 定时器</p >
 * <p>Copyright: Copyright (c) 2022</p >
 *
 * @author zhunter
 * @version 1.0
 * @date 2022-09-06-17:20
 */
@Component
public class SimpleSchedule {
    @Autowired
    private UserService userService;
    private Integer time = 0;

    /**
     * 定时器定义，设置执行时间
     * 0 5 * * *? //每5分钟
     */

    @Scheduled(fixedDelay = 60*1000)
    private void updateLoginStatus() {
        Date newDate = DateUtil.date();
        newDate = DateUtil.offset(newDate, DateField.MINUTE, -20);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ge("login_err_num",0);
        queryWrapper.lt("update_date",newDate);
        List<User> userList = userService.list(queryWrapper);
        for (User user: userList) {
            user.setLoginErrNum(0);
            userService.saveOrUpdate(user);
        }
    }
}
