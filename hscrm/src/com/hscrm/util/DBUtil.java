package com.hscrm.util;

import java.sql.*;

public class DBUtil {
    //======四条连接信息======//
    private static final String  driverName;
    private static final String  url;
    private static final String  user;
    private static final String  password;
    //jdbc对象
    private Connection   conn=null;
    private PreparedStatement  ps=null;
    private ResultSet  rs=null;
    //======静态块注册驱动：只需要注册一次即可======//
    static{
        //0、初始化连接信息
        driverName="com.mysql.cj.jdbc.Driver";
        url="jdbc:mysql:///hscrm?serverTimezone=Asia/Shanghai";
        user="root";
        password="root";
        //1、注册驱动
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //======获取连接======//
    public   void  getConnection(){
        try {
            //2、建立连接
            conn= DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //======断开连接======//
    public  void  close(){
        //6、断开连接
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    //======更新操作：增删改======//
    public int update(String sql,Object[] objs){
        int i=0;
        try {
            getConnection();
            //3、创建sql对象
            ps=conn.prepareStatement(sql);
            for (int j = 0; j < objs.length; j++) {
                ps.setObject(j+1,objs[j]);
            }
            //4、执行sql
            i=ps.executeUpdate();
            //5、业务处理
        } catch ( SQLException e) {
            e.printStackTrace();
        }
        return   i;
    }
    //======查询操作======//
    public   ResultSet  select(String sql,Object[] objs){
        try {
            getConnection();
            //3、创建sql对象
            ps=conn.prepareStatement(sql);
            for (int j = 0; j < objs.length; j++) {
                ps.setObject(j+1,objs[j]);
            }
            //4、执行sql
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  rs;
    }
}