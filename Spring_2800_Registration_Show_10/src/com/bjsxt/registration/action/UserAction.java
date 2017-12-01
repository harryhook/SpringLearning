package com.bjsxt.registration.action;

import com.bjsxt.registration.model.User;
import com.bjsxt.registration.service.UserManager;
import com.bjsxt.registration.vo.UserRegisterInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/*
Component最好设置为与类名相同的属性
否则会因为struts.objectFactory.spring.autoWire 下默认按 name 关联到 bean 上
 */
@Component("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven {

    private UserRegisterInfo userInfo = new UserRegisterInfo();

    private UserManager userManager;

    private List<User> users = new ArrayList<User>();
    private User user;

    @Override
    public String execute() throws Exception {
        User u = new User();
        u.setUsername(userInfo.getUsername());
        u.setPassword(userInfo.getPassword());
        if (userManager.exists(u)) {
            return "fail";
        }
        userManager.add(u);
        return "success";
    }

    public String load() {
        this.user = this.userManager.loadById(userInfo.getId());
        return "load";
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String list() {
        this.users = userManager.getUsers();
        return "list";
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    @Resource
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public UserRegisterInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserRegisterInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public Object getModel() {
        return userInfo;
    }
}