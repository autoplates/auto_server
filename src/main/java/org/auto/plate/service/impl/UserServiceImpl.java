package org.auto.plate.service.impl;

import org.auto.plate.entity.RespBean;
import org.auto.plate.entity.User;
import org.auto.plate.mapper.UserMapper;
import org.auto.plate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-04-09 23:32:14
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public RespBean insert(User user) {
        user.setCreatedate(new Date());
        /**判断参数是否为空字符串*/
        if (user.getUsername() == "" || user.getPassword() == "" || user.getAddress() == "" || user.getCompany() == ""){
            return RespBean.error("参数不能为空！！！");
        }
        /**判断用户名是否存在*/
        if (userMapper.selectUserByUsername(user.getUsername()) != null) {
            return RespBean.error("用户名已存在！请更换用户名！！！");
        }
        int count = userMapper.insert(user);
        if (count == 1) {
           return RespBean.ok("添加成功",user);
        }
        return RespBean.error("添加失败！请检查参数是否正确");
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userMapper.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userMapper.deleteById(id) > 0;
    }

    @Override
    public RespBean doLogin(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println(username + "************" + password);
        User userEntitly = userMapper.getUser(username,password);
        if (userEntitly == null) {
            return RespBean.error("用户名或密码错误");
        }
        return RespBean.ok("登录成功！",userEntitly);
    }

    @Override
    public RespBean getUserList() {
        List<User> userList = userMapper.selectUserList();
        if (userList.size() == 1) {
            return RespBean.error("暂无用户可添加");
        }
        return RespBean.ok("用户获取成功",userList);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("username:"+s);
        User user = userMapper.selectUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在！！！");
        }
        return user;
    }
}