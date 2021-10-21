package com.hscrm.dao.impl;

import com.hscrm.dao.EmpDao;
import com.hscrm.domain.Emp;
import com.hscrm.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpDaoImpl implements EmpDao {
    private DBUtil db = new DBUtil();
    @Override
    public int addEmp(Emp emp) {
        String sql = "insert into emp values(null,?,?,?,?,?)";
        Object[] objs = {emp.getE_name(),emp.getE_sex(),emp.getE_tel(),emp.getUsername(),emp.getPasswd()};
        int flag = db.update(sql,objs);
        db.close();
        return flag;
    }

    @Override
    public Emp findEmpByUsername(String username) {
        String sql = "select * from emp where username = ?";
        Object[] objs = {username};
        ResultSet resultSet = db.select(sql,objs);
        Emp emp = null;
        try {
            if (resultSet.next()){
                emp = new Emp();
                emp.setE_id(resultSet.getInt("e_id"));
                emp.setE_name(resultSet.getString("e_name"));
                emp.setE_sex(resultSet.getString("e_sex"));
                emp.setE_tel(resultSet.getString("e_tel"));
                emp.setUsername(resultSet.getString("username"));
                emp.setPasswd(resultSet.getString("passwd"));
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            db.close();
        }
        return emp;
    }

    @Override
    public Emp findEmpById(int e_id) {
        String sql = "select * from emp where e_id = ?";
        Object[] objs = {e_id};
        ResultSet resultSet = db.select(sql,objs);
        Emp emp = null;
        try {
            if (resultSet.next()){
                emp = new Emp();
                emp.setE_id(resultSet.getInt("e_id"));
                emp.setE_name(resultSet.getString("e_name"));
                emp.setE_sex(resultSet.getString("e_sex"));
                emp.setE_tel(resultSet.getString("e_tel"));
                emp.setUsername(resultSet.getString("username"));
                emp.setPasswd(resultSet.getString("passwd"));
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            db.close();
        }
        return emp;
    }

    @Override
    public String findOldPasswdByUsername(String username) {
        String oldPasswd = null;
        String sql = "select passwd from emp where username = ?";
        Object[] objs = {username};
        ResultSet resultSet = db.select(sql,objs);
        try {
            if (resultSet.next()){
                oldPasswd = resultSet.getString("passwd");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.close();
        }
        return oldPasswd;
    }

    @Override
    public int updatePasswdByUsername(String username, String newPasswd) {
        String sql = "update emp set passwd = ? where username = ?";
        Object[] objs = {newPasswd ,username};
        int flag = db.update(sql,objs);
        db.close();
        return flag;
    }
}
