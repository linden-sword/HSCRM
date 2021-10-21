package com.hscrm.service;

import com.hscrm.domain.Emp;

public interface EmpService {
    /**
     * 注册
     * @param emp
     * @return
     */
    int register(Emp emp);

    /**
     * 登录
     * @param username
     * @param passwd
     * @return
     */
    int login(String username,String passwd);

    /**
     * 判断用户名唯一性
     * @param username
     * @return
     */
    int usernameUnique(String username);
    /**
     * 修改密码
     * @param username
     * @param oldPasswd
     * @param newPasswd
     * @return
     */
    int updatePasswd(String username,String oldPasswd,String newPasswd);

    /**
     * 通过用户名获取e_id
     * @param username
     * @return
     */
    int getEmpIdByUsername(String username);
}
