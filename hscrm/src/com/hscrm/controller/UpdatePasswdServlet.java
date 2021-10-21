package com.hscrm.controller;

import com.hscrm.service.EmpService;
import com.hscrm.service.impl.EmpServiceImpl;
import com.hscrm.util.JsonUtil;
import com.hscrm.util.MD5Util;
import com.hscrm.util.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user/updatePasswd")
public class UpdatePasswdServlet extends HttpServlet {
    private EmpService empService = new EmpServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
//        String username = req.getParameter("username");
//        username从token中获取
        String tokenString = (String) req.getAttribute("token");
        Token token = (Token) req.getServletContext().getAttribute(tokenString);
        String username = token.getUsername();

        String oldpasswd = req.getParameter("oldpasswd");
        String newpasswd = req.getParameter("newpasswd");
        System.out.println(username+","+oldpasswd+","+newpasswd);
        //修改密码
        int flag = empService.updatePasswd(username, MD5Util.toMd5(oldpasswd),MD5Util.toMd5(newpasswd));
        System.out.println(MD5Util.toMd5(oldpasswd)+","+MD5Util.toMd5(newpasswd));
        //响应
        JsonUtil<String> jsonUtil = new JsonUtil<>();
        String responseString = "";
        if (flag == -1){
            //老密码错误
            responseString = jsonUtil.toString(1005,"操作失败","旧密码错误");
        }
        if (flag == 0){
            //修改失败
            responseString = jsonUtil.toString(1005,"操作失败","修改失败");
        }
        if (flag == 1){
            //修改成功
            responseString = jsonUtil.toString(1000,"OK","修改成功");
        }
        PrintWriter out = resp.getWriter();
        out.write(responseString);
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
