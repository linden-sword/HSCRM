package com.hscrm.controller;

import com.hscrm.service.EmpService;
import com.hscrm.service.impl.EmpServiceImpl;
import com.hscrm.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/userNameUnique")
public class UsernameUniqueServlet extends HttpServlet {
    EmpService empService = new EmpServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        //唯一性判断
        int flag = empService.usernameUnique(username);
        //响应
        JsonUtil jsonUtil = new JsonUtil();
        String responseString = jsonUtil.toString(1000,"OK",flag);

        PrintWriter out = resp.getWriter();
        out.write(responseString);
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
