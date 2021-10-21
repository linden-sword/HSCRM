package com.hscrm.service.impl;

import com.hscrm.dao.EmpDao;
import com.hscrm.dao.impl.EmpDaoImpl;
import com.hscrm.domain.Emp;
import com.hscrm.service.EmpService;

public class EmpServiceImpl implements EmpService {
    private EmpDao empDao = new EmpDaoImpl();
    @Override
    public int register(Emp emp) {
        return empDao.addEmp(emp);
    }

    @Override
    public int login(String username, String passwd) {
        Emp emp = empDao.findEmpByUsername(username);
        if (emp == null){
            return 0;
        }
        if (!emp.getPasswd().equals(passwd)){
            return 1;
        }
        return 2;
    }

    @Override
    public int usernameUnique(String username) {
        Emp emp = empDao.findEmpByUsername(username);
        if (emp == null){
            return 0;
        }
        return 1;
    }

    @Override
    public int updatePasswd(String username, String oldPasswd, String newPasswd) {
        String oldpasswd = empDao.findOldPasswdByUsername(username);
        if (!oldPasswd.equals(oldpasswd)){
            //原密码输错
            return -1;
        }
        return empDao.updatePasswdByUsername(username,newPasswd);
    }

    @Override
    public int getEmpIdByUsername(String username) {
        return empDao.findEmpByUsername(username).getE_id();
    }
}
