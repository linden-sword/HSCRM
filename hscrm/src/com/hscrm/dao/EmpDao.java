package com.hscrm.dao;

import com.hscrm.domain.Emp;

public interface EmpDao {
    /**
     * 添加员工
     * @param emp
     * @return
     */
    int addEmp(Emp emp);

    /**
     * 通过用户名查找员工
     * @param username
     * @return
     */
    Emp findEmpByUsername(String username);

    /**
     * 通过id查找用户
     * @param e_id
     * @return
     */
    Emp findEmpById(int e_id);

    /**
     * 通过用户名查询老密码
     * @param username
     * @return
     */
    String findOldPasswdByUsername(String username);

    /**
     * 通过用户名修改密码
     * @param username
     * @return
     */
    int updatePasswdByUsername(String username,String newPasswd);
}
